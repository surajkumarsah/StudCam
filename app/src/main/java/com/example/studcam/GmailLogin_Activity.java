package com.example.studcam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.squareup.picasso.Picasso;

public class GmailLogin_Activity extends AppCompatActivity {


    static final int GOOGLE_SIGN_IN = 123;
    FirebaseAuth mAuth;
    Button btn_login, btn_logout, btn_phone_login;
    TextView text;
    ImageView image;
    ProgressBar progressBar;
    GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gmail_login_);

        btn_login = findViewById(R.id.login);
        btn_logout = findViewById(R.id.logout);
        btn_phone_login = findViewById(R.id.phonelogin);

        text = findViewById(R.id.text);
        image = findViewById(R.id.image);
        progressBar = findViewById(R.id.progress_circular);

        mAuth = FirebaseAuth.getInstance();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignInGoogle();
            }
        });
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logout();
            }
        });

        btn_phone_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GmailLogin_Activity.this, PhoneReg_Activity.class);
                startActivity(intent);
            }
        });

        if (mAuth.getCurrentUser() != null) {
            FirebaseUser user = mAuth.getCurrentUser();
            updateUI(user);
        }
    }

    public void SignInGoogle() {
        progressBar.setVisibility(View.VISIBLE);
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, GOOGLE_SIGN_IN);
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d("TAG", "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {

                            progressBar.setVisibility(View.INVISIBLE);

                            Log.d("TAG", "signInWithCredential:success");

                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        }
                        else {
                            progressBar.setVisibility(View.INVISIBLE);

                            Log.w("TAG", "signInWithCredential:failure", task.getException());

                            Toast.makeText(GmailLogin_Activity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GOOGLE_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                if (account != null) firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                Log.w("TAG", "Google sign in failed", e);
            }
        }
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            String name = user.getDisplayName();
            String email = user.getEmail();
            String photo = String.valueOf(user.getPhotoUrl());

            text.append("Info : \n");
            text.append(name + "\n");
            text.append(email);

            Picasso.get().load(photo).into(image);
            btn_logout.setVisibility(View.VISIBLE);
            btn_login.setVisibility(View.INVISIBLE);

            Thread thread = new Thread()
            {

                @Override
                public void run() {
                    try
                    {
                        sleep(8000);
                        Intent intent = new Intent(getApplicationContext(), ChooseExam_Activity.class);
                        startActivity(intent);
                        finish();
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            };
            thread.start();

        } else {
            text.setText(getString(R.string.firebase_login));
            //Picasso.with(GmailLogin_Activity.this).load(R.drawable.ic_firebase_logo).into(image);
            Picasso.get().load(R.drawable.ic_firebase_logo).into(image);
            btn_logout.setVisibility(View.INVISIBLE);
            btn_login.setVisibility(View.VISIBLE);
        }
    }

    private void Logout() {
        FirebaseAuth.getInstance().signOut();
        mGoogleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                updateUI(null);
            }
        });
    }
}