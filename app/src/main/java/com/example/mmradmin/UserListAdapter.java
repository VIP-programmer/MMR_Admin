package com.example.mmradmin;

import android.content.Context;
import android.content.Intent;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;

import java.util.Calendar;
import java.util.Vector;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {
    // Store a member variable for the meetings
    private Vector<Medcin> mList;
    private Context context;
    private RequestQueue queue;

    // Pass in the meeting array into the constructor
    public UserListAdapter(Vector<Medcin> mList,Context context) {
        this.mList = mList;
        this.context=context;
        queue= VolleySingleton.getInstance(context).getRequestQueue();
    }

    @NonNull
    @Override
    public UserListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View itemView = inflater.inflate(R.layout.user_item, parent, false);

        // Return a new holder instance
        UserListAdapter.ViewHolder viewHolder = new UserListAdapter.ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserListAdapter.ViewHolder holder, int position) {
        // Get the data model based on position
        Medcin med = mList.get(position);

        // Set item views based on your views and data model
        holder.serie.setText(med.getSerie());
        holder.docName.setText(med.getNom()+" "+med.getPrenom());
        //activeDot.setBackground();
        holder.validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Model(context,queue).giveAcces(med.getCin(), new Model.SignUpCallBack() {
                    @Override
                    public void onSuccess(String message) {
                        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onErr(String message) {
                        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView docName;
        public TextView serie;
        public ImageButton validate;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            docName = (TextView) itemView.findViewById(R.id.doc_name);
            serie = (TextView) itemView.findViewById(R.id.doc_serie);
            validate = itemView.findViewById(R.id.validate_doc);
        }
    }
}