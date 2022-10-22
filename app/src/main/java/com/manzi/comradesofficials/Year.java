package com.manzi.comradesofficials;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Year extends AppCompatActivity implements View.OnClickListener{

    RadioButton radioBtn1, radioBtn2, radioBtn3, radioBtn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_year);

        radioBtn1 = (RadioButton) findViewById(R.id.radioBtnyear1);
        radioBtn2 = (RadioButton) findViewById(R.id.radioBtnyear2);
        radioBtn3 = (RadioButton) findViewById(R.id.radioBtnyear3);
        radioBtn4 = (RadioButton) findViewById(R.id.radioBtnyear4);

        radioBtn1.setOnClickListener(this);
        radioBtn2.setOnClickListener(this);
        radioBtn3.setOnClickListener(this);
        radioBtn4.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.radioBtnyear1:
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                break;

            case R.id.radioBtnyear2:
                Intent intent1 = new Intent(getApplicationContext(), MainActivity2.class);
                startActivity(intent1);
                break;
            case R.id.radioBtnyear3:
                Intent intent2 = new Intent(getApplicationContext(), MainActivity3.class);
                startActivity(intent2);
                break;
            case R.id.radioBtnyear4:
                Intent intent3 = new Intent(getApplicationContext(), MainActivity4.class);
                startActivity(intent3);
                break;

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Check out this cool application");
        intent.putExtra(Intent.EXTRA_TEXT, "your application link here");
        startActivity(Intent.createChooser(intent, "Share via"));

        return super.onOptionsItemSelected(item);
    }

}
