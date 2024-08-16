package com.example.multimediaproject;

import android.os.Parcelable;
import android.os.Parcel;


import androidx.annotation.NonNull;

public class TaylorSwift implements Parcelable{


    private String album, label, mostViewedSong, views, releaseDate;
    private int numOfSongs;
    private int rating;
    private int image;


    public TaylorSwift(String album, String label, String mostViewedSong, int rating, int numOfSongs, int image, String views, String releaseDate) {
        this.album = album;
        this.label = label;
        this.mostViewedSong = mostViewedSong;
        this.rating = rating;
        this.numOfSongs = numOfSongs;
        this.image = image;
        this.views = views;
        this.releaseDate = releaseDate;
    }

    public String getAlbum(){
        return album;
    }
    public String getLabel(){
        return label;
    }
    public String getMostViewedSong(){
        return mostViewedSong;
    }

    public int getRating(){
        return rating;
    }

    public int setRating(int ratings) {
        rating = ratings;
        return rating;
    }
    public int getNumOfSongs(){
        return numOfSongs;
    }
    public int getImage(){
        return image;
    }

    public String getViews() {
        return views;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public static final Creator<TaylorSwift> CREATOR = new Creator<TaylorSwift>() {
        @Override
        public TaylorSwift createFromParcel(Parcel in) {
            return new TaylorSwift(in);
        }

        @Override
        public TaylorSwift[] newArray(int size) {
            return new TaylorSwift[size];
        }
    };

    public TaylorSwift(Parcel in) {
        album = in.readString();
        label = in.readString();
        mostViewedSong = in.readString();
        rating = in.readInt();
        numOfSongs = in.readInt();
        image = in.readInt();
        views = in.readString();
        releaseDate = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(album);
        dest.writeString(label);
        dest.writeString(mostViewedSong);
        dest.writeInt(numOfSongs);
        dest.writeInt(rating);
        dest.writeInt(image);
        dest.writeString(views);
        dest.writeString(releaseDate);
    }


} //closes class