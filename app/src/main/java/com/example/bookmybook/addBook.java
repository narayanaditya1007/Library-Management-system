package com.example.bookmybook;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bookmybook.Models.BookModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

public class addBook extends AppCompatActivity {


    // view for image view
    private ImageView imageView;
    public String URI;


    public EditText bookName,authorName,bookCnt;
    public TextView submit;
    // Uri indicates, where the image will be picked from
    private Uri filePath;
    // request code
    private final int PICK_IMAGE_REQUEST = 22;

    public BookModel book;

    // instance for firebase storage and StorageReference
    FirebaseStorage storage;
    StorageReference storageReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);


        // initialise views
        imageView = findViewById(R.id.SelectImage);
        submit= (TextView) findViewById(R.id.submit);
        storage = FirebaseStorage.getInstance();
        bookName=findViewById(R.id.addBook_name);
        authorName=findViewById(R.id.addBook_authorName);
        bookCnt=findViewById(R.id.addBook_bookCnt);
        storageReference = storage.getReference();
//        DatabaseHandler db=new DatabaseHandler(this);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                SelectImage();
                Toast.makeText(addBook.this, "Selected", Toast.LENGTH_SHORT).show();
            }
        });

//         on pressing btnUpload uploadImage() is called
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String UploadedImage=uploadImage();



            }
        });

        //SQL wala part karo madarchod
    }

    // Select Image method
    private void SelectImage()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Image from here..."), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                // Setting image on image view using Bitmap
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageView.setImageBitmap(bitmap);
            }
            catch (IOException e) {
                // Log the exception
                e.printStackTrace();
            }
        }
    }

    private String uploadImage()
    {
        final String[] res = new String[1];
        if (filePath != null) {
            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();
            StorageReference ref = storageReference.child("images/" + UUID.randomUUID().toString());
            ref.putFile(filePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                            progressDialog.dismiss();
                                            //Toast.makeText(addBook.this, "Image Uploaded!!", Toast.LENGTH_SHORT).show();
                                            taskSnapshot.getStorage().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                @Override
                                                public void onSuccess(Uri uri) {
                                                    res[0] =uri.toString();
                                                    URI=res[0];
                                                    Toast.makeText(addBook.this, "Ho gya"+res[0], Toast.LENGTH_SHORT).show();
                                                    book=new BookModel(bookName.getText().toString(),authorName.getText().toString(),res[0],Integer.parseInt(bookCnt.getText().toString()));
                                                    DatabaseHandler db=new DatabaseHandler(addBook.this);
                                                    db.addBook(book);
                                                    startActivity(new Intent( addBook.this,HomeActivity.class));
                                                    finish();


                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(addBook.this, "error", Toast.LENGTH_SHORT).show();
                                                }
                                            });

                                }
                            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // Error, Image not uploaded
                            progressDialog.dismiss();
                            Toast.makeText(addBook.this, "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onProgress(
                                        UploadTask.TaskSnapshot taskSnapshot) {
                                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                                            progressDialog.setMessage("Uploaded " + (int)progress + "%");
                                        }
                                });

        }
        String ress=res[0];
        Toast.makeText(this, "ress:"+ress, Toast.LENGTH_SHORT).show();
        return  ress;
    }
}