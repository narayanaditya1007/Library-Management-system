package com.example.bookmybook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.bookmybook.Models.BookModel;
import com.example.bookmybook.Models.IssueModel;
import com.example.bookmybook.Models.UserModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
        String createTableBooks= "CREATE TABLE " + BOOKSTABLE + " (" + BOOKNAME + " TEXT, " + AUTHORNAME + " TEXT, " + IMAGEURL + " TEXT, " + BOOKID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + BOOKCNT + " INTEGER)";
        String creteTableIssue= "CREATE TABLE " + ISSUETABLE + " (" + USEREMAIL + " TEXT, " + ISSUEDATE + " TEXT, " + RETURNDATE + " TEXT, " + ISSUEID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + BOOKID1 + " INTEGER)";
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
        values.put(BOOKCNT,book.getBookCnt());

        // Inserting Row
        db.insert(BOOKSTABLE, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }


    public ArrayList<BookModel> getAllBooks() {
        ArrayList<BookModel> bookList = new ArrayList<BookModel>();
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

        cursor.close();
        db.close();
        // return contact list
        return bookList;
    }

    public ArrayList<BookModel> getAllBooksSearch(String searchKey) {
        ArrayList<BookModel> bookList = new ArrayList<BookModel>();

        String s1="%"+searchKey+"%";
        // Select All Query
        System.out.println("String :"+searchKey);
        String selectQuery = "SELECT  * FROM " + BOOKSTABLE+" WHERE "+BOOKNAME+" LIKE '%"+searchKey+"%' ";
        System.out.println("Query: "+selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

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

        cursor.close();
        db.close();
        // return contact list
        return bookList;
    }

    public boolean addUser(UserModel user){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NAME,user.getName()); // Contact Name
        values.put(EMAIL,user.getEmail()); // Contact Phone
        values.put(PASSWORD,user.getPassword());


        // Inserting Row
        long res=db.insert(USERTABLE, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
        return res!=-1;
    }

    public boolean checkUsername(String username){
        SQLiteDatabase myDB=this.getReadableDatabase();
        String que="SELECT * FROM "+USERTABLE+" WHERE "+EMAIL+" = ?";
        Cursor cursor=myDB.rawQuery(que, new String[]{username});
        return cursor.getCount()>0;
    }

    public boolean checkUsernamePassword(String username, String password){
        SQLiteDatabase myDB=this.getReadableDatabase();
        Cursor cursor=myDB.rawQuery("SELECT * FROM "+USERTABLE+" WHERE "+EMAIL+" =  ? AND "+PASSWORD+" = ?",new String[]{username,password});
        return cursor.getCount()>0;
    }

    public ArrayList<IssueModel> getAllBooksIssued(String email){
        ArrayList<IssueModel> IssueList = new ArrayList<IssueModel>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + ISSUETABLE+" WHERE "+ USEREMAIL +" = ? ORDER BY "+ISSUEDATE+" DESC";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{email});

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                IssueModel issue = new IssueModel();
                issue.setUserEmail(cursor.getString(0));
                issue.setIssueDate(cursor.getString(1));
                issue.setReturnDate(cursor.getString(2));
                issue.setIssueID(cursor.getInt(3));
                issue.setBookID(cursor.getInt(4));
                // Adding contact to list
                IssueList.add(issue);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        // return contact list
        return IssueList;
    }

    public int bookIdfromIssueId(int Issueid){
        SQLiteDatabase db=this.getReadableDatabase();
        String query="SELECT "+BOOKID1+" FROM "+ISSUETABLE+" WHERE "+ISSUEID+" = "+Issueid;
        Cursor cursor=db.rawQuery(query,null);
        return cursor.getInt(0);
    }

    public BookModel getBook(int Bookid){
        System.out.println(String.valueOf(Bookid));
        SQLiteDatabase db=this.getReadableDatabase();
        String query="SELECT * FROM "+BOOKSTABLE+" WHERE "+BOOKID+" = "+String.valueOf(Bookid);
        Cursor cursor=db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            BookModel book=new BookModel(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getInt(3),cursor.getInt(4));
            return book;
        }

       return new BookModel();
    }

    public void addIssue(IssueModel issue){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USEREMAIL,issue.getUserEmail()); // Contact Name
        values.put(ISSUEDATE,issue.getIssueDate()); // Contact Phone
        values.put(RETURNDATE,issue.getReturnDate());
        values.put(BOOKID1,issue.getBookID());


        // Inserting Row
        db.insert(ISSUETABLE, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection

    }

    public void bookCntDecrement(int bookId,int bookcnt){
        String query="UPDATE "+BOOKSTABLE+" SET "+BOOKCNT+" = "+String.valueOf(bookcnt-1)+" WHERE "+BOOKID+" = "+String.valueOf(bookId);
        System.out.println("query:"+query);
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL(query);
    }

    public void setReturndate(int issueId){
        System.out.println("Issue ID" + issueId);
        SQLiteDatabase db=this.getWritableDatabase();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
        String currentDate = sdf.format(new Date());
        System.out.println("Current date:"+currentDate);
//        currentDate="19";
        String query="UPDATE "+ISSUETABLE+" SET "+RETURNDATE+" = "+currentDate+" WHERE "+ISSUEID+" = "+String.valueOf(issueId);
        System.out.println("querry " + query);
        db.execSQL(query);
    }
    public void bookCntIncrement(int bookId,int bookcnt){
        String query="UPDATE "+BOOKSTABLE+" SET "+BOOKCNT+" = "+String.valueOf(bookcnt+1)+" WHERE "+BOOKID+" = "+String.valueOf(bookId);
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL(query);
    }

    public void deleteIssue(int issueId){
        SQLiteDatabase db=this.getWritableDatabase();
        String query="DELETE FROM "+ISSUETABLE+" WHERE "+ISSUEID+" = ?";
        db.rawQuery(query,new String[]{String.valueOf(issueId)});
    }

    public String usernameFromEmail(String email){
        SQLiteDatabase myDB=this.getReadableDatabase();
        String que="SELECT "+NAME+" FROM "+USERTABLE+" WHERE "+EMAIL+" = ?";
        Cursor cursor=myDB.rawQuery(que, new String[]{email});
        if(cursor.moveToFirst()){
            return cursor.getString(0);

        }
        return "lawda";
    }

}
