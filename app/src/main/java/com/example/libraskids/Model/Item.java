package com.example.libraskids.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Item implements Parcelable {

    private String imageUrl , videoUrl, title;

    public Item(){

    }

    public Item(String imageUrl, String videoUrl, String title) {
        this.imageUrl = imageUrl;
        this.videoUrl = videoUrl;
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int arg1) {
        // TODO Auto-generated method stub

        dest.writeString(title);
        dest.writeString(imageUrl);
        dest.writeString(videoUrl);
    }

    public Item(Parcel in) {

        title = in.readString();
        imageUrl = in.readString();
        videoUrl = in.readString();
    }

    public static final Parcelable.Creator<Item> CREATOR = new Parcelable.Creator<Item>() {
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };
}
