package com.example.hussain.mathsyafeedback;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.media.Rating;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.text.Selection;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Toast;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import java.util.StringTokenizer;


public class test extends Activity
{
        String sat,pass,value,help,selection,quality,name,address,tel,mob,email,suggestion,bday,anniversary,source;
    private String  URL_NEW_PREDICTION = "http://mathsya.netau.net/matPhp.php";
    EditText ed1,ed2,ed3,ed4,ed5,ed6,ed7,ed8;
    Button submitButton;
    RadioGroup rg1;
    RadioButton rb1,rb2,rb3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pass = getIntent().getExtras().getString("arg");
        String result[]=pass.split("\\s");
        sat=result[0];
        value=result[1];
        help=result[2];
        selection=result[3];
        quality=result[4];
        ed1=(EditText)findViewById(R.id.editText1);
        ed2=(EditText)findViewById(R.id.editText3);
        ed3=(EditText)findViewById(R.id.editText4);
        ed4=(EditText)findViewById(R.id.editText5);
        ed5=(EditText)findViewById(R.id.editText7);
        ed6=(EditText)findViewById(R.id.editText8);
        ed7=(EditText)findViewById(R.id.editText9);
        ed8=(EditText)findViewById(R.id.editText10);
        submitButton = (Button) findViewById(R.id.submit);
        submitButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                 name=ed1.getText().toString();
                address=ed2.getText().toString();
                tel=ed3.getText().toString();
                mob=ed4.getText().toString();
                email=ed5.getText().toString();
                bday=ed6.getText().toString();
                anniversary=ed7.getText().toString();
                suggestion=ed8.getText().toString();
                rg1=(RadioGroup)findViewById(R.id.myRadioGroup);
                rb1=(RadioButton)findViewById(R.id.radioButton);
                rb2=(RadioButton)findViewById(R.id.radioButton2);
                rb3=(RadioButton)findViewById(R.id.radioButton3);
                rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if(rb1.isChecked())

                            source="Radio";

                        if(rb2.isChecked())
                            source="Advertisment";
                        if(rb3.isChecked())
                            source="Other";


                    }
                });

                new AddNewPrediction().execute(sat,value,help,selection,quality,name,address,tel,mob,email,bday,anniversary,source,suggestion);
                Toast.makeText(getApplicationContext(),"Data Submitted",Toast.LENGTH_SHORT).show();
            }
        });

    }
    private class AddNewPrediction extends AsyncTask<String, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(String... arg) {
            // TODO Auto-generated method stub
            String Sat=arg[0];
            String Val=arg[1];
            String help=arg[2];
            String sel=arg[3];
            String qual=arg[4];
             String name = arg[5];
            String address = arg[6];
            String tel = arg[7];
            String mob = arg[8];
            String email = arg[9];
            String bday= arg[10];
            String anniversary= arg[11];
            String source = arg[12];
            String sug=arg[13];



            // Preparing post params
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("satisfaction", Sat));
            params.add(new BasicNameValuePair("value", Val));
            params.add(new BasicNameValuePair("help",help));
            params.add(new BasicNameValuePair("selection",sel));
            params.add(new BasicNameValuePair("quality",qual));
            params.add(new BasicNameValuePair("name",name));
            params.add(new BasicNameValuePair("address",address));
            params.add(new BasicNameValuePair("tel",tel));
            params.add(new BasicNameValuePair("mob",mob));
            params.add(new BasicNameValuePair("email",email));
            params.add(new BasicNameValuePair("bday",bday));
            params.add(new BasicNameValuePair("anniversary",anniversary));
            params.add(new BasicNameValuePair("source",source));
            params.add(new BasicNameValuePair("suggestion",sug));
            Sender serviceClient = new Sender();

            String json = serviceClient.makeServiceCall(URL_NEW_PREDICTION,
                    Sender.POST, params);

            Log.d("Create Prediction Request: ", "> " + json);

            if (json != null) {
                try {
                    JSONObject jsonObj = new JSONObject(json);
                    boolean error = jsonObj.getBoolean("error");
                    // checking for error node in json
                    if (!error) {
                        // new category created successfully
                        Log.e("Prediction added successfully ",
                                "> " + jsonObj.getString("message"));
                    } else {
                        Log.e("Add Prediction Error: ",
                                "> " + jsonObj.getString("message"));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } else {
                Log.e("JSON Data", "JSON data error!");
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
        }
    }




    }

