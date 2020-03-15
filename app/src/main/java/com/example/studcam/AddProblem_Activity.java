package com.example.studcam;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.studcam.model.QuestionsViewModel;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class AddProblem_Activity extends AppCompatActivity {


    private EditText description;
    private Button addData, viewProblem;
    String inputName, inputEmail, inputMessage;
    ImageView prlmImg;
    String downloadImageUrl;

    GoogleSignInClient mGoogleSignInClient;
    FirebaseAuth mAuth;

    private ProgressDialog loadingBar;
    private DatabaseReference prlmRef,userRef;
    StorageReference prlmImgRef;
    String saveCurrentDate, saveCurrentTime, productRandomKey;
    FirebaseUser user;

    private static final int GalleryPick = 1;
    private Uri ImageUri;
    public QuestionsViewModel questionsViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_problem_);

        prlmImgRef = FirebaseStorage.getInstance().getReference().child("problem_image");

        description = (EditText) findViewById(R.id.prlm_desc);
        addData = (Button) findViewById(R.id.submit);
        prlmImg = (ImageView) findViewById(R.id.problem_image);
        viewProblem = findViewById(R.id.view_problem);


        mAuth = FirebaseAuth.getInstance();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


        if (mAuth.getCurrentUser() != null) {
            user = mAuth.getCurrentUser();
        }


        prlmImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        viewProblem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(), ProblemViewRecycler_Activity.class);
                startActivity(intent);
            }
        });

        addData.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                validateStudentAccount();
            }
        });
    }

    private void openGallery() {
        Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, GalleryPick);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GalleryPick && resultCode == RESULT_OK && data != null)
        {
            ImageUri = data.getData();
            prlmImg.setImageURI(ImageUri);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void validateStudentAccount() {

        if (ImageUri == null) {
            Toast.makeText(this, "Product Image is mandatory.", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(description.getText().toString()))
        {
            Toast.makeText(this, "Add Description", Toast.LENGTH_SHORT).show();
        }
            else {
            StoreStudentInformation();
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void StoreStudentInformation() {
        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd,YYYY");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calendar.getTime());

        productRandomKey = saveCurrentDate + saveCurrentTime;

        final StorageReference filePath = prlmImgRef.child(ImageUri.getLastPathSegment() + productRandomKey + ".jpg");
        final UploadTask uploadTask = filePath.putFile(ImageUri);


        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                String message = e.toString();
                Toast.makeText(AddProblem_Activity.this, "Error : " + message, Toast.LENGTH_SHORT).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(AddProblem_Activity.this, "Image Uploaded Successfully.", Toast.LENGTH_SHORT).show();

                Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()) {
                            throw task.getException();
                        }

                        downloadImageUrl = filePath.getDownloadUrl().toString();
                        return filePath.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()) {
                            downloadImageUrl = task.getResult().toString();

                            Toast.makeText(AddProblem_Activity.this, "got the Photo image URL Successfully.", Toast.LENGTH_SHORT).show();
                            saveStudentInfoToDatabase();
                            saveDataByUser();
                        }
                    }
                });
            }
        });


    }

    private void saveDataByUser()
    {
        userRef = FirebaseDatabase.getInstance().getReference().child("UserProblem").child(user.getUid());

        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!(dataSnapshot.child(productRandomKey).exists())) {
                    HashMap<String, Object> prlmDetails = new HashMap<>();
                    prlmDetails.put("desc", description.getText().toString().trim());
                    prlmDetails.put("date", saveCurrentDate);
                    prlmDetails.put("userId",user.getUid());
                    prlmDetails.put("userEmail",user.getEmail());
                    prlmDetails.put("formRegTime", saveCurrentTime);
                    prlmDetails.put("Image", downloadImageUrl);

                    userRef.child(productRandomKey).updateChildren(prlmDetails)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(AddProblem_Activity.this, "Congratulation, Problem Details has been created Successfully.", Toast.LENGTH_SHORT).show();

                                        Intent intent = new Intent(AddProblem_Activity.this, MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        Toast.makeText(AddProblem_Activity.this, "Network Error, Please try again after Sometime.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });


                } else {
                    Toast.makeText(AddProblem_Activity.this, "This " + productRandomKey + " already Exists.", Toast.LENGTH_SHORT).show();

                    Toast.makeText(AddProblem_Activity.this, "problem is already exist try again.", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(AddProblem_Activity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void saveStudentInfoToDatabase() {

        prlmRef = FirebaseDatabase.getInstance().getReference().child("SubjectNote");

        prlmRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!(dataSnapshot.child(productRandomKey).exists())) {
                    HashMap<String, Object> prlmDetails = new HashMap<>();
                    prlmDetails.put("desc", description.getText().toString().trim());
                    prlmDetails.put("date", saveCurrentDate);
                    prlmDetails.put("userId",user.getUid());
                    prlmDetails.put("userEmail",user.getEmail());
                    prlmDetails.put("formRegTime", saveCurrentTime);
                    prlmDetails.put("Image", downloadImageUrl);

                    prlmRef.child(productRandomKey).updateChildren(prlmDetails)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(AddProblem_Activity.this, "Congratulation, Problem Details has been created Successfully.", Toast.LENGTH_SHORT).show();

                                        Intent intent = new Intent(AddProblem_Activity.this, MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        Toast.makeText(AddProblem_Activity.this, "Network Error, Please try again after Sometime.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });


                } else {
                    Toast.makeText(AddProblem_Activity.this, "This " + productRandomKey + " already Exists.", Toast.LENGTH_SHORT).show();

                    Toast.makeText(AddProblem_Activity.this, "problem is already exist try again.", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(AddProblem_Activity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
