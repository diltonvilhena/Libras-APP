package com.example.libraskids.VideoPlay;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.libraskids.R;

import java.util.ArrayList;

public class AdapterVP extends RecyclerView.Adapter<AdapterVP.HolderVideo>{

    //context
    private Context context;
    //array list
    private ArrayList<ModelVideoPlay> videoPlayArrayList;

    //construtor
    public AdapterVP(Context context, ArrayList<ModelVideoPlay> videoPlayArrayList) {
        this.context = context;
        this.videoPlayArrayList = videoPlayArrayList;
    }

    @NonNull
    @Override
    public HolderVideo onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.row_videoplay,parent, false);
        return new HolderVideo(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderVideo holder, int position) {

        ModelVideoPlay modelVideoPlay = videoPlayArrayList.get(position);

        String title = modelVideoPlay.getTitle();
        String videoUrl = modelVideoPlay.getVideoUrl();

        holder.titleVP.setText(title);
        setVideoUrl(videoUrl, holder);

    }

    private void setVideoUrl(String videoUrl, HolderVideo holder) {

        //media controler
        MediaController mediaController = new MediaController(context);
        mediaController.setAnchorView(holder.videoVP);

        Uri videoUri = Uri.parse(videoUrl);
        holder.videoVP.setMediaController(mediaController);
        holder.videoVP.setVideoURI(videoUri);

        holder.videoVP.requestFocus();
        holder.videoVP.setOnPreparedListener(mp -> mp.start());

        holder.videoVP.setOnInfoListener((mp, what, extra) -> {

            switch (what){
                case MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START:{
                    return true;
                }
                case MediaPlayer.MEDIA_INFO_BUFFERING_START:{
                    return true;
                }
                case MediaPlayer.MEDIA_INFO_BUFFERING_END:{
                    return true;
                }
            }

            return false;
        });

        holder.videoVP.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.start(); //restart do video
            }
        });

    }

    @Override
    public int getItemCount() {

        return videoPlayArrayList.size();
    }

    class HolderVideo extends RecyclerView.ViewHolder{

        //views do row_videoplay.xml
        VideoView videoVP;
        TextView titleVP;

        public HolderVideo(@NonNull View itemView) {
            super(itemView);

            //views do row_videoplay.xml
            videoVP = itemView.findViewById(R.id.videoVP);
            titleVP = itemView.findViewById(R.id.titleVP);
        }
    }
}
