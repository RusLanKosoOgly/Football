package com.example.kursovai_final3.ui.models;

import android.os.Parcel;
import android.os.Parcelable;

public class IgrokModel implements Parcelable {
    int StranaImageResId;
    int KlubImageResId;
    int IgrokImageResId;
    String Imya;
    String Age;
    String CarearGame;
    String CarearGoal;
    String CarearAsist;
    String SeasonAsist;
    String SeasonGoal;
    String SeasonGame;

    public IgrokModel(int StranaImageResId, int KlubImageResId, int IgrokImageResId, String Imya, String Age, String CarearGame, String CarearGoal, String CarearAsist, String SeasonAsist, String SeasonGoal, String SeasonGame) {
        this.IgrokImageResId = IgrokImageResId;
        this.StranaImageResId = StranaImageResId;
        this.KlubImageResId = KlubImageResId;
        this.Imya = Imya;
        this.Age = Age;
        this.CarearGame = CarearGame;
        this.CarearGoal = CarearGoal;
        this.CarearAsist = CarearAsist;
        this.SeasonAsist = SeasonAsist;
        this.SeasonGoal = SeasonGoal;
        this.SeasonGame = SeasonGame;
    }

    protected IgrokModel(Parcel in) {
        IgrokImageResId = in.readInt();
        StranaImageResId = in.readInt();
        KlubImageResId = in.readInt();
        Imya = in.readString();
        Age = in.readString();
        CarearGame = in.readString();
        CarearGoal = in.readString();
        CarearAsist = in.readString();
        SeasonAsist = in.readString();
        SeasonGoal = in.readString();
        SeasonGame = in.readString();
    }

    public static final Creator<IgrokModel> CREATOR = new Creator<IgrokModel>() {
        @Override
        public IgrokModel createFromParcel(Parcel in) {
            return new IgrokModel(in);
        }

        @Override
        public IgrokModel[] newArray(int size) {
            return new IgrokModel[size];
        }
    };

    public int getIgrokImageResId() {
        return IgrokImageResId;
    }

    public int getStranaImageResId() {
        return StranaImageResId;
    }

    public int getKlubImageResId() {
        return KlubImageResId;
    }

    public String getImya() {
        return Imya;
    }

    public String getAge() {
        return Age;
    }

    public String getCarearGame() {
        return CarearGame;
    }

    public String getCarearGoal() {
        return CarearGoal;
    }

    public String getCarearAsist() {
        return CarearAsist;
    }

    public String getSeasonAsist() {
        return SeasonAsist;
    }

    public String getSeasonGoal() {
        return SeasonGoal;
    }

    public String getSeasonGame() {
        return SeasonGame;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(IgrokImageResId);
        dest.writeInt(StranaImageResId);
        dest.writeInt(KlubImageResId);
        dest.writeString(Imya);
        dest.writeString(Age);
        dest.writeString(CarearGame);
        dest.writeString(CarearGoal);
        dest.writeString(CarearAsist);
        dest.writeString(SeasonAsist);
        dest.writeString(SeasonGoal);
        dest.writeString(SeasonGame);
    }
}
