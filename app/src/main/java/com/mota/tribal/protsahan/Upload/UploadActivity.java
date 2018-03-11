package com.mota.tribal.protsahan.Upload;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mota.tribal.protsahan.Helper.Urls;
import com.mota.tribal.protsahan.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UploadActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {

    private static final String TAG = UploadActivity.class.getSimpleName();
    private static final int REQUEST_VIDEO_CAPTURE = 300;
    private static final int READ_REQUEST_CODE = 200;
    private static final int REQUEST_IMAGE_CAPTURE = 100;
    private Uri uri;
    private String pathToStoredVideo, accessToken;
    private TextView videoName, imageName, docName;
    private Button captureVideoButton, imageButton, docButton;
    private int REQUEST_DOC_UPLOAD = 50;
    private Retrofit retrofit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        videoName = findViewById(R.id.video_name);
        imageName = findViewById(R.id.image_name);
        docName = findViewById(R.id.document_name);
        captureVideoButton = findViewById(R.id.capture_video);
        imageButton = findViewById(R.id.image_upload);
        docButton = findViewById(R.id.doc_upload);


        retrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        captureVideoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent videoCaptureIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                if (videoCaptureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(videoCaptureIntent, REQUEST_VIDEO_CAPTURE);
                }
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent imageCaptureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (imageCaptureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(imageCaptureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });

        docButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent docUpload = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                docUpload.setType("*/*");
                startActivityForResult(docUpload, REQUEST_DOC_UPLOAD);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, UploadActivity.this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_VIDEO_CAPTURE) {
            uri = data.getData();
            if (EasyPermissions.hasPermissions(UploadActivity.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
                pathToStoredVideo = getRealPathFromURIPath(uri, UploadActivity.this);
                Log.d("abhi", "Recorded Video Path ::" + pathToStoredVideo);
                videoName.setText(pathToStoredVideo);

                //1Store the video to your server
                uploadVideoToServer(pathToStoredVideo);

            } else {
                EasyPermissions.requestPermissions(UploadActivity.this,
                        this.getString(R.string.read_file), READ_REQUEST_CODE,
                        Manifest.permission.READ_EXTERNAL_STORAGE);
            }
        }

        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_IMAGE_CAPTURE) {
            if (EasyPermissions.hasPermissions(UploadActivity.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
                Bitmap photo = (Bitmap) data.getExtras().get("data");

                // CALL THIS METHOD TO GET THE URI FROM THE BITMAP
                Uri tempUri = getImageUri(getApplicationContext(), photo);

                // CALL THIS METHOD TO GET THE ACTUAL PATH
                File finalFile = new File(getRealPathFromURI(tempUri));
                imageName.setText(finalFile.getPath());

                uploadImageToServer(finalFile.getPath());
                System.out.println(tempUri);

            } else {
                EasyPermissions.requestPermissions(UploadActivity.this,
                        this.getString(R.string.read_file), READ_REQUEST_CODE, Manifest.permission.READ_EXTERNAL_STORAGE);
            }
        }

        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_DOC_UPLOAD) {
            uri = data.getData();
            if (EasyPermissions.hasPermissions(UploadActivity.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
                docName.setText(uri.getPath());
                Log.d("abhi", uri.getPath());
                uploadDocToServer(uri.getPath());

            } else {
                EasyPermissions.requestPermissions(UploadActivity.this,
                        this.getString(R.string.read_file), READ_REQUEST_CODE,
                        Manifest.permission.READ_EXTERNAL_STORAGE);
            }
        }
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage,
                "Title", null);
        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }


