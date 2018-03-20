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
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mota.tribal.protsahan.Helper.Urls;
import com.mota.tribal.protsahan.Login.SQLiteHandler;
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
    private String pathToStoredVideo = "", pathToStoredImage = "", pathToStoredDoc = "", accessToken, username;
    private TextView videoPath, imagePath, docPath;

    private EditText videoName, imageName, docName;
    private Button captureVideoButton, captureImageButton, docButton, docUpload, videoUpload, imageUpload;
    private int REQUEST_DOC_UPLOAD = 50;
    private Retrofit retrofit;
    private SQLiteHandler db;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        db = new SQLiteHandler(this);
        accessToken = db.getUser().getToken();
        username = db.getUser().getUsername();


        videoPath = findViewById(R.id.video_name);
        imagePath = findViewById(R.id.image_name);
        docPath = findViewById(R.id.document_name);

        videoName = findViewById(R.id.video_des);
        imageName = findViewById(R.id.image_des);
        docName = findViewById(R.id.doc_des);

        captureVideoButton = findViewById(R.id.capture_video);
        captureImageButton = findViewById(R.id.capture_image);
        docButton = findViewById(R.id.doc_select);

        videoUpload = findViewById(R.id.video_upload);
        imageUpload = findViewById(R.id.image_upload);
        docUpload = findViewById(R.id.doc_upload);

        progressBar = findViewById(R.id.progress_bar);

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

        captureImageButton.setOnClickListener(new View.OnClickListener() {
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

        videoUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadVideoToServer(pathToStoredVideo);
            }
        });

        imageUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadImageToServer(pathToStoredImage);
            }
        });
        docUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadDocToServer(pathToStoredDoc);
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
                videoPath.setText(pathToStoredVideo);

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
                imagePath.setText(finalFile.getPath());
                pathToStoredImage = finalFile.getPath();
//                uploadImageToServer(finalFile.getPath());
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
                docPath.setText(uri.getPath());
                Log.d("abhi", uri.getPath());
                pathToStoredDoc = uri.getPath();
//                uploadDocToServer(uri.getPath());
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

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        if (uri != null) {
            if (EasyPermissions.hasPermissions(UploadActivity.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
                pathToStoredVideo = getRealPathFromURIPath(uri, UploadActivity.this);
                Log.d("abhi", "Recorded Video Path : " + pathToStoredVideo);
                videoPath.setText(pathToStoredVideo);
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
        if (pathToVideoFile.equals("")) {
            showMessage("{Please Select a video to upload!");
            return;
        }
        File videoFile = new File(pathToVideoFile);
        RequestBody videoBody = RequestBody.create(MediaType.parse("video/*"), videoFile);
        MultipartBody.Part vFile = MultipartBody.Part.createFormData("videos", videoFile.getName(), videoBody);

        UploadApi vInterface = retrofit.create(UploadApi.class);
        String videoname = videoName.getText().toString();
        if (videoname.equals(""))
            showMessage("Enter a Video Name relating to the content of video");
        else {
            showProgressBar(true);
            Call<Data> serverCom = vInterface.uploadVideoToServer(accessToken, username, videoname, vFile);
            serverCom.enqueue(new Callback<Data>() {
                @Override
                public void onResponse(Call<Data> call, Response<Data> response) {
                    Data result = response.body();
                    showProgressBar(false);
                    if (result.isSuccess()) {
                        showMessage(result.getMessage());
                        Log.d(TAG, "Result " + result.isSuccess());
                    }
                }

                @Override
                public void onFailure(Call<Data> call, Throwable t) {
                    showProgressBar(false);
                    showMessage(getString(R.string.ConnectionError));
                    Log.d(TAG, "Error message " + t.getMessage());
                }
            });
        }
    }


    private void uploadImageToServer(String pathToImage) {
        if (pathToImage.equals("")) {
            showMessage("{Please Select a image to upload!");
            return;
        }
        File imageFile = new File(pathToImage);
        RequestBody imageBody = RequestBody.create(MediaType.parse("image/*"), imageFile);
        MultipartBody.Part iFile = MultipartBody.Part.createFormData("images", imageFile.getName(), imageBody);
        UploadApi vInterface = retrofit.create(UploadApi.class);
        String imagename = imageName.getText().toString();
        if (imagename.equals(""))
            showMessage("Enter a image name relating to the content of image");
        else {
            showProgressBar(true);
            Call<Data> serverCom = vInterface.uploadImageToServer(accessToken, username, imagename, iFile);
            serverCom.enqueue(new Callback<Data>() {
                @Override
                public void onResponse(Call<Data> call, Response<Data> response) {
                    Data result = response.body();
                    showProgressBar(false);
                    if (result.isSuccess()) {
                        Log.d(TAG, "Result " + result.isSuccess());
                        showMessage(result.getMessage());
                    }
                }

                @Override
                public void onFailure(Call<Data> call, Throwable t) {
                    showProgressBar(false);
                    showMessage(getString(R.string.ConnectionError));
                    Log.d(TAG, "Error message " + t.getMessage());
                }
            });
        }
    }

    private void uploadDocToServer(String pathToDoc) {
        if (pathToDoc.equals("")) {
            showMessage("{Please Select a document to upload!");
            return;
        }
        File docFile = new File(pathToDoc);
        RequestBody docBody = RequestBody.create(MediaType.parse("text/plain"), docFile);
        MultipartBody.Part dFile = MultipartBody.Part.createFormData("doc", docFile.getName(), docBody);

        UploadApi vInterface = retrofit.create(UploadApi.class);
        String docname = docName.getText().toString();
        if (docname.equals(""))
            showMessage("Enter a Document Name relating to the content of document");
        else {
            showProgressBar(true);
            Call<Data> serverCom = vInterface.uploadDocToServer(accessToken, username, docname, dFile);
            serverCom.enqueue(new Callback<Data>() {
                @Override
                public void onResponse(Call<Data> call, Response<Data> response) {
                    showProgressBar(false);
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
                    showProgressBar(false);
                    showMessage(getString(R.string.ConnectionError));
                    Log.d(TAG, "Error message " + t.getMessage());
                }
            });
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        showMessage(getString(R.string.PermissionDenied));
        Log.d(TAG, "User has denied requested permission");
    }

    public void showProgressBar(boolean b) {
        if (b)
            progressBar.setVisibility(View.VISIBLE);
        else
            progressBar.setVisibility(View.GONE);
    }


    public void showMessage(String message) {
        Snackbar.make(findViewById(R.id.upload_rel_layout), message, Snackbar.LENGTH_LONG).setAction("Action", null).show();
    }
}