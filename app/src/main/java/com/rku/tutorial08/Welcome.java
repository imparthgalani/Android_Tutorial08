package com.rku.tutorial08;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Welcome extends AppCompatActivity {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    DatabaseHelper helper;

    //String userData[]={"Test1","Test2"};
    ListView lstUserList;
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        preferences = getSharedPreferences("college", MODE_PRIVATE);
        editor = preferences.edit();

        helper = new DatabaseHelper(this);

        lstUserList = findViewById(R.id.lstUserList);
        adapter = new ArrayAdapter<String>(
                Welcome.this,
                android.R.layout.simple_list_item_1,
                helper.getUserList()
        );
        lstUserList.setAdapter(adapter);
        lstUserList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               // Toast.makeText(Welcome.this,((TextView)view).getText().toString(), Toast.LENGTH_SHORT).show();
                String listItem = ((TextView)view).getText().toString();
                Intent intent = new Intent(Welcome.this,UserDetail.class);
                intent.putExtra("userdata",listItem);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cutsome_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnuLogout:
                editor.remove("isLogin");
                editor.commit();
                Intent intent = new Intent(Welcome.this, Login.class);
                startActivity(intent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}