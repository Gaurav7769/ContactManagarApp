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

public class RegisterActivity extends AppCompatActivity {

    EditText edtMail;
    EditText edtPassword;
    EditText edtConfirmPassword;
    Button   btnSignUp;
    TextView tvSignIn;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init();

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // on click it will execute
                signup();
            }

        });

        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
              startActivity(intent);
              finish();


            }
        });


    }

    private void init(){
        edtMail = findViewById(R.id.edtMail);
        edtPassword = findViewById(R.id.edtPassword);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);
        btnSignUp = findViewById(R.id.btnSignUp);
        tvSignIn = findViewById(R.id.tvSignIn);
    }

    private void toastMessage(String data){
        Toast.makeText(this,data,Toast.LENGTH_SHORT).show();

    }
    private void signup(){
        String email = edtMail.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();
        String confirmPassword = edtConfirmPassword.getText().toString().trim();

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
        else if(confirmPassword.length()<3){
            toastMessage("Please enter confirm password more than 3 characters");
            return;
        }
       else if(!password.equals(confirmPassword)) {
            toastMessage("password not matching");
            return;

        }
       else {
           saveUser();

        }
    }

    void saveUser(){
        SaveUser gt = new SaveUser();
        gt.execute();

    }


    class SaveUser extends AsyncTask<Void, Void, Long>

    {

        @Override
        protected Long doInBackground(Void... voids) {
            User user = new User();
            user.password = edtPassword.getText().toString().trim();
            user.username = edtMail.getText().toString().trim();


            Long status = DatabaseClient.getInstance(getApplication()).getAppDatabase().OpDao().insert(user);
            return status;
        }

        @Override
        protected void onPostExecute(Long status) {
            super.onPostExecute(status);
            Log.e("SaveContacts status",""+status);
            if(status>0) {
                toastMessage("successfully added");
                edtMail.setText("");
                edtPassword.setText("");
                edtConfirmPassword.setText("");
                Intent intent = new Intent(RegisterActivity.this, ContactActivity.class);
                startActivity(intent);
                finish();
            }
            else {
                toastMessage("failed to add");
            }
        }
    }
}
