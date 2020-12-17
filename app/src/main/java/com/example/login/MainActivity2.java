package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity2 extends AppCompatActivity {
    private EditText txt_id, txt_pass1, txt_pass2,txt_email;
    private Button button_Create;
    private SQLite sqLite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    //anh xa
        txt_id          = (EditText) findViewById(R.id.txtID);
        txt_pass1       = (EditText) findViewById(R.id.txtPass1);
        txt_pass2       = (EditText) findViewById(R.id.txtPass2);
        txt_email       = (EditText) findViewById(R.id.txtEmail);
        button_Create   = (Button) findViewById(R.id.btnCreate);
        sqLite = new SQLite(this, "taikhoan.sqlite",null, 1);

        //setAction
        button_Create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(txt_id.getText()) && !TextUtils.isEmpty(txt_pass1.getText()) && !TextUtils.isEmpty(txt_pass2.getText()) && !TextUtils.isEmpty(txt_email.getText())) {
                    String id = String.valueOf(txt_id.getText());
                    String pass1 = String.valueOf(txt_pass1.getText());
                    String pass2 = String.valueOf(txt_pass1.getText());
                    String email = String.valueOf(txt_email.getText());
                    if(pass1.equals(pass2)){
                            sqLite.queryData("INSERT INTO taikhoan VALUES(null,'"+ id +"','" + pass1 +"','" + email +"')");
                    }else{
                        Toast.makeText(MainActivity2.this,"Mày coi lại 2 cái pass dùm t cái",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Log.d("addUser", "FailInput");
                }


                }
        });

    }
    }
