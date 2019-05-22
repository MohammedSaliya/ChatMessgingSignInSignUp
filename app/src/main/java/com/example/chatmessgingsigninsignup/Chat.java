package com.example.chatmessgingsigninsignup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Chat extends AppCompatActivity {


    LinearLayout layout;
    RelativeLayout layout_2;
    ImageView sendButton;
    EditText messageArea;
    ScrollView scrollView;
    Firebase reference1, reference2;

    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        final Intent intent = getIntent();
        final String email = intent.getStringExtra("user");


      /*  mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference("MessageDetails");
*/
        layout = findViewById(R.id.layout1);
        layout_2 = findViewById(R.id.layout2);
        sendButton = findViewById(R.id.sendButton);
        messageArea = findViewById(R.id.messageArea);
        scrollView = findViewById(R.id.scrollView);


        Firebase.setAndroidContext(this);
        reference1 = new Firebase("https://chatmessgingsigninsignup.firebaseio.com/" + UsersDetails.username + "_" + UsersDetails.chatWith);
        reference2 = new Firebase("https://chatmessgingsigninsignup.firebaseio.com/" + UsersDetails.chatWith + "_" + UsersDetails.username);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messageText = messageArea.getText().toString();


                if (!messageText.equals("")) {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("message", messageText);
                    map.put("user", email);
                    reference1.push().setValue(map);
                    reference2.push().setValue(map);

                    messageArea.setText("");

                    Toast.makeText(Chat.this, "Message Sent", Toast.LENGTH_SHORT).show();
                }

            }
        });

        reference1.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Map map = dataSnapshot.getValue(Map.class);
                String message = map.get("message").toString();
                String userName = map.get("user").toString();

                if (userName.equals(UsersDetails.username)) {
                    addMessageBox("You:-\n" + message, 1);
                } else {
                    addMessageBox(UsersDetails.chatWith + ":-\n" + message, 2);
                }

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }
/*
    private void AddingDetail(String message, String username) {

        userid = mFirebaseDatabase.push().getKey();
        Bean registrationBean = new Bean(message, username);

        mFirebaseDatabase.child(userid).setValue(registrationBean);
    }*/

    private void addMessageBox(String message, int type) {
        TextView textView = new TextView(Chat.this);
        textView.setText("user");
        textView.setText(message);

        LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp2.weight = 1.0f;

        if (type == 1) {
            lp2.gravity = Gravity.LEFT;
            textView.setBackgroundResource(R.drawable.bubble_out);
        } else {
            lp2.gravity = Gravity.RIGHT;
            textView.setBackgroundResource(R.drawable.bubble_in);
        }
        textView.setLayoutParams(lp2);
        layout.addView(textView);
        scrollView.fullScroll(View.FOCUS_DOWN);
    }
}

