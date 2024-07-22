package com.example.task_bonum;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private List<Userdata>userList;
    // Constructor to initialize the user list
    public UserAdapter(List<Userdata>userList){
        this.userList=userList !=null ? userList : new ArrayList<Userdata>();
    }

    // Inflates the item layout and creates the ViewHolder object
    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user,parent,false);
        return new UserViewHolder(view);


    }

    // Binds the data to the ViewHolder
    @Override
    public void onBindViewHolder(@NonNull UserAdapter.UserViewHolder holder, int position) {
    Userdata user=userList.get(position);
    Log.d("UserAdapter", "Binding user: " + user.getName() + ", " + user.getEmail() + ", " + user.getAdress() + ", " + user.getProf());
    holder.name.setText(user.getName());
    holder.emai.setText(user.getEmail());
    holder.addres.setText(user.getAdress());
    holder.prof.setText(user.getProf());
    }
    // Returns the total number of items
    @Override
    public int getItemCount() {
        return userList.size();
    }
    // Method to update the list
    public void setUserList(List<Userdata> userList) {
        this.userList = userList != null ? userList : new ArrayList<Userdata>();
        notifyDataSetChanged(); // Notify adapter that data has changed
    }

    // ViewHolder class to hold the item layout views
    public static class UserViewHolder extends  RecyclerView.ViewHolder {
        TextView name,emai,addres,prof;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.nametextView);
            emai=itemView.findViewById(R.id.emailtextView);
            addres=itemView.findViewById(R.id.addrestextView);
            prof=itemView.findViewById(R.id.proftextView);
        }
    }
}
