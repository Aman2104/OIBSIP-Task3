package com.example.oibsip_task4;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView questionTextView;
    private RadioGroup optionsRadioGroup;
    private Button nextButton;

    private String[] questions = {
            "Q. Which keyword is used to create an object in Java?",
            "Q. What is the output of the following code?\n\npublic class Main {\n    public static void main(String[] args) {\n        int x = 5;\n        int y = x++;\n        System.out.println(y);\n    }\n}",
            "Q. What is the time complexity of searching in a balanced binary search tree?",
            "Q. Which of the following sorting algorithms has the best average-case time complexity?",
            "Q. Which of the following access modifiers in Java allows a method to be accessed from anywhere in the same package?"
    };
    private String[][] options = {
            {"new", "this", "create", "allocate"},
            {"5", "6", "0", "Compiler error"},
            {"O(n)", "O(log n)", "O(n log n)", "O(1)"},
            {"Selection Sort", "Bubble Sort", "Insertion Sort", "Merge Sort"},
            {"public", "protected", "private", "default"}
    };
    private int[] correctAnswers = {0, 0, 1, 3, 3};

    private int currentQuestionIndex = 0;
    private int score = 0;
    private int correctCount = 0;
    private int wrongCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionTextView = findViewById(R.id.questionTextView);
        optionsRadioGroup = findViewById(R.id.optionsRadioGroup);
        nextButton = findViewById(R.id.nextButton);

        showQuestion();

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
            }
        });
    }

    private void showQuestion() {
        questionTextView.setText(questions[currentQuestionIndex]);
        optionsRadioGroup.removeAllViews();
        for (int i = 0; i < options[currentQuestionIndex].length; i++) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(options[currentQuestionIndex][i]);
            optionsRadioGroup.addView(radioButton);
        }
    }

    private void checkAnswer() {
        int selectedOptionIndex = optionsRadioGroup.indexOfChild(
                findViewById(optionsRadioGroup.getCheckedRadioButtonId()));

        if (selectedOptionIndex == -1) {
            Toast.makeText(this, "Please select an answer", Toast.LENGTH_SHORT).show();
            return;
        }

        if (selectedOptionIndex == correctAnswers[currentQuestionIndex]) {
            score += 2;
            correctCount++;
        } else {
            wrongCount++;
        }

        currentQuestionIndex++;

        if (currentQuestionIndex < questions.length) {
            showQuestion();
        } else {
            showResult();
        }
    }

    private void showResult() {
        setContentView(R.layout.activity_result);
        TextView correctTextView = findViewById(R.id.correctTextView);
        TextView wrongTextView = findViewById(R.id.wrongTextView);
        TextView totalScoreTextView = findViewById(R.id.totalScoreTextView);

        correctTextView.setText("Correct: " + correctCount);
        wrongTextView.setText("Wrong: " + wrongCount);
        totalScoreTextView.setText("Total Score: " + score);
    }
}
