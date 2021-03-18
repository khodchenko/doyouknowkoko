package com.example.doyouknowkoko;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

// FirebaseRecyclerAdapter is a class provided by
// FirebaseUI. it provides functions to bind, adapt and show
// database contents in a Recycler View
public class PersonAdapter extends FirebaseRecyclerAdapter<
        Person, PersonAdapter.personsViewholder> {
    private static final String TAG = "PersonAdapter";
    public PersonAdapter(
            @NonNull FirebaseRecyclerOptions<Person> options)
    {
        super(options);
    }

    // Function to bind the view in Card view(here
    // "person.xml") with data in
    // model class(here "person.class")
    @Override
    protected void onBindViewHolder(@NonNull personsViewholder holder,
                     int position, @NonNull Person model)
    {

        // Add first name from model class (here
        // "person.class")to appropriate view in Card
        // view (here "person.xml")
        Log.i(TAG, "onBindViewHolder: sending");
        holder.name.setText(model.getName());
        holder.brand.setText(model.getBrand());
        holder.size.setText(model.getSize());
        holder.comment.setText(model.getComment());
        holder.price.setText(model.getPrice());

    }

    // Function to tell the class about the Card view (here
    // "person.xml")in
    // which the data will be shown
    @NonNull
    @Override
    public personsViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false);
        return new personsViewholder(view);
    }

    // Sub Class to create references of the views in Cards
    // view (here "person.xml")
    static class personsViewholder extends RecyclerView.ViewHolder { TextView name, brand, size, comment, price;
        public personsViewholder(@NonNull View itemView)
        {
            super(itemView);

            name = itemView.findViewById(R.id.tv_name_value);
            brand = itemView.findViewById(R.id.tv_brand_value);
            size = itemView.findViewById(R.id.tv_size_value);
            comment = itemView.findViewById(R.id.tv_comment_value);
            price = itemView.findViewById(R.id.tv_price_value);
        }
    }
}