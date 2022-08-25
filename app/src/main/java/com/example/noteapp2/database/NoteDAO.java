package com.example.noteapp2.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.noteapp2.Note;

import java.util.List;

@Dao
public interface NoteDAO {

    @Insert
    void insert(Note note);

    @Query("SELECT * FROM AppNote")
    List<Note> getAll();

    @Query("SELECT * FROM AppNote WHERE ngay= :ngay")
    List<Note> check(String ngay);

    @Query("SELECT * FROM AppNote WHERE ngay LIKE '%' || :date || '%'")
    List<Note> search(String date );
}
