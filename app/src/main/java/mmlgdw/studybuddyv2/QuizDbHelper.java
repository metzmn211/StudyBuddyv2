package mmlgdw.studybuddyv2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import mmlgdw.studybuddyv2.QuizContract.*;

import java.util.ArrayList;


public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "StudyBuddyQuiz.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NR + " INTEGER, " +
                QuestionsTable.COLUMN_CATEGORY + " TEXT" +
                ")";

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    private void fillQuestionsTable() {
        Question q1 = new Question("What is 2 + 2?",
                "2", "8", "4", 3, Question.CATEGORY_MATH);
        addQuestion(q1);
        Question q2 = new Question("What is 5 x 9?",
                "40", "43", "45", 3, Question.CATEGORY_MATH);
        addQuestion(q2);
        Question q3 = new Question("What is 8 x 7?",
                "56", "65", "55", 1, Question.CATEGORY_MATH);
        addQuestion(q3);
        Question q4 = new Question("What is 72 % 9?",
                "9", "8", "7", 2, Question.CATEGORY_MATH);
        addQuestion(q4);
        Question q5 = new Question("What is the root of 49?",
                "7", "6", "9", 1, Question.CATEGORY_MATH);
        addQuestion(q5);
        Question q6 = new Question("Humans have how many fingers?",
                "3", "14", "10", 3, Question.CATEGORY_SCIENCE);
        addQuestion(q6);
        Question q7 = new Question("What does oxygenated blood flow through?",
                "Veins", "Arteries", "Capsules", 2, Question.CATEGORY_SCIENCE);
        addQuestion(q7);
        Question q8 = new Question("Modern humans are apart of what kingdom?",
                "Plants", "Protists", "Animals", 3, Question.CATEGORY_SCIENCE);
        addQuestion(q8);
        Question q9 = new Question("What does un-oxygenated blood flow through?",
                "Veins", "Arteries", "Capsules", 1, Question.CATEGORY_SCIENCE);
        addQuestion(q9);
        Question q10 = new Question("What is the main artery in humans?",
                "Carotid Artery", "The Aorta", "Radial Artery", 2, Question.CATEGORY_SCIENCE);
        addQuestion(q10);
        Question q11 = new Question("How many oceans are there?",
                "7", "5", "9", 1, Question.CATEGORY_GEOGRAPHY);
        addQuestion(q11);
        Question q12 = new Question("What is the capital of Afghanistan?",
                "Algiers", "Luanda", "Kabul", 3, Question.CATEGORY_GEOGRAPHY);
        addQuestion(q12);
        Question q13 = new Question("What is the largest continent?",
                "Antarctica", "Asia", "Africa", 2, Question.CATEGORY_GEOGRAPHY);
        addQuestion(q13);
        Question q14 = new Question("What is the tallest mountain in the world?",
                "Cho Oyu", "K2", "Mount Everest", 3, Question.CATEGORY_GEOGRAPHY);
        addQuestion(q14);
        Question q15 = new Question("What direction does the Nile River flow?",
                "North", "East", "South", 1, Question.CATEGORY_GEOGRAPHY);
        addQuestion(q15);
    }

    public void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        cv.put(QuestionsTable.COLUMN_CATEGORY, question.getCategory());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }

    public void addNewQuestion(String q, String a1, String a2, String a3, int x, String c) {

        db = getWritableDatabase();

        String SQL = "INSERT INTO quiz_questions ( QUESTION, OPTION1, OPTION2, OPTION3, ANSWER_NR, CATEGORY ) VALUES ('" + q +
                "', '" + a1 + "', '" + a2 + "', '" + a3 + "', '" + x + "', '" + c + "');";

        Log.d("SQL STRING:   ", SQL);

        db.execSQL(SQL);

    }

    public ArrayList<Question> getAllQuestions() {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setCategory(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }

    public ArrayList<Question> getQuestions(String category) {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();

        String[] selectionArgs = new String[]{category};
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME +
                " WHERE " + QuestionsTable.COLUMN_CATEGORY + " = ?", selectionArgs);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setCategory(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }
}