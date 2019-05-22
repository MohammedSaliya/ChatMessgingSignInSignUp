package com.example.chatmessgingsigninsignup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {

    TextView registerUser;
    EditText username, password;
    Button loginButton;
    private FirebaseAuth auth;
    String user, pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        registerUser = findViewById(R.id.register);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);


        auth = FirebaseAuth.getInstance();

        registerUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Register.class));
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = username.getText().toString();
                pass = password.getText().toString();


                Intent intent = new Intent(Login.this, Chat.class);
                intent.putExtra("user", user);
                startActivity(intent);


                auth.signInWithEmailAndPassword(user, pass)
                        .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()) {
                                    if (password.length() < 6) {
                                        Toast.makeText(Login.this, "password shoud be more than 6 digtes", Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(Login.this, "Faild!!!!!", Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Toast.makeText(Login.this, "done", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(Login.this, Chat.class));

                                }
                            }
                        });
            }
        });

    }
}




