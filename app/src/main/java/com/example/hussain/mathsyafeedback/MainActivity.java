package com.example.hussain.mathsyafeedback;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity  {


    EditText Satisfaction, Help, Value, Quality, Selection,Suggestion;
    Button submitButton;
    private String  URL_NEW_PREDICTION = "http://mathsya.netau.net/phpcode.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Satisfaction = (EditText) findViewById(R.id.editText5);
        Value = (EditText) findViewById(R.id.editText1);
        Help = (EditText) findViewById(R.id.editText2);
        Selection = (EditText) findViewById(R.id.editText3);
        Quality = (EditText) findViewById(R.id.editText4);
        Suggestion = (EditText) findViewById(R.id.editText6);
        submitButton = (Button) findViewById(R.id.send);
        submitButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                String Sat=Satisfaction.getText().toString();
                String Val=Value.getText().toString();
                String help=Help.getText().toString();
                String sel=Selection.getText().toString();
                String qual=Quality.getText().toString();
                String sug=Suggestion.getText().toString();
                new AddNewPrediction().execute(Sat, Val, help, sel, qual, sug);
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
                String sug=arg[5];


                // Preparing post params
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("satisfaction", Sat));
                params.add(new BasicNameValuePair("value", Val));
                params.add(new BasicNameValuePair("help",help));
                params.add(new BasicNameValuePair("selection",sel));
                params.add(new BasicNameValuePair("quality",qual));
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


