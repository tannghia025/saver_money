package com.example.login;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;


import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    private EditText text_id,text_pass;
    private Button button_login, button_register;
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    public SQLite sqLite;
    private boolean isRight = false;
    private SignInButton signInButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //anh xa

        text_id         = findViewById(R.id.textid);
        text_pass       = findViewById(R.id.textpass);
        button_login    = findViewById(R.id.buttonlogin);
        loginButton     = findViewById(R.id.login_button);
        button_register = findViewById(R.id.buttonregister);
        signInButton    = findViewById(R.id.signInGoogle);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();


        //tao database
        sqLite = new SQLite(this, "taikhoan.sqlite",null, 1);
        sqLite.queryData("CREATE TABLE IF NOT EXISTS taikhoan(Id INTEGER PRIMARY KEY AUTOINCREMENT,taikhoan VARCHAR(50),  pass VARCHAR(15), mail VARCHAR(50))");
        sqLite.queryData("INSERT INTO taikhoan VALUES (null, 'nct','1234','a.@gmail.com')");


        //setAction cho button_login
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor dataInformation = sqLite.getData("SELECT * FROM taikhoan");
//                sqLite.queryData("DELETE FROM taikhoan");
                String id = String.valueOf(text_id.getText());
                String pass = String.valueOf(text_pass.getText());
                if(TextUtils.isEmpty(id) || TextUtils.isEmpty(pass)){
                    Toast.makeText(MainActivity.this, "Mày chưa nhập id hay pass nha tml !!!", Toast.LENGTH_SHORT).show();
                }else{
                    while (dataInformation.moveToNext()){
                        if(id.equals(dataInformation.getString(1)) && pass.equals(dataInformation.getString(2))){
                            Intent it_nav = new Intent(MainActivity.this, FirstPage.class);
                            startActivity(it_nav);
                            Log.d("Login", "Success");
                            isRight = true;
                        }
                    }
                    if(!isRight) Log.d("Login", "Fail ");
                }
            }
        });

        //buttonLogin
        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it_register = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(it_register);
            }
        });

        //setAction cho loginbutton face
        callbackManager = CallbackManager.Factory.create();
        loginButton.setPermissions(Arrays.asList("user_gender"));
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("Demo","Success");
            }

            @Override
            public void onCancel() {
                Log.d("Demo","Cancel");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d("Demo","Error");

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        callbackManager.onActivityResult(requestCode,resultCode,data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}