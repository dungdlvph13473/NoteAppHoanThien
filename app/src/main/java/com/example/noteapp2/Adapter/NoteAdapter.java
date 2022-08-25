package com.example.noteapp2.Adapter;


import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noteapp2.Note;
import com.example.noteapp2.R;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {
    List<Note> noteList;
    public void setData(List<Note> list){
        this.noteList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list, parent , false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = noteList.get(position);
        if(note == null){
            return;
        }

        holder.tvtenNote.setText(note.getTenNote());
        holder.tvngay.setText(note.getNgay());
        holder.tvghiChu.setText(note.getGhiChu());
        holder.itemView.setBackgroundColor(note.getColor());

    }

    @Override
    public int getItemCount() {
        if(noteList != null){
            return noteList.size();
        }
        return 0;
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder{
        TextView tvtenNote,tvngay,tvghiChu;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);

            tvtenNote = itemView.findViewById(R.id.tv_tenNote);
            tvngay = itemView.findViewById(R.id.tv_ngay);
            tvghiChu = itemView.findViewById(R.id.tv_ghiChu);

        }
    }
}
