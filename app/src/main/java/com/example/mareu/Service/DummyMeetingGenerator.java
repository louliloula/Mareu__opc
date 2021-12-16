package com.example.mareu.Service;

import com.example.mareu.Model.Meeting;
import com.example.mareu.R;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class DummyMeetingGenerator {

    public static List<Meeting> DUMMY_MEETING = Arrays.asList(
            new Meeting(0, R.drawable.ic_event_burgundy, "Paris", "Marketing", "30-11-2021", "7:00 AM", "farah@gmail.com,claire@yahoo.fr,clement@gmail.com"),
            new Meeting(1,R.drawable.ic_event_green,"Bordeaux","Financement","01-12-2021","8:15 AM","quentin@gmail.com,fred@gmail.com,danny@yahoo.fr"),
            new Meeting(2,R.drawable.ic_event_brown,"Lille","Vente","01-12-2021","9:30 AM","sandra@gmail.com,warda@yahoo.fr,quentin@gmail.com"),
            new Meeting(3,R.drawable.ic_event_blue,"Marrakech","Recrutement","02-12-2021","10:40 AM","quentin@gmail.com,fred@gmail.com,danny@yahoo.fr"),
            new Meeting(4,R.drawable.ic_event_burgundy,"Florence","Pub","02-12-2021","13:00 PM","farah@gmail.com,claire@yahoo.fr,clement@gmail.com"),
            new Meeting(5,R.drawable.ic_event_green,"Madrid","Service","05-12-2021","14:20 PM","sandra@gmail.com,warda@yahoo.fr,quentin@gmail.com"),
            new Meeting(6,R.drawable.ic_event_brown,"Marseille","Achat","05-12-2021","16:30 PM","quentin@gmail.com,fred@gmail.com,danny@yahoo.fr"),
            new Meeting(7,R.drawable.ic_event_blue,"Athenes","Application","07-12-2021","18:00 PM ","sandra@gmail.com,warda@yahoo.fr,quentin@gmail.com"),
            new Meeting(8,R.drawable.ic_event_burgundy,"Morbihan","Management","07-12-2021","15:00 PM","farah@gmail.com,claire@yahoo.fr,clement@gmail.com"),
            new Meeting(9,R.drawable.ic_event_green,"Amsterdam","Vente","08-12-2021","17:00 PM","quentin@gmail.com,fred@gmail.com,danny@yahoo.fr"));

    static List<Meeting> generateMeeting() {

        return new ArrayList<>(DUMMY_MEETING);
    }

}
