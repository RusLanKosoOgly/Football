package com.example.kursovai_final3.ui.models;

import android.os.Parcel;
import android.os.Parcelable;

public class ScoreModel implements Parcelable {
     int leftTeamImageResId;
     int rightTeamImageResId;
     String score;
     String description;
     String extraInfo;
    String matchResId;

    public ScoreModel(int leftTeamImageResId, int rightTeamImageResId, String score, String description, String extraInfo, String matchResId) {
        this.leftTeamImageResId = leftTeamImageResId;
        this.rightTeamImageResId = rightTeamImageResId;
        this.score = score;
        this.description = description;
        this.extraInfo = extraInfo;
        this.matchResId = matchResId;
    }

    protected ScoreModel(Parcel in) {
        leftTeamImageResId = in.readInt();
        rightTeamImageResId = in.readInt();
        score = in.readString();
        description = in.readString();
        extraInfo = in.readString();
        matchResId = in.readString();
    }

    public static final Creator<ScoreModel> CREATOR = new Creator<ScoreModel>() {
        @Override
        public ScoreModel createFromParcel(Parcel in) {
            return new ScoreModel(in);
        }

        @Override
        public ScoreModel[] newArray(int size) {
            return new ScoreModel[size];
        }
    };

    public int getLeftTeamImageResId() {
        return leftTeamImageResId;
    }

    public int getRightTeamImageResId() {
        return rightTeamImageResId;
    }

    public String getScore() {
        return score;
    }

    public String getDescription() {
        return description;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public String getMatchResId() {
        return matchResId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(leftTeamImageResId);
        dest.writeInt(rightTeamImageResId);
        dest.writeString(score);
        dest.writeString(description);
        dest.writeString(extraInfo);
        dest.writeString(matchResId);
    }
}
