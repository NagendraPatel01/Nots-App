package com.example.mymovie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class NotsActivity extends AppCompatActivity {

    FloatingActionButton Flotbutton;
    RecyclerView recycler;
    ProgrammingAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nots);

        recycler = findViewById(R.id.recycler);
        Flotbutton = findViewById(R.id.Flotbutton);

        LinearLayoutManager layoutManager= new LinearLayoutManager(this);
        recycler.setLayoutManager(layoutManager);


        FirebaseRecyclerOptions<Notesmodel> options = new FirebaseRecyclerOptions.Builder<Notesmodel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("ankit"), Notesmodel.class)
                        .build();

         adapter=new ProgrammingAdapter(options,NotsActivity.this);
        recycler.setAdapter(adapter);


        Flotbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(NotsActivity.this,InboxActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}