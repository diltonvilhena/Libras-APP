package com.example.libraskids.Adapter;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.libraskids.Model.Item;
import com.example.libraskids.R;

import java.util.ArrayList;

public class AdapterDetails extends RecyclerView.Adapter<AdapterDetails.HolderVideo> {

    private Context context;
    private final ArrayList<Item> videoArrayList;

    public AdapterDetails(Context context, ArrayList<Item> videoArrayList) {
        this.context = context;
        this.videoArrayList = videoArrayList;
    }

    @NonNull
    @Override
    public HolderVideo onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_videos,parent,false);
        return new HolderVideo(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderVideo holder, int position) {

        //getData
        Item item = videoArrayList.get(position);

        String title = item.getTitle();
        String image = item.getImageUrl();
        String videoUrl = item.getVideoUrl();

        //setData
        holder.titlevideo.setText(title);
        Glide.with(holder.imageView.getContext()).load(item.getImageUrl()).into(holder.imageView);
        setVideoUrl(videoUrl,holder);

    }

    public void setVideoUrl(String url, HolderVideo holder) {

        //controlador de midia para o vídeo
        MediaController mediaController = new MediaController(context);
        mediaController.setAnchorView(holder.videoView);  //coloquei para o view

        Uri videoUri = Uri.parse(url);
        holder.videoView.setMediaController(mediaController);
        holder.videoView.setVideoURI(videoUri);

        holder.videoView.requestFocus();

        //video is ready to play
        holder.videoView.setOnPreparedListener(MediaPlayer::start);

        holder.videoView.setOnInfoListener((mp, what, extra) -> {
            //to check if buffering, rendering etc
            switch (what){
                case MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START:{
                    //rendering started

                    return true;
                }
                case MediaPlayer.MEDIA_INFO_BUFFERING_START:{
                    //buffering started

                    return true;
                }
                case    MediaPlayer.MEDIA_INFO_BUFFERING_END:{
                    //buffering end
                    return true;
                }
            }
            return false;
        });

        //restart video if completed
        holder.videoView.setOnCompletionListener(MediaPlayer::start);
    }

    @Override
    public int getItemCount() {
        return videoArrayList.size();
    }

    //criação dos itens dentro do Item_videos.xml
    public class HolderVideo extends RecyclerView.ViewHolder{
        VideoView videoView;
        TextView titlevideo;
        ImageView imageView;

        public HolderVideo(@NonNull View itemView) {
            super(itemView);
            videoView = itemView.findViewById(R.id.videoview);
            imageView = itemView.findViewById(R.id.imageview);
            titlevideo = itemView.findViewById(R.id.titlevideo);
        }
    }
}

