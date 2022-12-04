package com.lectures.finalproject.controllers.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.lectures.finalproject.MainActivity;
import com.lectures.finalproject.R;
import com.lectures.finalproject.tools.AuthCheckTool;
import com.lectures.finalproject.tools.StyleTools;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etLoginEmail;
    private EditText etLoginPassword;
    private TextView loginTitle;
    private Button btnLogin;
    private TextView textLineRegister;
    private TextView clickableRegister;
    private ProgressBar progressBar;
    private boolean isLogin = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        StyleTools.setActionBar(Objects.requireNonNull(getSupportActionBar()),getBaseContext());
        findViews();
        setListeners();
    }

    private void setListeners() {
        btnLogin.setOnClickListener(this);
        clickableRegister.setOnClickListener(v->{
            if(isLogin){
                btnLogin.setText(R.string.register);
                loginTitle.setText(R.string.register);
                textLineRegister.setText(R.string.have_an_account);
                clickableRegister.setText(R.string.login_now);
                isLogin = false;
            }else{
                btnLogin.setText(R.string.login);
                loginTitle.setText(R.string.login);
                textLineRegister.setText(R.string.dont_have_an_account);
                clickableRegister.setText(R.string.register_now);
                isLogin = true;
            }
//            startActivity(new Intent(this, RegisterActivity.class));

        });
    }

    public void findViews(){
        etLoginEmail = findViewById(R.id.et_login_email);
        etLoginPassword = findViewById(R.id.et_login_password);
        btnLogin = findViewById(R.id.btn_login);
        textLineRegister = findViewById(R.id.tv_login_register);
        progressBar = findViewById(R.id.login_progressBar);
        loginTitle = findViewById(R.id.tv_login_title);
        clickableRegister = findViewById(R.id.tv_login_register_click);
    }
    @Override
    public void onClick(View view) {

        String email = etLoginEmail.getText().toString();
        String password = etLoginPassword.getText().toString();

        boolean isEmailValid = AuthCheckTool.isValidEmail(email);
        boolean isPasswordValid = AuthCheckTool.isValidPassword(password);

        if(isEmailValid && isPasswordValid){

            progressBar.setVisibility(View.VISIBLE);


                if(isLogin){
                    FirebaseAuth.getInstance()
                            .signInWithEmailAndPassword(email,password)
                            .addOnSuccessListener(authResult->{
                                onSuccess();
                            })
                            .addOnFailureListener(e -> {
                                onFailure();
                            });
                }else{
                    FirebaseAuth.getInstance()
                            .createUserWithEmailAndPassword(email,password)
                            .addOnSuccessListener(authResult->{
                                onSuccess();
                            })
                            .addOnFailureListener(e -> {
                                onFailure();
                            });
                }


        }else{
            if(!isEmailValid){
                Toast.makeText(this,"Email not Valid",Toast.LENGTH_SHORT).show();
            }
            if(!isPasswordValid){
                Toast.makeText(this,"Password not Valid",Toast.LENGTH_SHORT).show();
            }
        }




    }
    private void onSuccess(){
        progressBar.setVisibility(View.GONE);
        Log.d("Login","success");
        startActivity(new Intent(this, MainActivity.class));


    }
    private void onFailure(){
        progressBar.setVisibility(View.GONE);
        Toast.makeText(this,"fail to login",Toast.LENGTH_SHORT).show();
    }
}