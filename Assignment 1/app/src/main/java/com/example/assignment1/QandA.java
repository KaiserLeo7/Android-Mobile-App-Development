package com.example.assignment1;

import android.os.Parcel;
import android.os.Parcelable;

public class QandA implements Parcelable {

    public int question;           //string question
    public boolean answer;         //true or false answer
    public int color;               //a color code

    QandA(int question, boolean answer, int color) {
        this.question = question;
        this.answer = answer;
        this.color = color;
    }

    protected QandA(Parcel in) {
        question = in.readInt();
        answer = in.readByte() != 0;
        color = in.readInt();
    }

    public static final Parcelable.Creator<QandA> CREATOR = new Parcelable.Creator<QandA>() {
        @Override
        public QandA createFromParcel(Parcel in) {
            return new QandA(in);
        }

        @Override
        public QandA[] newArray(int size) {
            return new QandA[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(question);
        dest.writeByte((byte) (answer ? 1 : 0));
        dest.writeInt(color);
    }
}
