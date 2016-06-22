package com.example.hussain.mathsyafeedback;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.AsyncTask;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.hussain.mathsyafeedback.R.id.ratingBar;

public class MainActivity extends Activity {

String res="";
    RatingBar rb1,rb2,rb3,rb4,rb5;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen1);
        addListenerOnRatingBar();
    }
    public void addListenerOnRatingBar() {

        rb1 = (RatingBar) findViewById(R.id.ratingBar);
        rb2 = (RatingBar) findViewById(R.id.ratingBar2);
        rb3 = (RatingBar) findViewById(R.id.ratingBar3);
        rb4 = (RatingBar) findViewById(R.id.ratingBar4);
        rb5 = (RatingBar) findViewById(R.id.ratingBar5);

        Button b=(Button)findViewById(R.id.button);
                b.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        res= String.valueOf(rb1.getRating())+" "+String.valueOf(rb2.getRating())+" "+String.valueOf(rb3.getRating())+" "+String.valueOf(rb4.getRating())+" "+String.valueOf(rb5.getRating());



                        Intent intent = new Intent(MainActivity.this, test.class);
        intent.putExtra("arg", res);
        startActivity(intent);
                    }
                });

    }

}



