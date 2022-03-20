package com.example.libraskids.VideoPlay;

public class ModelVideoPlay {
    String title, videoUrl;

    //construtor
    public ModelVideoPlay() {
        // firebase solicita um construtor vazio
    }

    public ModelVideoPlay(String title, String videoUrl) {
        this.title = title;
        this.videoUrl = videoUrl;
    }

    // getter e setter


    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public String getVideoUrl() {

        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {

        this.videoUrl = videoUrl;
    }
}
