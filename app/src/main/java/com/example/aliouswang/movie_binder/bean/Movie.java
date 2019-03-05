package com.example.aliouswang.movie_binder.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {

    public int id;
    public String name;
    public String image;
    public int hot;

    public Movie() {}

    public Movie(int id, String name, String image, int hot) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.hot = hot;
    }

    protected Movie(Parcel in) {
        id = in.readInt();
        name = in.readString();
        image = in.readString();
        hot = in.readInt();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(image);
        dest.writeInt(hot);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Movie)) {
            return false;
        }
        Movie other = (Movie) obj;
        return this.id == other.id;
    }
}
