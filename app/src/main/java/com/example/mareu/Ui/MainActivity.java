package com.example.mareu.Ui;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mareu.DI;
import com.example.mareu.Model.Meeting;
import com.example.mareu.R;
import com.example.mareu.Service.MeetingApiService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ItemAdapter mItemAdapter;
    private RecyclerView mRecyclerview;
    private RecyclerView.LayoutManager mLayoutManager;
    private MeetingApiService mApiService = DI.getMeetingApiService();
    private List<Meeting> mMeetingList;



    FloatingActionButton fab;


    @Override
    protected void onResume() {
        super.onResume();
        initList();
        initRecyclerView();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initList();
        initRecyclerView();

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent monintent = new Intent(v.getContext(), MeetingAdd.class);
                v.getContext().startActivity(monintent);
            }
        });
    }

    private void initRecyclerView(){

        mRecyclerview = findViewById(R.id.rvMeeting);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerview.setLayoutManager(mLayoutManager);
        mItemAdapter = new ItemAdapter(mMeetingList);
        mRecyclerview.setAdapter(mItemAdapter);
        mItemAdapter.setOnItemClicklistener(new ItemAdapter.OnItemClickListener() {

            @Override
            public void OnItemDelete(int position) {
                removeItem(position);

            }
        });
    }

    public void removeItem(int position) {
        mMeetingList.remove(position);
        mItemAdapter.notifyItemRemoved(position);


    }


    private void initList() {
        mMeetingList = new ArrayList<>(mApiService.getMeeting());



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.filter_date:
                dateDialog();
                return true;
            case R.id.filter_room:
                filterRoom();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
        
    }

    private void filterRoom() {
      AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this);
        View view = getLayoutInflater().inflate(R.layout.custom_dialog, null);
        builder.setView(view);


        final AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);

        EditText txt_input = view.findViewById(R.id.txt_input);
        Button btn_ok =view.findViewById(R.id.btn_ok);

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMeetingList = mApiService.getMeetingFiltredByRoom(txt_input.getText().toString());
                initRecyclerView();
                alertDialog.dismiss();

            }
        });
        alertDialog.show();


    }



    private void dateDialog() {
        int selectedYear = 2021;
        int selectedMonth = 12;
        int selectedDayOfMonth = 7;


        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                Calendar cal = Calendar.getInstance();
                cal.set(i, i1, i2);
                mMeetingList.clear();
                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                String time = format.format(cal.getTime());
                try {
                    mMeetingList = mApiService.getMeetingFiltredByDate(time);
                    initRecyclerView();
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }

        };


        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                dateSetListener, selectedYear, selectedMonth, selectedDayOfMonth);


        datePickerDialog.show();
    }

}
