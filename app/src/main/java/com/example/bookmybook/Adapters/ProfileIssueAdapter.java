package com.example.bookmybook.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bookmybook.Models.BookModel;
import com.example.bookmybook.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileIssueAdapter extends RecyclerView.Adapter<ProfileIssueAdapter.ViewHolder> {

    ArrayList<BookModel> list;
    Context context;

    public ProfileIssueAdapter(ArrayList<BookModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ProfileIssueAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.profile_issue_sample,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileIssueAdapter.ViewHolder holder, int position) {
        BookModel book = list.get(position);
        Glide.with(context).load(book.getImage()).into(holder.book_img);
        holder.book_name.setText(book.getName());
        holder.return_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //narayan madarchod apna kaam karo
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView return_btn,book_name,issueDate;
        CircleImageView book_img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            return_btn=itemView.findViewById(R.id.return_btn);
            book_name=itemView.findViewById(R.id.issued_book_name);
            book_img=itemView.findViewById(R.id.issued_book_image);
            issueDate=itemView.findViewById(R.id.userProfileissueDate);
        }
    }
}

