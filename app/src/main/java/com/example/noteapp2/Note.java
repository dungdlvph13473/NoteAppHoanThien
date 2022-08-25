package com.example.noteapp2;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "AppNote")
public class Note  {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String tenNote;
    private String ngay;
    private String ghiChu;
    private int color;

    public Note() {
    }

    public Note(String tenNote, String ngay, String ghiChu, int color) {
        this.tenNote = tenNote;
        this.ngay = ngay;
        this.ghiChu = ghiChu;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenNote() {
        return tenNote;
    }

    public void setTenNote(String tenNote) {
        this.tenNote = tenNote;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}