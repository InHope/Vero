package com.example.uday.vero;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DisplaySignUpForm extends AppCompatActivity {
    //using the DatabaseHelper Class
    DatabaseHelper helper=new DatabaseHelper(this);

    private EditText et_email, et_fname, et_lname, et_password;
    private String email, fname, lname, password;
    Button sigbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_sign_up_form);
        et_email = (EditText) findViewById(R.id.emtxt);
        et_email.setTextColor(Color.parseColor("#FFFFFF"));
        et_fname = (EditText) findViewById(R.id.fntxt);
        et_fname.setTextColor(Color.parseColor("#FFFFFF"));
        et_lname = (EditText) findViewById(R.id.lntxt);
        et_lname.setTextColor(Color.parseColor("#FFFFFF"));
        et_password = (EditText) findViewById(R.id.pswd);
        et_password.setTextColor(Color.parseColor("#FFFFFF"));
        sigbtn = (Button) findViewById(R.id.buttonsignup);
        sigbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
    }

    public void register() {
        initalize();// Intialize the input into string variables
        if (!validate()) {
            Toast.makeText(this, "SignUp has Failed", Toast.LENGTH_SHORT).show();
        }
        else {
            onSignupSuccess();
        }
    }
    public void onSignupSuccess() {
        // insert the details in database
        Contact cs= new Contact();
        //storing the contactsignup values
        cs.setEmail(email);
        cs.setFname(fname);
        cs.setLname(lname);
        cs.setPassword(password);
        helper.insertContact(cs);



    }

    public boolean validate() {
        boolean valid = true;
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            et_email.setError("Please Enter Valid Email Address");
            valid = false;
        }
        if (fname.isEmpty() || fname.length() > 16) {
            et_fname.setError("Please Enter First name");
            valid = false;
        }
        if (lname.isEmpty()) {
            et_lname.setError("Please Enter Last name");
            valid = false;
        }
        if (password.isEmpty() || password.length() < 4) {
            et_password.setError("Password length must be more than 4 Characters ");
            valid = false;
        }
        return valid;

    }

    public void initalize() {
        //intialize the input into String variables
        email = et_email.getText().toString().trim();
        fname = et_fname.getText().toString().trim();
        lname = et_lname.getText().toString().trim();
        password = et_password.getText().toString().trim();

    }
}
