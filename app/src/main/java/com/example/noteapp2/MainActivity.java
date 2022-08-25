package com.example.noteapp2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;

import com.example.noteapp2.Adapter.NoteAdapter;
import com.example.noteapp2.database.NoteDatabase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton floatingActionButton;
    RecyclerView ryc_home;
    NoteAdapter noteAdapter;
    CalendarView calendarView;
    TextView tv_calendar;
    List<Note> mlistNote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calendarView = findViewById(R.id.calendarView);
        tv_calendar = findViewById(R.id.tv_calendar);
        noteAdapter = new NoteAdapter();
        noteAdapter.setData(NoteDatabase.getInstance(this).noteDAO().getAll());


        floatingActionButton = findViewById(R.id.floatingbtn);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddNote.class);
                startActivity(intent);
            }
        });

        ryc_home = findViewById(R.id.ryc_home);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        ryc_home.setLayoutManager(linearLayoutManager);
        ryc_home.setAdapter(noteAdapter);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                String date = (i1+1) + "/" + i2 + "/" + i;
                tv_calendar.setText(date);
                mlistNote = new ArrayList<>();
                mlistNote = NoteDatabase.getInstance(MainActivity.this).noteDAO().search(date);
                noteAdapter.setData(mlistNote);

            }
        });
    }

}