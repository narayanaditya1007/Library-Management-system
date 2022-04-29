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

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    Context context;
    Vector<BookModel> list;
    @NonNull
    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.activity_home,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView return_btn,book_name;
        ImageView book_img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            return_btn=itemView.findViewById(R.id.return_btn);
            book_name=itemView.findViewById(R.id.book_name_issue);
            book_img=itemView.findViewById(R.id.image_book_issued);
        }
    }
}
