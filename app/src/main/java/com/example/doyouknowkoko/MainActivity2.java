package com.example.doyouknowkoko;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    RecyclerView recyclerView;

    private DatabaseReference databaseReference;

    private ArrayList<ReceiveData> receiveDataArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recyclerView = findViewById(R.id.recyclerview_xml);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        receiveDataArrayList = new ArrayList<>();

        GetDataFromDataBase();
    }

    private void GetDataFromDataBase() {

    }
}