package com.kanjistudy.views.quiz;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.kanjistudy.R;

public class QuizActivity extends AppCompatActivity {

    private TextView questionTextView;
    private ProgressBar mainProgressBar;
    private TextView progressText;
    private Button firstOption, secondOption, thirdOption, fourthOption;


    AlertDialog.Builder alertDialog;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_activity_multiple_options);

        // register our buttons to listen to click events
        questionTextView = findViewById(R.id.questionTextView);


        //We initialize this when the button is pressed to figure out which is the value
        mainProgressBar = findViewById(R.id.simpleProgressBar);
        progressText = findViewById(R.id.progressText);

        firstOption = findViewById(R.id.firstOptionButtonQuiz);
        secondOption = findViewById(R.id.secondOptionButtonQuiz);
        thirdOption = findViewById(R.id.thirdOptionButtonQuiz);
        fourthOption = findViewById(R.id.fourthOptionButtonQuiz);


    }

}
