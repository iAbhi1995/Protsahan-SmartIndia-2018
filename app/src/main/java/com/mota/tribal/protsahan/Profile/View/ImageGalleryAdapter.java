package com.mota.tribal.protsahan.Profile.View;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mota.tribal.protsahan.Helper.Urls;
import com.mota.tribal.protsahan.Login.Model.Data.UserInfo;
import com.mota.tribal.protsahan.Login.SQLiteHandler;
import com.mota.tribal.protsahan.Profile.Model.Data.VidImgDocData;
import com.mota.tribal.protsahan.Profile.Presenter.ProfilePresenter;
import com.mota.tribal.protsahan.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

public class ImageGalleryAdapter extends RecyclerView.Adapter<ImageGalleryAdapter.MyViewHolder> {
    private ArrayList<VidImgDocData.Obj> urls;
    private String profile_pic_url, tribal_name, type;
    private Context context;
    private ProfilePresenter presenter;
    private SQLiteHandler db;
    private UserInfo user;

    public ImageGalleryAdapter(ArrayList<VidImgDocData.Obj> urls,
                               String tribal_name, String profile_pic_url, String type, Context context, ProfilePresenter presenter) {
        this.urls = urls;
        this.type = type;
        this.tribal_name = tribal_name;
        this.profile_pic_url = profile_pic_url;
        this.context = context;
        this.presenter = presenter;
        db = new SQLiteHandler(context);
        user = db.getUser();
    }

    public static Bitmap retriveVideoFrameFromVideo(String videoPath) throws Throwable {
        Bitmap bitmap = null;
        MediaMetadataRetriever mediaMetadataRetriever = null;
        try {
            mediaMetadataRetriever = new MediaMetadataRetriever();
            if (Build.VERSION.SDK_INT >= 14)
                mediaMetadataRetriever.setDataSource(videoPath, new HashMap<String, String>());
            else
                mediaMetadataRetriever.setDataSource(videoPath);
            //   mediaMetadataRetriever.setDataSource(videoPath);
            bitmap = mediaMetadataRetriever.getFrameAtTime();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Throwable("Exception in retriveVideoFrameFromVideo(String videoPath)" + e.getMessage());

        } finally {
            if (mediaMetadataRetriever != null) {
                mediaMetadataRetriever.release();
            }
        }
        return bitmap;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.image_gallery_item, parent, false);
        return new ImageGalleryAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        Picasso.with(context).load(profile_pic_url).placeholder(R.drawable.mario_black).into(holder.profilePic);
        holder.imageName.setText(urls.get(position).getTitle());
        holder.tribalName.setText(tribal_name);
        Log.d("abhi", Urls.BASE_URL2 + urls.get(position).getUrl().substring(6));
        if (type.equals("image"))
            Picasso.with(context).load(Urls.BASE_URL2 + urls.get(position).getUrl().substring(6)).placeholder(R.drawable.mario_black).into(holder.image);

        if (type.equals("video")) {
            /*try {
                Bitmap bitmap = retriveVideoFrameFromVideo(Urls.BASE_URL2+urls.get(position).getUrl().substring(6));
                holder.image.setImageBitmap(bitmap);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }*/
            Picasso.with(context).load(Urls.BASE_URL2 + urls.get(position).getUrl().substring(6) + ".png").placeholder(R.drawable.mario_black).into(holder.image);
            holder.playButton.setVisibility(View.VISIBLE);
            holder.playButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, VideoViewActivity.class);
                    intent.putExtra("url", urls.get(position).getUrl());
                    context.startActivity(intent);
                }
            });
        }


        holder.deleteimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (type.equals("image"))
                    presenter.deleteImage(user.getToken(), user.getUsername(), urls.get(position).getUrl(), urls.get(position).getTitle());
                else if (type.equals("video"))
                    presenter.deleteVideo(user.getToken(), user.getUsername(), urls.get(position).getUrl(), urls.get(position).getTitle());

            }
        });
    }

    @Override
    public int getItemCount() {
        return urls.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView profilePic, image, deleteimage, playButton;
        TextView tribalName, imageName;

        public MyViewHolder(View itemView) {
            super(itemView);
            profilePic = itemView.findViewById(R.id.profile_img);
            image = itemView.findViewById(R.id.image);
            playButton = itemView.findViewById(R.id.play_button);

            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int width = displayMetrics.widthPixels;
            image.getLayoutParams().height = width;
            image.requestLayout();

            if (type.equals("video"))
                playButton.setVisibility(View.VISIBLE);

            tribalName = itemView.findViewById(R.id.name_of_tribal);
            imageName = itemView.findViewById(R.id.image_name);
            deleteimage = itemView.findViewById(R.id.delete_image);

        }
    }
}

