package com.example.uday.vero;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class DisplayLoginForm extends AppCompatActivity {
    //using the databasehelper class
    DatabaseHelper helper=new DatabaseHelper(this);

    LoginButton login_Button;
    CallbackManager callbackManager;
    TextView textView;
    private EditText lt_email,lt_password;
    private String email,password;
    Button lgbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());

        setContentView(R.layout.activity_display_login_form);
        initializeControls();
        loginWithFB();

        lt_email=(EditText)findViewById(R.id.ematxt);
        lt_password=(EditText)findViewById(R.id.pwdtxt);
        lgbtn =(Button)findViewById(R.id.buttonlogin);
        lgbtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                //validate();
                initalize();
                    String pass = helper.searchpassword(email);
                    if (password.equals(pass)) {
                        Intent intent = new Intent(DisplayLoginForm.this, LoginStatus.class);
                        intent.putExtra("email", email);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast temp = Toast.makeText(DisplayLoginForm.this, "Check email and password", Toast.LENGTH_LONG);
                        temp.show();

                    }
                }

        });


    }
    public void initalize(){
        email=lt_email.getText().toString().trim();
        password=lt_password.getText().toString().trim();
    }
    /*public boolean validate(){
        boolean valid=true;
        if(email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            lt_email.setError("Valid email is required");
            valid=false;
        }
        if(password.isEmpty()){
            lt_password.setError("Enter the password");
            valid=false;
        }
        return valid;
    }
*/

    public void initializeControls() {
        login_Button = (LoginButton) findViewById(R.id.fb_login_bn);

        textView = (TextView) findViewById(R.id.textview);

        callbackManager = CallbackManager.Factory.create();
    }

    public void loginWithFB() {

        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {

                textView.setText("Login Success \n " +
                        loginResult.getAccessToken().getUserId() +
                        "\n" + loginResult.getAccessToken().getToken());
            }

            @Override
            public void onCancel() {
                textView.setText("Login Cancelled");
            }

            @Override
            public void onError(FacebookException error) {
            textView.setText("Login Error:"+error.getMessage());
            }
        });
    }


    public void onActivityResult(int requestcode,int resultcode,Intent data){
     callbackManager.onActivityResult(requestcode,resultcode,data);
    }
}
