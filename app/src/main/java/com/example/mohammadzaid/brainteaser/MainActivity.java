package com.example.mohammadzaid.brainteaser;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;

public class MainActivity extends AppCompatActivity {
    Button button1 ;
    Button button2;
    Button button3 ;
    Button button4;
    int correctpos;
    TextView sum;
    int correctans=0;
    TextView score;
    int totalquestions=0;
    TextView time;
    TextView maxscore;
    CountDownTimer timer;
    SharedPreferences sharedPreferences;
    ArrayList<Integer> arrayList = new ArrayList<Integer>();
    public void check(View view)
    {
       if(view.getTag().toString().equals(Integer.toString(correctpos)))
       {
           Toast.makeText(MainActivity.this,"Correct !",Toast.LENGTH_SHORT).show();
           correctans++;
            totalquestions++;
           score.setText(Integer.toString(correctans)+"/"+Integer.toString(totalquestions));
           random();
       }
       else{
           Toast.makeText(MainActivity.this,"Wrong !",Toast.LENGTH_SHORT).show();
       totalquestions++;
        score.setText(Integer.toString(correctans)+"/"+Integer.toString(totalquestions));
       random();
    }
    }
    public void random()
    {


        Random random = new Random();
        int a = random.nextInt(20);
        int b =random.nextInt(20);
        int c= a+b;
        sum.setText(Integer.toString(a)+"+"+Integer.toString(b));
        arrayList.clear();
        correctpos = random.nextInt(4);


        for(int i=0;i<4;i++)
        {
            if(i==correctpos)
            {
                arrayList.add(c);
            }
            else
            {
                int wronganswer1 = random.nextInt(40);
                arrayList.add(wronganswer1);
            }
        }

        button1.setText(Integer.toString(arrayList.get(0)));
        button2.setText(Integer.toString(arrayList.get(1)));
        button3.setText(Integer.toString(arrayList.get(2)));
        button4.setText(Integer.toString(arrayList.get(3)));


    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1=(Button)findViewById(R.id.button1);
        button2=(Button)findViewById(R.id.button2);
        button3=(Button)findViewById(R.id.button3);
        button4=(Button)findViewById(R.id.button4);
        sum=(TextView)findViewById(R.id.sum);
        score = (TextView)findViewById(R.id.score);
        time =(TextView)findViewById(R.id.time);
        maxscore =(TextView)findViewById(R.id.maxscore);
        sharedPreferences = getSharedPreferences("high_score",Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        maxscore.setText(sharedPreferences.getString("high","0"));
        random();

        timer = new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished)
            {
                time.setText("" + millisUntilFinished / 1000);
            }

            public void onFinish() {
                Toast.makeText(MainActivity.this, "Game is Finished", Toast.LENGTH_LONG).show();
                button1.setVisibility(View.INVISIBLE);
                button2.setVisibility(View.INVISIBLE);
                button3.setVisibility(View.INVISIBLE);
                button4.setVisibility(View.INVISIBLE);
                String highscore = sharedPreferences.getString("high", "0");
                int h = Integer.parseInt(highscore);
                if (h < correctans)
                {
                    editor.putString("high",Integer.toString(correctans));
                    editor.commit();
                }
                maxscore.setText(sharedPreferences.getString("high","0"));
                }


        };
        timer.start();

    }
}
