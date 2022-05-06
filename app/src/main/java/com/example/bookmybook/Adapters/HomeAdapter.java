package com.example.bookmybook.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bookmybook.DatabaseHandler;
import com.example.bookmybook.Models.BookModel;
import com.example.bookmybook.Models.IssueModel;
import com.example.bookmybook.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.viewholder> {
    Context context;
    ArrayList<BookModel> list;
    String userEmail;

    public HomeAdapter(Context context, ArrayList<BookModel> list,String userEmail) {
        this.context = context;
        this.list = list;
        this.userEmail=userEmail;
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
        System.out.println("book dekhte hai"+book.getBookID());
        holder.book_name.setText(book.getName());
        Glide.with(context).load(book.getDescription()).into(holder.book_img);
        holder.authorName.setText(book.getAuthor());
        holder.available_count.setText(""+book.getBookCnt());
        holder.rent_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(book.getBookCnt()==0){
                    Toast.makeText(context, "Book unavailable", Toast.LENGTH_SHORT).show();
                }
                else{
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
                    String currentDate = sdf.format(new Date());
                    IssueModel issue=new IssueModel(userEmail,currentDate,"null",book.getBookID());
                    DatabaseHandler db=new DatabaseHandler(context.getApplicationContext());
                    db.addIssue(issue);
                    db.bookCntDecrement(book.getBookID(),book.getBookCnt());
                    BookModel b1=db.getBook(book.getBookID());
                    System.out.println("book "+b1.toString());
                    System.out.println("Issue ID "+issue.getIssueID());
                    Toast.makeText(context, "Successfully rented", Toast.LENGTH_SHORT).show();
                    holder.available_count.setText(String.valueOf(b1.getBookCnt()));
                    //holder.rent_btn.setVisibility(View.INVISIBLE);
                }
            }
        });
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
        TextView book_name,authorName,available_count,rent_btn;
        CircleImageView book_img;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            book_name=itemView.findViewById(R.id.book_sample_book_name);
            authorName=itemView.findViewById(R.id.book_sample_author_name);
            available_count=itemView.findViewById(R.id.book_sample_available_count);
            book_img=itemView.findViewById(R.id.book_sample_book_image);
            rent_btn=itemView.findViewById(R.id.book_smaple_rent_btn);
        }
    }
}
