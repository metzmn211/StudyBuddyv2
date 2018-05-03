package mmlgdw.studybuddyv2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class EditQuiz extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_quiz);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button goHome = findViewById(R.id.homeButton);
        Button addQuestion = findViewById(R.id.addButton);
        final TextView ques = findViewById(R.id.qBox);
        final TextView opt1 = findViewById(R.id.aBox);
        final TextView opt2 = findViewById(R.id.bBox);
        final TextView opt3 = findViewById(R.id.cBox);
        final TextView category = findViewById(R.id.qCat);
        final RadioGroup answerTheRadio = findViewById(R.id.radioGroup);;

        final QuizDbHelper dbh = new QuizDbHelper(this);


        addQuestion.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){

            String question = ques.getText().toString();
            String ans1 = opt1.getText().toString();
            String ans2 = opt2.getText().toString();
            String ans3 = opt3.getText().toString();
            String cat = category.getText().toString();

            RadioButton rbSelected = findViewById(answerTheRadio.getCheckedRadioButtonId());
            int answerNr = answerTheRadio.indexOfChild(rbSelected) + 1;

            //this passes to the function to write the question to the database
            dbh.addNewQuestion(question, ans1, ans2, ans3, answerNr, cat);

            Context ctx = getApplicationContext();
            CharSequence msg = "Added Question!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(ctx, msg, duration);
            toast.show();

            ques.setText("");
            opt1.setText("");
            opt2.setText("");
            opt3.setText("");
            category.setText("");
            answerTheRadio.clearCheck();

            }
        });

        goHome.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent = new Intent(EditQuiz.this, HomeScreen.class);
                startActivity(intent);
            }
        });

    }

}
