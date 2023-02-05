package edu.northeastern.numadsp_23vinhk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class LaunchLinkCollectorActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    EditText editText;
    FloatingActionButton floatingActionButton;
    ArrayList<Urlmodel> linkcollector = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_link_collector);
        recyclerView = findViewById(R.id.linkcollector);
        floatingActionButton = findViewById(R.id.addurl);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        Linkadapter adapter = new Linkadapter(this, linkcollector);
        recyclerView.setAdapter(adapter);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(LaunchLinkCollectorActivity.this);
                dialog.setContentView(R.layout.url_adder);

                EditText siteName = dialog.findViewById(R.id.editNamedi);
                EditText siteUrl = dialog.findViewById(R.id.editTextUrldi);
                Button btnAction = dialog.findViewById(R.id.btnurladd);

                btnAction.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = "", url = "";
                        if (!siteName.getText().toString().equals("")) {
                            name = siteName.getText().toString();
                        }
                        if (!siteUrl.getText().toString().equals("")) {
                            url = siteUrl.getText().toString();
                        }
                        int color = 0;
                        String text = "";
                        int flag = 0;
                        Urlmodel linkmodel;
                        try {
                            linkmodel = new Urlmodel(name, url);
                            linkcollector.add(linkmodel);
                            text = "Link Added Successfully";
                            color = Color.GREEN;
                        } catch (IllegalArgumentException e) {
                            text = "link not added missing name or url";
                            color = Color.RED;
                            flag = 1;
                        }
                        Snackbar.make(recyclerView, text + "\t \t" + name, Snackbar.LENGTH_INDEFINITE)
                                .setTextColor(color)
                                .setAction("OK", new View.OnClickListener() {

                                    @Override
                                    public void onClick(View v) {
                                    }
                                }).show();
                        if (flag == 0) {
                            adapter.notifyItemInserted(linkcollector.size() - 1);
                            recyclerView.scrollToPosition(linkcollector.size() - 1);
                        }

                        dialog.dismiss();
                    }
                });
                dialog.show();
            }

        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);



    }
}
