package com.rku.tutorial08;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

public class UserDetail extends AppCompatActivity {

    DatabaseHelper helper;
    TextView txtUserDetail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        this.setTitle("User Detail");

        helper = new DatabaseHelper(this);
        txtUserDetail = findViewById(R.id.txtUserDetail);

        String userAllData="";

        Intent intent = getIntent();
        String userdata = intent.getStringExtra("userdata");

        Cursor cursor = helper.getSingleUserDetail(userdata);
        cursor.moveToFirst();
        userAllData +="FirstName : "+cursor.getString(1);
        userAllData +="\nLastName : "+cursor.getString(2);
        userAllData +="\nUsername : "+cursor.getString(3);
        userAllData +="\nPassword : "+cursor.getString(4);
        userAllData +="\nBranch : "+cursor.getString(5);
        userAllData +="\nGender : "+cursor.getString(6);
        userAllData +="\nCity : "+cursor.getString(7);
        userAllData +="\nStatus : "+cursor.getString(8);

        txtUserDetail.setText(userAllData);

    }
}