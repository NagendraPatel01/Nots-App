package com.example.mymovie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class InboxActivity extends AppCompatActivity {

    EditText edit1,edit2;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox);

        edit1=findViewById(R.id.edit1);
        edit2=findViewById(R.id.edit2);
        btn=findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HashMap<Object,String> hashMap=new HashMap<>();

                hashMap.put("Title", edit1.getText().toString());
                hashMap.put("Nots", edit2.getText().toString());

                FirebaseDatabase.getInstance().getReference().child("ankit").push().setValue(hashMap);

                Intent intent =new Intent(InboxActivity.this,NotsActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}