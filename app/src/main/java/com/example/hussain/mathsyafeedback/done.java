package com.example.hussain.mathsyafeedback;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class done extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done);
        Button b=(Button)findViewById(R.id.submit);
        b.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
        Intent i=new Intent(done.this,MainActivity.class);
        startActivity(i);
        finish();
    }
});

    }
}
