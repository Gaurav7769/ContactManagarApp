package com.example.wee;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.MyViewHolder>{


        private List<Contact> contactList;

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView name, number, email;

            public MyViewHolder(View view) {
                super(view);
                name = (TextView) view.findViewById(R.id.name);
                number = (TextView) view.findViewById(R.id.number);
                email = (TextView) view.findViewById(R.id.email);
            }
        }


        public ContactAdapter(List<Contact> moviesList) {
            this.contactList = moviesList;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_contact, parent, false);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            Contact movie = contactList.get(position);
            holder.name.setText(movie.name);
            holder.number.setText(movie.phoneNumber);
            holder.email.setText(movie.email);
        }

        @Override
        public int getItemCount() {
            return contactList.size();
        }
    }

