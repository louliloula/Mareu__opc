package com.example.mareu.Ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mareu.Model.Meeting;
import com.example.mareu.R;

import java.text.SimpleDateFormat;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private List<Meeting> mMeetings;
    //AJOUT SUPPRESSION MEETING
    private OnItemClickListener mListener;


    public ItemAdapter(List<Meeting> meetings) {
        this.mMeetings = meetings;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_meeting, parent, false);
        //return new ViewHolder(view);
        //AJOUT SUPPRESSION MEETING
        ViewHolder holder = new ViewHolder(view, mListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.displayMeeting(mMeetings.get(position));


    }

    @Override
    public int getItemCount() {
        return mMeetings.size();
    }

    //AJOUT SUPPRESSION ITEM
    public interface OnItemClickListener {
        void OnItemDelete(int position);

    }

    public void setOnItemClicklistener(OnItemClickListener listener) {
        mListener = listener;

    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView mImageItem;
        public TextView mTextMeeting;
        public TextView mTextMails;
        public ImageView mDeleteMeeting;

        public ViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            mImageItem = itemView.findViewById(R.id.imageItem);
            mTextMeeting = itemView.findViewById(R.id.textMeeting);
            mTextMails = itemView.findViewById(R.id.textMails);
            mDeleteMeeting = itemView.findViewById(R.id.deleteMeeting);
            mDeleteMeeting.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getBindingAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.OnItemDelete(position);
                        }

                    }

                }
            });


        }

        public void displayMeeting(Meeting meeting) {

            mImageItem.setImageResource(meeting.getmImage());
            mTextMeeting.setText(meeting.getmRoomName() + " - " + meeting.getmHour() + " - " +
                    meeting.getmSubject() + "\n" + meeting.getmDate());
            mTextMails.setText(meeting.getmParticipants());

        }


    }
}