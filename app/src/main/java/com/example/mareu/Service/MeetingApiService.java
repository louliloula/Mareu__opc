package com.example.mareu.Service;

import com.example.mareu.Model.Meeting;

import java.text.ParseException;
import java.util.List;

public interface MeetingApiService {

    List<Meeting> getMeeting();

    void deleteMeeting (Meeting meeting);

    void createMeeting (Meeting meeting);

    List<Meeting> getMeetingFiltredByDate(String date) throws ParseException;

    List<Meeting> getMeetingFiltredByRoom(String room);
}
