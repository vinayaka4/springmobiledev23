package edu.northeastern.numadsp_23vinhk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Prime_Activity extends AppCompatActivity {
    private static final String TAG = "_________in runaable thread";
    private Handler textHandler = new Handler();



   private int i=3, jk, k;

    private int count=0;

    String num = "THE NUMBER BEING CURRENTLY SEARCHED IS :  ";

    String prim = "Latest Prime number Found IS :  ";
    private  TextView txtnum, txtprn;

    private  boolean isRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prime);
        txtnum = findViewById(R.id.txtNumber);
        txtprn = findViewById(R.id.txtPrime);
        if (null != savedInstanceState) {
            String number = savedInstanceState.getString("number");
            String primenumber = savedInstanceState.getString("primenumber");
            jk = savedInstanceState.getInt("numb");
            k = savedInstanceState.getInt("prime");

                isRunning = savedInstanceState.getBoolean("thread");
                if(isRunning){
                    i=savedInstanceState.getInt("init");
                    new Thread(new Prime()).start();
                }
            txtnum.setText(number + (String.valueOf(jk)));
            txtprn.setText(primenumber + (String.valueOf(k)));
            Log.d("=====onCreate111=====", jk + " ||| " + k + " ||| " + number + " ||| " + primenumber);

        }


    }

    public void searchPrime(View view) {
        i = 3;
       // System.out.println("jkkkkkkk = "+i);
        isRunning = true;
        new Thread(new Prime()).start();


    }

    public void onTerminate(View view) {
        isRunning = false;
    }

    class Prime implements Runnable {
        @Override
        public void run() {

            System.out.println("JKKKKKKKK = "+i);
            for (; ; i++) {
                jk = i;
                System.out.println("jk = "+jk);
                if (!isRunning) {
                    return;
                }

                textHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        txtnum.setText(num + (String.valueOf(jk)));
                    }
                });

                boolean isPrime = true;
                for (int j = 2; j <= Math.sqrt(i); j++) {
                    if (i % j == 0) {
                        isPrime = false;
                        break;
                    }
                }
                if (isPrime) {
                    k = jk;
                    textHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            txtprn.setText(prim + (String.valueOf(k)));
                        }
                    });
                }
                Log.d(TAG, "thread started" + i);

                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                i++;
            }

        }

    }


    @Override
    public void onBackPressed() {
        if (isRunning==true) {
            new AlertDialog.Builder(this)
                    .setTitle("EXIT PRIME SEARCH")
                    .setMessage("ARE YOU SURE YOU WANT TO TERMINATE SEARCH")
                    .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            isRunning = false;
                            finish();
                        }
                    })
                    .setNegativeButton("NO", null)
                    .show();


        } else {
            finish();
        }
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString("number",num);
        outState.putString("primenumber",prim);
        outState.putInt("init",i);
        outState.putInt("numb",jk);
         outState.putInt("prime",k);
        outState.putBoolean("thread",isRunning);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
