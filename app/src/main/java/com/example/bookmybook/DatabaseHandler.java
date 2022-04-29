package com.example.bookmybook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.bookmybook.Models.BookModel;
import com.example.bookmybook.Models.UserModel;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    public static final String USERTABLE = "USERTABLE";
    public static final String NAME = "NAME";
    public static final String EMAIL = "EMAIL";
    public static final String PASSWORD = "PASSWORD";
    public static final String BOOKCNT = "BOOKCNT";
    public static final String BOOKID1 = "BOOKID";
    public static final String BOOKID = BOOKID1;
    public static final String IMAGEURL = "IMAGEURL";
    public static final String AUTHORNAME = "AUTHORNAME";
    public static final String BOOKNAME = "BOOKNAME";
    public static final String BOOKSTABLE = "BOOKSTABLE";
    public static final String ISSUEID = "ISSUEID";
    public static final String RETURNDATE = "RETURNDATE";
    public static final String ISSUEDATE = "ISSUEDATE";
    public static final String USEREMAIL = "USEREMAIL";
    public static final String ISSUETABLE = "ISSUETABLE";

    public DatabaseHandler(@Nullable Context context) {
        super(context,"Library.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableUser= "CREATE TABLE " + USERTABLE + " (" + NAME + " TEXT, " + EMAIL + " TEXT PRIMARY KEY, " + PASSWORD + " TEXT)";
        String createTableBooks= "CREATE TABLE " + BOOKSTABLE + " (" + BOOKNAME + " TEXT, " + AUTHORNAME + " TEXT, " + IMAGEURL + " TEXT, " + BOOKID + " INTEGER PRIMARY KEY, " + BOOKCNT + " INTEGER)";
        String creteTableIssue= "CREATE TABLE " + ISSUETABLE + " (" + USEREMAIL + " TEXT, " + ISSUEDATE + " TEXT, " + RETURNDATE + " TEXT, " + ISSUEID + " INTEGER PRIMARY KEY, " + BOOKID1 + " INTEGER)";
        sqLiteDatabase.execSQL(createTableBooks);
        sqLiteDatabase.execSQL(createTableUser);
        sqLiteDatabase.execSQL(creteTableIssue);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    void addBook(BookModel book) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(BOOKNAME, book.getName()); // Contact Name
        values.put(IMAGEURL,book.getDescription()); // Contact Phone
        values.put(AUTHORNAME,book.getAuthor());
        values.put(BOOKID,book.getBookID());
        values.put(BOOKCNT,book.getBookCnt());

        // Inserting Row
        db.insert(BOOKSTABLE, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }


    public List<BookModel> getAllBooks() {
        List<BookModel> bookList = new ArrayList<BookModel>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + BOOKSTABLE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                BookModel book = new BookModel();
                book.setName(cursor.getString(0));
                book.setAuthor(cursor.getString(1));
                book.setDescription(cursor.getString(2));
                book.setBookID(cursor.getInt(3));
                book.setBookCnt(cursor.getInt(4));
                // Adding contact to list
                bookList.add(book);
            } while (cursor.moveToNext());
        }

        // return contact list
        return bookList;
    }


}
