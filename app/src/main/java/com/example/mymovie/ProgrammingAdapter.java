package com.example.mymovie;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

class ProgrammingAdapter extends FirebaseRecyclerAdapter<Notesmodel, ProgrammingAdapter.Viewholder> {

    Context context;

    public ProgrammingAdapter(@NonNull FirebaseRecyclerOptions<Notesmodel> options,Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull final Viewholder holder, final int position, @NonNull Notesmodel model) {

        Log.d("dzbdhb", "onBindViewHolder: "+model.getTitle());
        holder.title.setText(model.getTitle());
        holder.description.setText(model.getNots());

        holder.card.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                AlertDialog.Builder alert=new AlertDialog.Builder(context);

                final View dialogView= LayoutInflater.from(context).inflate(R.layout.dilog,null,false);
                alert.setView(dialogView);

                final AlertDialog alertDialog=alert.create();
                alertDialog.show();

                Button yes=dialogView.findViewById(R.id.yes);
                Button no=dialogView.findViewById(R.id.no);

                yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        FirebaseDatabase.getInstance().getReference().child("ankit")
                                .child(getRef(position).getKey())
                                .removeValue()
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        alertDialog.cancel();
                                    }
                                });
                    }
                });

                no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                  alertDialog.cancel();

                    }
                });


                return false;
            }
        });


    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.i,parent,false);
        return new Viewholder(view);
    }


   public class Viewholder extends RecyclerView.ViewHolder {

        TextView title,description;
        CardView card;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            card = itemView.findViewById(R.id.card);
        }
    }
}
