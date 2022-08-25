package com.example.noteapp2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.noteapp2.Adapter.NoteAdapter;
import com.example.noteapp2.database.NoteDatabase;

import java.util.ArrayList;
import java.util.List;

import yuku.ambilwarna.AmbilWarnaDialog;

public class AddNote extends AppCompatActivity {
    EditText ed_tenNote,ed_ngay,ed_ghiChu;
    Button btn_save;
    Button btnColor;
    Note note;
    NoteAdapter noteAdapter;
    List<Note> mlistNote;
    int initialColor = Color.RED;
    SharedPreferences sp;
    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        sp = getSharedPreferences("sp",MODE_PRIVATE);
        linearLayout = findViewById(R.id.add_linear);

        ed_tenNote = findViewById(R.id.ed_tenNote);
        ed_ngay = findViewById(R.id.ed_ngay);
        ed_ghiChu = findViewById(R.id.ed_ghiChu);
        btn_save = findViewById(R.id.btn_save);
        btnColor = findViewById(R.id.btnColor);

        noteAdapter = new NoteAdapter();
        mlistNote = new ArrayList<>();
        noteAdapter.setData(mlistNote);

        btnColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AmbilWarnaDialog dialog = new AmbilWarnaDialog(AddNote.this, initialColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
                    @Override
                    public void onOk(AmbilWarnaDialog dialog, int color) {
                        // color is the color selected by the user.
                        linearLayout.setBackgroundColor(color);
                        initialColor = color;
                        sp.edit().putString("color", String.valueOf(color)).commit();
                    }

                    @Override
                    public void onCancel(AmbilWarnaDialog dialog) {
                        // cancel was selected by the user
                    }
                });
                dialog.show();
            }
        });


        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNote();
            }
        });


    }
    public void addNote(){
        String strtenNote = ed_tenNote.getText().toString().trim();
        String strngay = ed_ngay.getText().toString().trim();
        String strghiChu = ed_ghiChu.getText().toString().trim();
        String srtcolor ;
        if(TextUtils.isEmpty(strtenNote) || TextUtils.isEmpty(strngay) || TextUtils.isEmpty(strghiChu) ){
            return;
        }
        Note note = new Note(strtenNote,strngay,strghiChu,initialColor);
        NoteDatabase.getInstance(this).noteDAO().insert(note);
        Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show();

        ed_tenNote.setText("");
        ed_ngay.setText("");
        ed_ghiChu.setText("");


//        mlistNote = NoteDatabase.getInstance(this).noteDAO().getAll();
//        noteAdapter.setData(mlistNote);
        Intent intent = new Intent(AddNote.this,MainActivity.class);
        startActivity(intent);
    }

    public boolean isNoteExist(Note note){
        List<Note> list = NoteDatabase.getInstance(this).noteDAO().check(note.getNgay());
        return list != null && !list.isEmpty();
    }


}