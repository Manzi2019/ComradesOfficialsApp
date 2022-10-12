package com.manzi.comradesofficials;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase mDatabase;
    DatabaseReference mRef;
    FirebaseStorage mStorage;
    ImageView imageButton;
    EditText  editName, editMultiple;
    Button btnAnnounce;
    private static final int Gallery_code=1;
    Uri imageUrl=null;

    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageButton=(ImageView) findViewById(R.id.imageHolder);
        editName=(EditText) findViewById(R.id.edtName);
        editMultiple=(EditText) findViewById(R.id.edtMultiLine);
        btnAnnounce=(Button) findViewById(R.id.btnSubmit);

        mDatabase=FirebaseDatabase.getInstance();
        mRef=FirebaseDatabase.getInstance().getReference().child("Viongozi");
        mStorage=FirebaseStorage.getInstance();
        progressDialog=new ProgressDialog(this);

        Intent intent=new Intent(MainActivity.this,RetriveData.class);
        startActivity(intent);



        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/");
                startActivityForResult(intent,Gallery_code);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==Gallery_code && resultCode==RESULT_OK)
        {
            imageUrl=data.getData();
            imageButton.setImageURI(imageUrl);

        }

        btnAnnounce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String fn=editName.getText().toString().trim();
                String ln=editMultiple.getText().toString().trim();

                if (!(fn.isEmpty() && ln.isEmpty() && imageUrl!=null))

                {

progressDialog.setTitle("Uploading....");
progressDialog.show();

                    StorageReference filepath=mStorage.getReference().child("imagePost").child(imageUrl.getLastPathSegment());
                    filepath.putFile(imageUrl).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            Task<Uri> downloadUrl=taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {

                                    String t=task.getResult().toString();
                                    DatabaseReference newpost=mRef.push();

                                    newpost.child("Name").setValue(fn);
                                    newpost.child("Announcement").setValue(ln);
                                    newpost.child("Image").setValue(task.getResult().toString());
                                    progressDialog.dismiss();

                                }
                            });

                        }
                    });

                }
            }
        });
    }
}