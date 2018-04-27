package mmlgdw.studybuddyv2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class ViewFlashcards extends AppCompatActivity {
    private static final int REQUEST_CODE_QUIZ = 1;
    public static final String EXTRA_CATEGORY = "extraCategory";

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String KEY_HIGHSCORE = "keyHighscore";

    private Spinner spinnerCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_flashcards);

        spinnerCategory = findViewById(R.id.spinner_category);

        String[] Categories = Question.getAllCategories();

        ArrayAdapter<String> adapterCategory = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, Categories);
        adapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapterCategory);


        Button buttonStartQuiz = findViewById(R.id.button_start_quiz);
        buttonStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz();
            }
        });

        Button goToHomeScreen = (Button) findViewById(R.id.go_home);
        goToHomeScreen.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewFlashcards.this, HomeScreen.class);
                startActivity(intent);
            }
        });
    }

    private void startQuiz() {
        String category = spinnerCategory.getSelectedItem().toString();

        Intent intent = new Intent(ViewFlashcards.this, QuizActivity.class);
        intent.putExtra(EXTRA_CATEGORY, category);
        startActivityForResult(intent, REQUEST_CODE_QUIZ);
    }


}

