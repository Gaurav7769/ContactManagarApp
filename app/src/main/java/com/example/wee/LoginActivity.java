package com.example.wee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    EditText edtMail;
    EditText edtPassword;
    Button   btnSignIn;
    TextView   btnSignUp;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtMail = findViewById(R.id.edtMail);
        edtPassword = findViewById(R.id.edtPassword);
        btnSignIn = findViewById(R.id.btnSignin);
        btnSignUp = findViewById(R.id.tvSignUp);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();

            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = edtMail.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();


                if(email.length()<3){
                    toastMessage("Please enter email more than 3 characters");
                    return;
                }
                else if(!email.matches(emailPattern)){
                    toastMessage("Please enter valid email");
                    return;

                }
                else if(password.length()<3) {
                    toastMessage("Please enter password more than 3 characters");
                    return;
                }

                else {
                    GetUser g = new GetUser();
                    g.execute();

                }
            }

        });



    }




    private void toastMessage(String data){
        Toast.makeText(this,data,Toast.LENGTH_SHORT).show();

    }
    class GetUser extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... voids) {
            Boolean isExist = DatabaseClient.getInstance(getApplication()).getAppDatabase().OpDao().getUser(edtMail.getText().toString().trim()
           ,edtPassword.getText().toString().trim());
            return isExist;
        }

        @Override
        protected void onPostExecute(Boolean isExist) {
            super.onPostExecute(isExist);
            Log.e("user exist" , ""+isExist);
            if(isExist) {
                Intent intent = new Intent(LoginActivity.this, ContactActivity.class);
                startActivity(intent);
            }
            else{
                toastMessage("user not exist");
            }
        }
    }

}