//    private String getFileDestinationPath() {
//        String generatedFilename = String.valueOf(System.currentTimeMillis());
//        String filePathEnvironment = Environment.
//                getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).
//                getAbsolutePath();
//        File directoryFolder = new File(filePathEnvironment + "/video/");
//        if (!directoryFolder.exists()) {
//            directoryFolder.mkdir();
//        }
//        Log.d("abhi", "Full path " + filePathEnvironment + "/video/" + generatedFilename + ".mp4");
//        return filePathEnvironment + "/video/" + generatedFilename + ".mp4";
//    }


    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        if (uri != null) {
            if (EasyPermissions.hasPermissions(UploadActivity.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
                pathToStoredVideo = getRealPathFromURIPath(uri, UploadActivity.this);
                Log.d("abhi", "Recorded Video Path : " + pathToStoredVideo);
                videoName.setText(pathToStoredVideo);
            }
        }
    }

    private String getRealPathFromURIPath(Uri contentURI, Activity activity) {
        Cursor cursor = activity.getContentResolver().query(contentURI, null,
                null, null, null);
        if (cursor == null) {
            return contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(idx);
        }
    }

    private void uploadVideoToServer(String pathToVideoFile) {
        File videoFile = new File(pathToVideoFile);

        RequestBody videoBody = RequestBody.create(MediaType.parse("video/*"), videoFile);
        MultipartBody.Part vFile = MultipartBody.Part.createFormData("video", videoFile.getName(), videoBody);

        UploadApi vInterface = retrofit.create(UploadApi.class);
        Call<Data> serverCom = vInterface.uploadVideoToServer(accessToken, vFile);
        serverCom.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                Data result = response.body();
                if (result.isSuccess()) {
//                    Toast.makeText(UploadActivity.this, "Result " + result.isSuccess(),
//                            Toast.LENGTH_LONG).show();
                    showMessage(result.getMessage());
                    Log.d(TAG, "Result " + result.isSuccess());
                }
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                showMessage(getString(R.string.ConnectionError));
                Log.d(TAG, "Error message " + t.getMessage());
            }
        });
    }


    private void uploadImageToServer(String pathToImage) {

        File imageFile = new File(pathToImage);
        RequestBody imageBody = RequestBody.create(MediaType.parse("image/*"), imageFile);
        MultipartBody.Part iFile = MultipartBody.Part.createFormData("image", imageFile.getName(), imageBody);

        UploadApi vInterface = retrofit.create(UploadApi.class);
        Call<Data> serverCom = vInterface.uploadImageToServer(accessToken, iFile);
        serverCom.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                Data result = response.body();
                if (result.isSuccess()) {
//                    Toast.makeText(UploadActivity.this, "Result " + result.isSuccess(),
//                            Toast.LENGTH_LONG).show();
                    Log.d(TAG, "Result " + result.isSuccess());
                    showMessage(result.getMessage());
                }
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                showMessage(getString(R.string.ConnectionError));
                Log.d(TAG, "Error message " + t.getMessage());
            }
        });
    }

    private void uploadDocToServer(String pathToDoc) {
        File docFile = new File(pathToDoc);
        RequestBody docBody = RequestBody.create(MediaType.parse("text/plain"), docFile);
        MultipartBody.Part dFile = MultipartBody.Part.createFormData("doc", docFile.getName(), docBody);

        UploadApi vInterface = retrofit.create(UploadApi.class);
        Call<Data> serverCom = vInterface.uploadDocToServer(accessToken, dFile);
        serverCom.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                Data result = response.body();
                if (result.isSuccess()) {
//                    Toast.makeText(UploadActivity.this, "Result " + result.isSuccess(),
//                            Toast.LENGTH_LONG).show();
                    Log.d(TAG, "Result " + result.isSuccess());
                    showMessage(result.getMessage());
                }
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                showMessage(getString(R.string.ConnectionError));
                Log.d(TAG, "Error message " + t.getMessage());
            }
        });
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        showMessage(getString(R.string.PermissionDenied));
        Log.d(TAG, "User has denied requested permission");
    }

    public void showMessage(String message) {
        Snackbar.make(findViewById(R.id.upload_rel_layout), message, Snackbar.LENGTH_LONG).setAction("Action", null).show();
    }
}