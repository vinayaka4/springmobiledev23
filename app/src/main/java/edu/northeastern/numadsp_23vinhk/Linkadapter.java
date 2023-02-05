package edu.northeastern.numadsp_23vinhk;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class Linkadapter extends RecyclerView.Adapter<Linkadapter.ViewHolder> {
    Context context;
    CardView cardView;
    TextView textView;
    ConstraintLayout constraintLayout;
    RecyclerView recyclerView;
    ArrayList<Urlmodel> linkcollector;

    Linkadapter(Context context, ArrayList<Urlmodel> linkcollector) {
        this.context = context;
        this.linkcollector = linkcollector;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View linkview = LayoutInflater.from(context).inflate(R.layout.linkcollector, parent, false);
        ViewHolder viewholder = new ViewHolder(linkview);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.txtname.setText(linkcollector.get(holder.getAdapterPosition()).name);
        holder.txturl.setText(linkcollector.get(holder.getAdapterPosition()).url);
        holder.row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.url_adder);
                EditText siteName = dialog.findViewById(R.id.editNamedi);
                EditText siteUrl = dialog.findViewById(R.id.editTextUrldi);
                Button btnAction = dialog.findViewById(R.id.btnurladd);
                TextView txtview = dialog.findViewById(R.id.AddUrlTitle);
                txtview.setText("UPDATE URL");
                btnAction.setText("UPDATE");

                siteName.setText((linkcollector.get(holder.getAdapterPosition()).name));
                siteUrl.setText(linkcollector.get(holder.getAdapterPosition()).url);

                btnAction.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String name = "", url = "";
                        try {
                            if (!siteName.getText().toString().equals("")) {
                                name = siteName.getText().toString();
                            } else {
                                Toast.makeText(context, "NAME CANNOT BE EMPTY", Toast.LENGTH_SHORT).show();
                            }


                            if (!siteUrl.getText().toString().equals("")) {
                                url = siteUrl.getText().toString();
                            } else {
                                Toast.makeText(context, "URL CANNOT BE EMPTY", Toast.LENGTH_SHORT).show();
                            }


                            linkcollector.set((holder.getAdapterPosition()), new Urlmodel(name, url));

                            notifyItemChanged(holder.getAdapterPosition());
                            Toast.makeText(context, "UPDATE SUCCESSFUL", Toast.LENGTH_SHORT).show();

                            dialog.dismiss();
                        } catch (Exception e) {

                        }


                    }

                });

                dialog.show();

            }
        });

        holder.row.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder dialogue = new AlertDialog.Builder(context).setTitle("DELETE").setMessage("ARE YOU SURE YOU WANT TO DELETE").setIcon(R.drawable.baseline_delete_24).setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                linkcollector.remove(linkcollector.get(holder.getAdapterPosition()));
                                notifyItemRemoved(holder.getAdapterPosition());
                            }


                        })

                        .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }

                        });


                dialogue.show();

                return true;
            }

        });


        //holder.txtname.setText(linkcollector.get(position).name);
        //holder.txturl.setText(linkcollector.get(position).url);

        holder.txturl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent openlink = new Intent(Intent.ACTION_VIEW, Uri.parse(linkcollector.get(holder.getAdapterPosition()).url));
                    context.startActivity(openlink);
                } catch (Exception e) {
                    Toast.makeText(context, "Plz enter link with https://\nex: https://www.yahoo.com", Toast.LENGTH_SHORT).show();
                }

                //  System.out.println(Uri.parse(linkcollector.get(holder.getAdapterPosition()).url));


            }
        });

    }

    @Override
    public int getItemCount() {

        return linkcollector.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtname, txturl;
        ConstraintLayout row;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtname = itemView.findViewById(R.id.textname);
            txturl = itemView.findViewById(R.id.texturl);
            row = itemView.findViewById(R.id.row);
        }
    }
}
