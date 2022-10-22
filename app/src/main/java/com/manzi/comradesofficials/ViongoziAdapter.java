package com.manzi.comradesofficials;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.net.CookieHandler;
import java.util.List;

public class ViongoziAdapter extends RecyclerView.Adapter<ViongoziAdapter.viewHolder> {

    Context context;
    List<ViongoziModel> viongoziModelList;


    public ViongoziAdapter(Context context, List<ViongoziModel> viongoziModelList) {
        this.context = context;
        this.viongoziModelList = viongoziModelList;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.design_row_for_recyclerview,parent,false);
        //design row connectivity here

        return new viewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        ViongoziModel viongoziModel=viongoziModelList.get(position);
        holder.tvname.setText("Name: "+ viongoziModel.getName());
        holder.tvannouncement.setText( viongoziModel.getAnnouncement());

        String imageUri=null;
        imageUri= viongoziModel.getImage();
        Picasso.get().load(imageUri).into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        return viongoziModelList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        //declare design
        ImageView imageView;
        TextView tvname, tvannouncement;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.imageView_recyclerview_id);
            tvname=itemView.findViewById(R.id.textView_recyclerview_name);
            tvannouncement=itemView.findViewById(R.id.textView_recyclerview_announce);

        }
    }
}
