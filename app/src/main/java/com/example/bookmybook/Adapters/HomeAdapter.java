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

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.viewholder> {
    Context context;
    ArrayList<BookModel> list;

    public HomeAdapter(Context context, ArrayList<BookModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public HomeAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.book_sample,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.viewholder holder, int position) {
        BookModel book = list.get(position);
        holder.book_name.setText(book.getName());
        Glide.with(context).load(book.getDescription()).into(holder.book_img);
        holder.authorName.setText(book.getAuthor());
        holder.available_count.setText(""+book.getBookCnt());
        holder.
//        holder.book_img.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView book_name,authorName,available_count;
        CircleImageView book_img;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            book_name=itemView.findViewById(R.id.book_sample_book_name);
            authorName=itemView.findViewById(R.id.book_sample_author_name);
            available_count=itemView.findViewById(R.id.book_sample_available_count);
            book_img=itemView.findViewById(R.id.book_sample_book_image);
        }
    }
}
