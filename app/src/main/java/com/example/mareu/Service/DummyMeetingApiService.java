package com.example.mareu.Service;

import com.example.mareu.Model.Meeting;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;



public class DummyMeetingApiService implements MeetingApiService {

    private List<Meeting> meetings = DummyMeetingGenerator.generateMeeting();


    /**
     * {@inheritDoc}
     *
     * @return
     */
    @Override
    public List<Meeting> getMeeting() {
        return meetings;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteMeeting(Meeting meeting) {
        meetings.remove(meeting);
    }

    /**
     * {@inheritDoc}
     *
     * @param meeting
     */
    @Override
    public void createMeeting(Meeting meeting) {
        meetings.add(meeting);
    }

    @Override
    public List<Meeting> getMeetingFiltredByDate(String date) throws ParseException {
        // Creation d'une nouvelle ArrayList vide
        ArrayList<Meeting> result = new ArrayList<>();

        //recuperation d'un calendar
        Calendar cal1 = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        cal1.setTime(sdf.parse(date));
        //associé a la date que l'utilisateur va rentrer
        //cal1.setTime(date);
        //boucle qui itere sur tous les meeting
        for (int i = 0; i < meetings.size(); i++) {
            //second calendrier associé aux meeting courant
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(sdf.parse(meetings.get(i).getmDate()));
            //verifier si le resultat de la recherche est le meme
            boolean sameDay = cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR) &&
                    cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                    cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);
            if (sameDay)
                result.add(meetings.get(i));
        }

        return result;
    }


    @Override
    public List<Meeting> getMeetingFiltredByRoom(String room) {
        // trouver en java comment filtrer une liste selon des critères
        // Creation d'une nouvelle ArrayList vide
        ArrayList<Meeting> result = new ArrayList<>();


        //boucle qui itere sur tous les meeting
        for (int i = 0; i < meetings.size(); i++) {

            //verifier si le resultat de la recherche est le meme
            String meetingNameAtIndex = meetings.get(i).getmRoomName();

            boolean sameRoom = meetingNameAtIndex.equals(room);
            if (sameRoom)
                result.add(meetings.get(i));
        }

        return result;

    }

}





