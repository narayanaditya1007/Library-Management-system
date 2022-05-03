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

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileIssueAdapter extends RecyclerView.Adapter<ProfileIssueAdapter.ViewHolder> {

    ArrayList<IssueModel> list;
    Context context;

    public ProfileIssueAdapter(ArrayList<IssueModel> list, Context context) {
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
        IssueModel issue = list.get(position);
        //System.out.println("Issue id " + issue.getIssueID());
        DatabaseHandler db=new DatabaseHandler(context.getApplicationContext());
        int bookid=issue.getBookID();
        BookModel book=db.getBook(bookid);
        Glide.with(context).load(book.getDescription()).into(holder.book_img);
        holder.book_name.setText(book.getName());
        holder.return_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHandler db=new DatabaseHandler(context);

                db.setReturndate(issue.getIssueID());
                db.deleteIssue(issue.getIssueID());
                db.bookCntIncrement(bookid,book.getBookCnt());
                Toast.makeText(context, "Book Returned", Toast.LENGTH_SHORT).show();
                BookModel b1=db.getBook(bookid);
                System.out.println("lawda");
                System.out.println(b1.toString());
//                holder.return_btn.setVisibility(View.INVISIBLE);
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

