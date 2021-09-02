package edu.csumb.rhuskinshw1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public static boolean verifyUsername(String username) {
        return username.equals("din_djarin");
    }

    public void nextActivity(View view) {
        Intent intent = LandingPage.getIntent(getApplicationContext(), "hello!");

        startActivity(intent);
    }
}