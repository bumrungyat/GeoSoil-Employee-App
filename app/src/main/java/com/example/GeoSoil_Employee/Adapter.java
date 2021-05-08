package com.example.GeoSoil_Employee;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private List<String> fullnames,nicknames,phonenumbers,officelevels,images;
    private LayoutInflater inflater;

    Adapter(Context context, List<String> fullnames,List<String> nicknames, List<String> phonenumbers, List<String> officenames ,List<String> images){
        Log.d("data", "fullnames -> "+fullnames);
        this.fullnames = fullnames;
        this.nicknames = nicknames;
        this.phonenumbers = phonenumbers;
        this.officelevels = officenames;
        this.images = images;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String full = fullnames.get(position);
        String nick = nicknames.get(position);
        String phone = phonenumbers.get(position);
        String office = officelevels.get(position);
        String imga =images.get(position);

        holder.fulln.setText(full);
        holder.nickn.setText(nick);
        holder.phonen.setText(phone);
        holder.officel.setText(office);
        // use picasso to download and display image
        Picasso.get().load(imga).into(holder.img);


    }

    @Override
    public int getItemCount() {
        return fullnames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView fulln,nickn,phonen,officel;
        ImageView img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fulln = itemView.findViewById(R.id.fullname);
            nickn = itemView.findViewById(R.id.nickname);
            phonen = itemView.findViewById(R.id.phonenumber);
            officel = itemView.findViewById(R.id.officelevel);
            img = itemView.findViewById(R.id.image);

        }
    }
}