package com.example.mareu.Model;

import java.util.Date;

public class Meeting {
    private int mIdMeeting;
    private int mImage;
    private String mRoomName;
    private String mSubject;
    private String mDate;
    private String mHour;
    private String mParticipants;

    public Meeting(int mIdMeeting, int mImage, String mRoomName, String mSubject, String mDate, String mHour, String mParticipants) {
        this.mIdMeeting = mIdMeeting;
        this.mImage = mImage;
        this.mRoomName = mRoomName;
        this.mSubject = mSubject;
        this.mDate = mDate;
        this.mHour = mHour;
        this.mParticipants = mParticipants;
    }

    public int getmIdMeeting() {
        return mIdMeeting;
    }

    public void setmIdMeeting(int mIdMeeting) {
        this.mIdMeeting = mIdMeeting;
    }

    public int getmImage() {
        return mImage;
    }

    public void setmImage(int mImage) {
        this.mImage = mImage;
    }

    public String getmRoomName() {
        return mRoomName;
    }

    public void setmRoomName(String mRoomName) {
        this.mRoomName = mRoomName;
    }

    public String getmSubject() {
        return mSubject;
    }

    public void setmSubject(String mSubject) {
        this.mSubject = mSubject;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public String getmHour() {
        return mHour;
    }

    public void setmHour(String mHour) {
        this.mHour = mHour;
    }

    public String getmParticipants() {
        return mParticipants;
    }

    public void setmParticipants(String mParticipants) {
        this.mParticipants = mParticipants;
    }


}

