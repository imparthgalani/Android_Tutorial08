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
        userAllData +="Username : "+cursor.getString(1);
        userAllData +="\nPassword : "+cursor.getString(2);

        txtUserDetail.setText(userAllData);

    }
}