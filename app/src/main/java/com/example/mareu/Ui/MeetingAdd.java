package com.example.mareu.Ui;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mareu.DI;
import com.example.mareu.Model.Meeting;
import com.example.mareu.R;
import com.example.mareu.Service.MeetingApiService;
import com.example.mareu.Utils;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class MeetingAdd extends AppCompatActivity {


    private MeetingApiService mApiService = DI.getMeetingApiService();
    private int mDate;
    private int mMonth;
    private int mYear;

    TextInputEditText subjectContent;
    TextInputEditText placeContent;
    TextInputEditText dateContent;
    TextInputEditText hourContent;
    TextInputEditText participantsContent;
    Button mSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_meeting);

        placeContent = findViewById(R.id.placeContent);
        subjectContent = findViewById(R.id.subjectContent);
        dateContent = findViewById(R.id.dateContent);
        hourContent = findViewById(R.id.hourContent);
        participantsContent = findViewById(R.id.participantsContent);
        mSave = findViewById(R.id.save);

        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateMail()) {
                    Meeting meeting = new Meeting(4, R.drawable.ic_event_burgundy,
                            placeContent.getText().toString(), subjectContent.getText().toString(),
                            dateContent.getText().toString(), hourContent.getText().toString(),
                            participantsContent.getText().toString());
                    mApiService.createMeeting(meeting);
                    List meetings = mApiService.getMeeting();
                    Log.i("tryhard","meetings size : "+meetings.size());
                    Toast.makeText(getApplicationContext(), "Meeting save with success", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                } else
                    Toast.makeText(getApplicationContext(), "Format invalide", Toast.LENGTH_SHORT).show();
                


            }

        });
        dateContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar cal = Calendar.getInstance();

                mDate = cal.get(Calendar.DAY_OF_MONTH);
                mMonth = cal.get(Calendar.MONTH);
                mYear = cal.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(MeetingAdd.this,
                        AlertDialog.THEME_HOLO_LIGHT,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                Calendar c = Calendar.getInstance();
                                c.set(Calendar.YEAR, year);
                                c.set(Calendar.MONTH, month);
                                c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                                c.setTimeZone(TimeZone.getDefault());
                                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                                String time = format.format(c.getTime());
                                dateContent.setText(time);
                            }
                        }, mDate, mMonth, mYear);
                datePickerDialog.getDatePicker().setMinDate(Calendar.getInstance().getTimeInMillis());

                datePickerDialog.show();

            }
        });


        hourContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int min = calendar.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(MeetingAdd.this,
                        AlertDialog.THEME_HOLO_LIGHT,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                Calendar c = Calendar.getInstance();
                                c.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                c.set(Calendar.MINUTE, minute);
                                c.setTimeZone(TimeZone.getDefault());
                                SimpleDateFormat format = new SimpleDateFormat("k:mm a");
                                String time = format.format(c.getTime());
                                hourContent.setText(time);

                            }
                        }, hour, min, true
                );
                timePickerDialog.show();


            }

        });
    }

    private boolean validateMail() {

        String mail = participantsContent.getText().toString();

        ArrayList<String> mailsArray = Utils.parseStringToList(mail);


        if (mailsArray.size() < 3)
            return false;


        for (int j = 0; j < mailsArray.size(); j++) {
            if (mailsArray.get(j).isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(mailsArray.get(j)).matches())
                return false;
        }

        return true;
    }


}









