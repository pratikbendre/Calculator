package com.android.calculator.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.calculator.R;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;


    public class cal_history extends RecyclerView.Adapter<cal_history.ViewHolder> {

        public static Context context;
        ArrayList<DocumentSnapshot> calculationlist;

        public cal_history(Context mcontext, ArrayList<DocumentSnapshot> calculationlist) {
            super();
            this.context = mcontext;
            this.calculationlist = calculationlist;
        }


        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_calculations, parent, false);
            ViewHolder viewHolder = new ViewHolder(v);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

            Log.d("check adapter",calculationlist.toString());
            holder.eqntv.setText(calculationlist.get(position).get("all_eqnarray").toString());
            holder.sumtv.setText(calculationlist.get(position).get("all_sumarray").toString());

        }


        @Override
        public int getItemCount() {  return calculationlist == null ? 0 : calculationlist.size();
        }



        public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {


            TextView eqntv;
            TextView sumtv;




            public ViewHolder(@NonNull View itemView) {
                super(itemView);

                eqntv = itemView.findViewById(R.id.eqn);
                sumtv = itemView.findViewById(R.id.sum);



            }

            @Override
            public void onClick(View view) {

            }

            @Override
            public boolean onLongClick(View view) {
                return false;
            }
        }
    }

