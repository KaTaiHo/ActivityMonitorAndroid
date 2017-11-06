package com.example.katai.activityrecognition;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;


public class DataActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;
    public FirebaseAuth mAuth;
    public FirebaseAuth.AuthStateListener mAuthListener;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        final TableLayout table = (TableLayout) findViewById(R.id.table);
        FirebaseUser user = mAuth.getCurrentUser();
        mDatabase.child("users").child(user.getUid()).child("Posts").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot post : dataSnapshot.getChildren()) {
                        // do something with the individual posts
                        populateTable(post);
                        //System.out.println(post);
                    }
                    table.invalidate();
                }
                else {
                    System.out.println("THE USER IDENT IS WRONG");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void populateTable(DataSnapshot post){
        TableLayout ll = (TableLayout) findViewById(R.id.table);
        TableRow row= new TableRow(this);
        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        row.setLayoutParams(lp);

        TextView tv = new TextView(this);
        HashMap<String, Object> dataTemp = (HashMap)(post.getValue());
        String message = (String)(dataTemp.get("message"));
        System.out.println(message);
        tv.setText(message);
        row.addView(tv);
        ll.addView(row);
    };

}
