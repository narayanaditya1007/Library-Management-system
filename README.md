# Library-Management-system

Introduction
Book my Book is a bookshare management application for android which
allows users to donate books anonymously and then the donated books
can be rented for reading by the users and then they have to return the
rented books.

Technologies Used:
1. Java
2. Xml
3. Sql (SQLite)

How to run:
1. You’ll find an .apk file in the repository, download the file in any
android device.
2. Install the .apk file in your device, and run the application.

Functionalities:

● Registration

● Login

● Donate book

● Request a book for rent

● Return the book after reading.

● Update the count of available books accordingly.

Entities Along with Schema:

User:

Name
Email Primary Key
Password

Book:

Name
Author
Book ID Primary Key
Book Image
Book Count

Issues:

User Email
Issue Date
Return Date
Issue ID Primary Key
Book ID

SQL Queries:

1. Table Creation:

"CREATE TABLE " + USERTABLE + " (" + NAME + " TEXT, " +
EMAIL + " TEXT PRIMARY KEY, " + PASSWORD + " TEXT)"

"CREATE TABLE " + BOOKSTABLE + " (" + BOOKNAME + " TEXT,
" + AUTHORNAME + " TEXT, " + IMAGEURL + " TEXT, " +
BOOKID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
BOOKCNT + " INTEGER)"

"CREATE TABLE " + ISSUETABLE + " (" + USEREMAIL + "
TEXT, " + ISSUEDATE + " TEXT, " + RETURNDATE + " TEXT, "
+ ISSUEID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
BOOKID1 + " INTEGER)"

2. Data Extraction:

"SELECT * FROM " + BOOKSTABLE

"SELECT * FROM " + BOOKSTABLE+" WHERE "+BOOKNAME+" LIKE
'%"+searchKey+"%' "

"SELECT * FROM " + ISSUETABLE+" WHERE "+ USEREMAIL +" =
? ORDER BY "+ISSUEDATE+" DESC"

"SELECT "+BOOKID1+" FROM "+ISSUETABLE+" WHERE
"+ISSUEID+" = "+Issueid

"SELECT * FROM "+BOOKSTABLE+" WHERE "+BOOKID+" =
"+String.valueOf(Bookid)

"SELECT "+NAME+" FROM "+USERTABLE+" WHERE "+EMAIL+" = ?"

3. Authentication:

"SELECT * FROM "+USERTABLE+" WHERE "+EMAIL+" = ?"

"SELECT * FROM "+USERTABLE+" WHERE "+EMAIL+" = ? AND
"+PASSWORD+" = ?"

4. Data Updation:

"UPDATE "+BOOKSTABLE+" SET "+BOOKCNT+" =
"+String.valueOf(bookcnt-1)+" WHERE "+BOOKID+" =
"+String.valueOf(bookId)

"UPDATE "+ISSUETABLE+" SET "+RETURNDATE+" =
"+currentDate+" WHERE "+ISSUEID+" =
"+String.valueOf(issueId)

"UPDATE "+BOOKSTABLE+" SET "+BOOKCNT+" =
"+String.valueOf(bookcnt+1)+" WHERE "+BOOKID+" =
"+String.valueOf(bookId)

5. Data Deletion:

"DELETE FROM "+ISSUETABLE+" WHERE "+ISSUEID+" = ?"
