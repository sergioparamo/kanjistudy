package com.kanjistudy.views.quiz;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.kanjistudy.R;
import com.kanjistudy.controllers.ToastsConfig;
import com.kanjistudy.database.resources.Data;
import com.kanjistudy.models.QuizViewModel;

public class KanjiQuizMultipleOptions extends AppCompatActivity {

    private TextView questionTextView;
    private ProgressBar mainProgressBar;
    private TextView progressText;
    private Button firstOption, secondOption, thirdOption, fourthOption;


    QuizViewModel qViewModel = null;
    AlertDialog.Builder alertDialog;

    public static ToastsConfig toastsConfig;

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


        refreshScreen();


    }

    public void refreshScreen() {
        questionTextView.setText(qViewModel.getQuestion());
        progressText.setText(qViewModel.progressText());
    }

    public void nextQuestion() {

        //We set the progress bar before the current index

        qViewModel.moveToNextQuestion();

        mainProgressBar.setProgress(qViewModel.getIncrementBy());

        questionTextView.setText(qViewModel.getQuestion());

        progressText.setText(qViewModel.progressText());
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void alertDialog() {

        // Once we have finished the quiz we will set the buttons to prompt the alert dialog to avoid unnecessary score increasing
        firstOption.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                alertDialog();
            }
        });

        secondOption.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                alertDialog();
            }
        });

        thirdOption.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                alertDialog();
            }
        });

        fourthOption.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                alertDialog();
            }
        });


        alertDialog.setTitle("Thank you for taking the quiz " + Data.currentUser.getName() + "!" + '\n' + "You got " + qViewModel.getScoreCounter() + " questions right out of " + qViewModel.getTotalQuestions());
        alertDialog.setMessage("What do you want to do next?");
        alertDialog.setPositiveButton("Play again!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                Intent intent = getIntent();
                finish();
                startActivity(intent);
                ;
            }
        });
        alertDialog.setNegativeButton("Leave the game", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                KanjiQuizMultipleOptions.this.finish();
            }
        });
        alertDialog.show();

    }


}
