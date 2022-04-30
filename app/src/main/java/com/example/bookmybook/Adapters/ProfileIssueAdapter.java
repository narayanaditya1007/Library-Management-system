package com.example.bookmybook.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookmybook.Models.BookModel;
import com.example.bookmybook.R;

import java.util.Vector;

public class ProfileIssueAdapter extends RecyclerView.Adapter<ProfileIssueAdapter.ViewHolder> {

    Vector<BookModel> list;
    Context context;
    @NonNull
    @Override
    public ProfileIssueAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.profile_issue_sample,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileIssueAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView return_btn,book_name;
        ImageView book_img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            return_btn=itemView.findViewById(R.id.return_btn);
//            book_name=itemView.findViewById(R.id.book_name_issue);
//            book_img=itemView.findViewById(R.id.image_book_issued);
        }
    }
}

