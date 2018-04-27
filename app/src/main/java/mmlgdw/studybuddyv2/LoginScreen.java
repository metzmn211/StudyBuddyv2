package mmlgdw.studybuddyv2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class LoginScreen extends AppCompatActivity {
    Button loginButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        // temporary Button to go to home Screen
        Button goToHomeScreen = (Button) findViewById(R.id.login_button);
        goToHomeScreen.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent = new Intent(LoginScreen.this, HomeScreen.class);
                startActivity(intent);
            }
        });

    }
}