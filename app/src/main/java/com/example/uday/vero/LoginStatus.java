package com.example.uday.vero;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class LoginStatus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginstatus);
        String email=getIntent().getStringExtra("email");

        TextView tv=(TextView)findViewById(R.id.user);
        tv.setText(email);
    }
}
