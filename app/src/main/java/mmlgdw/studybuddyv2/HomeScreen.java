package mmlgdw.studybuddyv2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeScreen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);


        // Button to go to Take Quiz Screen
        Button goToTakeQuiz = (Button) findViewById(R.id.takeQuiz);
        goToTakeQuiz.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent = new Intent(HomeScreen.this, TakeQuiz.class);
                startActivity(intent);
            }
        });
        // Button to go to Edit Quiz Screen
        Button goToEditQuiz = (Button) findViewById(R.id.editQuiz);
        goToEditQuiz.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent = new Intent(HomeScreen.this, EditQuiz.class);
                startActivity(intent);
            }
        });
        // Button to go to View Flashcards Screen
        Button goToViewFlashcards = (Button) findViewById(R.id.viewCards);
        goToViewFlashcards.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent = new Intent(HomeScreen.this, ViewFlashcards.class);
                startActivity(intent);
            }
        });
        // Button to go to Edit Flashcards Screen
        Button goToEditFlashcards = (Button) findViewById(R.id.editCards);
        goToEditFlashcards.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent = new Intent(HomeScreen.this, EditFlashcards.class);
                startActivity(intent);
            }
        });
        // Return to Login Screen
        Button goToLoginScreen = (Button) findViewById(R.id.logout);
        goToLoginScreen.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent = new Intent(HomeScreen.this, LoginScreen.class);
                startActivity(intent);
            }
        });
    }
}