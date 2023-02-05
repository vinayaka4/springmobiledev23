package edu.northeastern.numadsp_23vinhk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.snackbar.SnackbarContentLayout;


public class ClickyClickyActivity extends AppCompatActivity {

private ConstraintLayout group6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clicky_clicky);
    }

    public void onPress(View view) {
        int theid = view.getId();
        if (theid == R.id.butA) {
            ((TextView) findViewById(R.id.textView4)).setText("PRESSED:A");
        }
        if (theid == R.id.butB) {
            ((TextView) findViewById(R.id.textView4)).setText("PRESSED:B");
        }
        if (theid == R.id.butC) {
            ((TextView) findViewById(R.id.textView4)).setText("PRESSED:C");
        }
        if (theid == R.id.butD) {
            ((TextView) findViewById(R.id.textView4)).setText("PRESSED:D");
        }
        if (theid == R.id.butE) {
            ((TextView) findViewById(R.id.textView4)).setText("PRESSED:E");
        }
        if (theid == R.id.butF) {
            ((TextView) findViewById(R.id.textView4)).setText("PRESSED:F");
        }
    }

}