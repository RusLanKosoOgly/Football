package com.example.kursovai_final3.ui.models;

import android.os.Parcel;
import android.os.Parcelable;

public class MatchModel implements Parcelable {
    int leftTeamImageResId;
    int rightTeamImageResId;
    String title;
    String description;
    String price;


    public MatchModel(int leftTeamImageResId, int rightTeamImageResId, String title, String description, String price) {
        this.leftTeamImageResId = leftTeamImageResId;
        this.rightTeamImageResId = rightTeamImageResId;
        this.title = title;
        this.description = description;
        this.price = price;

    }

    protected MatchModel(Parcel in) {
        leftTeamImageResId = in.readInt();
        rightTeamImageResId = in.readInt();
        title = in.readString();
        description = in.readString();
        price = in.readString();

    }

    public static final Creator<MatchModel> CREATOR = new Creator<MatchModel>() {
        @Override
        public MatchModel createFromParcel(Parcel in) {
            return new MatchModel(in);
        }

        @Override
        public MatchModel[] newArray(int size) {
            return new MatchModel[size];
        }
    };

    public int getLeftTeamImageResId() {
        return leftTeamImageResId;
    }

    public int getRightTeamImageResId() {
        return rightTeamImageResId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(leftTeamImageResId);
        dest.writeInt(rightTeamImageResId);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(price);

    }
}
