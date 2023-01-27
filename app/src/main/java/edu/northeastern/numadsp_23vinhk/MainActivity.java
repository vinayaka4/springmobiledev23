package edu.northeastern.numadsp_23vinhk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void HandleOnClick(View view){
        Toast.makeText(this,"NAME:VINAYAKA H K \n EMAIL:hosahallikotrappa.v@northeastern.edu" ,Toast.LENGTH_LONG).show();

    }

    public void launchNewActivity(View view){
        Intent i = new Intent(this,ClickyClickyActivity.class);
        startActivity(i);
    }

}