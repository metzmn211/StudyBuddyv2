package mmlgdw.studybuddyv2;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class TakeQuiz extends AppCompatActivity {
    private static final int REQUEST_CODE_QUIZ = 1;
    public static final String EXTRA_CATEGORY = "extraCategory";

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String KEY_HIGHSCORE = "keyHighscore";

    private TextView textViewHighscore;
    private Spinner spinnerCategory;

    private int highscore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_quiz);

        textViewHighscore = findViewById(R.id.text_view_highscore);
        spinnerCategory = findViewById(R.id.spinner_category);

        String[] Categories = Question.getAllCategories();

        ArrayAdapter<String> adapterCategory = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, Categories);
        adapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapterCategory);

        loadHighscore();

        Button buttonStartQuiz = findViewById(R.id.button_start_quiz);
        buttonStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz();
            }
        });

        Button goToHomeScreen = (Button) findViewById(R.id.go_home);
        goToHomeScreen.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent = new Intent(TakeQuiz.this, HomeScreen.class);
                startActivity(intent);
            }
        });
    }

    private void startQuiz() {
        String category = spinnerCategory.getSelectedItem().toString();

        Intent intent = new Intent(TakeQuiz.this, QuizActivity.class);
        intent.putExtra(EXTRA_CATEGORY, category);
        startActivityForResult(intent, REQUEST_CODE_QUIZ);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_QUIZ) {
            if (resultCode == RESULT_OK) {
                int score = data.getIntExtra(QuizActivity.EXTRA_SCORE, 0);
                if (score > highscore) {
                    updateHighscore(score);
                }
            }
        }
    }

    private void loadHighscore() {
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        highscore = prefs.getInt(KEY_HIGHSCORE, 0);
        textViewHighscore.setText("Highscore: " + highscore);
    }

    private void updateHighscore(int highscoreNew) {
        highscore = highscoreNew;
        textViewHighscore.setText("Highscore: " + highscore);

        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_HIGHSCORE, highscore);
        editor.apply();
    }
}