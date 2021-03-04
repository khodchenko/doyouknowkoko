package com.example.doyouknowkoko;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    RecyclerView recyclerView;

    private DatabaseReference databaseReference;

    private ArrayList<ReceiveData> receiveDataArrayList;
    private RecyclerAdapter2 recyclerAdapter;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recyclerView = findViewById(R.id.recyclerview_xml);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        receiveDataArrayList = new ArrayList<>();

        clearAll();

        getDataFromDataBase();
    }

    private void getDataFromDataBase() {

        Query query = databaseReference;//TODO CHILD

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    ReceiveData receiveData = new ReceiveData();
                    receiveData.setOutfitName(snapshot.child("outfitName").getValue().toString());

                    receiveDataArrayList.add(receiveData);
                }

                recyclerAdapter = new RecyclerAdapter2(context,receiveDataArrayList );
                recyclerView.setAdapter(recyclerAdapter);
                recyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void clearAll(){
        if(receiveDataArrayList != null){
            receiveDataArrayList.clear();

            if(recyclerAdapter!=null){
                recyclerAdapter.notifyDataSetChanged();
            }
        }
        receiveDataArrayList = new ArrayList<>();
    }
}