package edu.csumb.rhuskinshw1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private EditText name;
    private EditText password;
    private Button loginButton;
    private List<User> users = new ArrayList<User>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.usernameText);
        password = findViewById(R.id.passwordText);
        loginButton = findViewById(R.id.loginButton);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/").addConverterFactory(GsonConverterFactory.create()).build();
        JsonApi jsonApi = retrofit.create(JsonApi.class);
        Call<List<User>> call = jsonApi.getUsers();

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),"API Error",Toast.LENGTH_SHORT).show();
                }

                List<User> x = response.body();
                users.addAll(x);
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });
        System.out.println("hi");
    }

    public void nextActivity(View view) {

        int userID = -1;
        String username = "";
        for(User x: users) {
            if (x.getEmail().toLowerCase(Locale.ROOT).equals(name.getText().toString().toLowerCase(Locale.ROOT))) {
                userID = x.getId();
                username = x.getUsername();
                break;
            }
        }

        name.setTextColor(Color.BLACK);
        password.setTextColor(Color.BLACK);


        if (userID == -1) {
            name.setTextColor(Color.RED);
            Toast.makeText(getApplicationContext(),"Invalid Username",Toast.LENGTH_SHORT).show();
        } else if (!LoginUtils.verifyPassword(password.getText().toString(), userID-1)) {
            password.setTextColor(Color.RED);
            Toast.makeText(getApplicationContext(),"Invalid Password",Toast.LENGTH_SHORT).show();
        }
        else {
            Intent intent = LandingPage.getIntent(getApplicationContext(),username, userID);
            startActivity(intent);
        }


    }
}