package com.example.wee;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ContactActivity extends AppCompatActivity {

    EditText edtName;
    EditText edtContact;
    EditText edtEmail;
    Button btnSave;
    Button btngetcontact;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        init();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // on click it will execute
                toastMessage("clicked");
                save();
            }

        });
    }

    private void init(){
        edtName = findViewById(R.id.edtName);
        edtContact = findViewById(R.id.edtNumber);
        edtEmail =  findViewById(R.id.edtEmail);
        btnSave = findViewById(R.id.btnSave);
        btngetcontact = findViewById(R.id.btngetcontact);
        btngetcontact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContactActivity.this,DetailActivity.class);
                startActivity(intent);

            }
        });

    }

    private void toastMessage(String data){
        Toast.makeText(this,data,Toast.LENGTH_SHORT).show();

    }
    private void save(){
        String name = edtName.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();
        String contact = edtContact.getText().toString().trim();


        if(name.length()<3){
            toastMessage("Please enter name more than 3 characters");
            return;
        }

        else if(contact.length()<10) {
            toastMessage("Please enter number more than 10 characters");
            return;
        }
        else if(!email.matches(emailPattern)) {
            toastMessage("Please enter valid email");
            return;
        }


        else {
            saveContact();


        }
    }

    void saveContact(){
        SaveContacts gt = new SaveContacts();
        gt.execute();
    }


    class SaveContacts extends AsyncTask<Void, Void, Long>

    {

        @Override
        protected Long doInBackground(Void... voids) {
            Contact contact = new Contact();
            contact.phoneNumber = edtContact.getText().toString().trim();

            contact.name = edtName.getText().toString().trim();
            contact.email = edtEmail.getText().toString().trim();
            Long status = DatabaseClient.getInstance(getApplication()).getAppDatabase().OpDao().insert(contact);
            return status;
        }

        @Override
        protected void onPostExecute(Long status) {
            super.onPostExecute(status);
            Log.e("SaveContacts status",""+status);
            if(status>0) {
                toastMessage("successfully added");
                edtName.setText("");
                edtContact.setText("");
                edtEmail.setText("");
            }
            else {
                toastMessage("failed to add");
            }
        }
    }

}