package com.rku.tutorial08;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegistrationForm extends AppCompatActivity {
    EditText edtFirstName,edtLastName,edtRUsername,edtRPassword;
    Switch branch;
    Spinner city;
    CheckBox agree;
    RadioGroup rdb_group;
    RadioButton rdb_select;
    DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrationform);
        this.setTitle("Registration Form");

        helper = new DatabaseHelper(this);

        edtFirstName = findViewById(R.id.edtFirstName);
        edtLastName = findViewById(R.id.edtLastName);
        edtRUsername = findViewById(R.id.edtRUsername);
        edtRPassword = findViewById(R.id.edtRPassword);
        branch       = findViewById(R.id.branch);
        rdb_group    = findViewById(R.id.rdb_group);
        city         = findViewById(R.id.city);
        agree        = findViewById(R.id.agree);

    }

    public void saveRecord(View view) {
        String ValFirstName,ValLastName,ValUsername,ValPassword,ValBranch,ValGender,ValCity,ValAgree;


        ValFirstName = edtFirstName.getText().toString();
        ValLastName  = edtLastName.getText().toString();
        ValUsername  = edtRUsername.getText().toString();
        ValPassword  = edtRPassword.getText().toString();
        ValBranch    = branch.getText().toString();
        int id       = rdb_group.getCheckedRadioButtonId();
        rdb_select   = findViewById(id);
        ValGender    = rdb_select.getText().toString();
        ValCity      = city.getSelectedItem().toString();


        /*------------------- Validation Start ---------------------*/

        if (TextUtils.isEmpty(ValFirstName)) {
            edtFirstName.setError("Please Enter FirstName");
            return;
        }

        if (TextUtils.isEmpty(ValLastName)) {
            edtLastName.setError("Please Enter LastName");
            return;
        }

        if (TextUtils.isEmpty(ValUsername)) {
            edtRUsername.setError("Please Enter Email Address");
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(ValUsername).matches()) {
            Toast.makeText(RegistrationForm.this, "Email address format is not valid", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(ValPassword)) {
            edtRPassword.setError("Password is Required.");
            return;
        }

        if (ValPassword.length() < 6) {
            edtRPassword.setError("Password Must be >= 6 Characters");
            return;
        }

        if(ValCity.equals("Select City"))
        {
            Toast.makeText(RegistrationForm.this, "Please Select City", Toast.LENGTH_SHORT).show();
            return;
        }

        if(branch.isChecked()) {
            ValBranch = "IT";
        }else{
            ValBranch = "CE";
        }

        if(agree.isChecked()) {
            ValAgree = "Active";
        }else{
            ValAgree = "Inactive";
        }


        /*------------------- Validation End ---------------------*/

        if (helper.duplicate_user(ValUsername)){
            edtRUsername.requestFocus();
            edtRUsername.setError("Username Already Exits");
            return;
        }else {

            Boolean res = helper.insertData(ValFirstName,ValLastName,ValUsername,ValPassword,ValBranch,ValGender,ValCity,ValAgree);

            if (res == true) {
                Toast.makeText(this, "User Created", Toast.LENGTH_SHORT).show();
                edtFirstName.setText("");
                edtLastName.setText("");
                edtRUsername.setText("");
                edtRPassword.setText("");
                Intent intent = new Intent(RegistrationForm.this, Welcome.class);
                startActivity(intent);

            } else {
                Toast.makeText(RegistrationForm.this, "Something is Wrong", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void btnLogin(View view) {
        Intent intent = new Intent(RegistrationForm.this, Login.class);
        startActivity(intent);
    }
}