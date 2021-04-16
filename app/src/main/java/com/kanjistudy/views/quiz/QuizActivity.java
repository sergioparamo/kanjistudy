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
import androidx.lifecycle.ViewModelProvider;

import com.kanjistudy.R;
import com.kanjistudy.components.ToastsConfig;
import com.kanjistudy.views.loginProcess.MainActivity;

public class QuizActivity extends AppCompatActivity {

    private TextView questionTextView;
    private ProgressBar mainProgressBar;
    private TextView progressText;
    private Button trueButton, falseButton, leaveButton;
    QuizViewModel qViewModel = null;

    AlertDialog.Builder alertDialog;
    public final ToastsConfig toasts = new ToastsConfig();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        if (qViewModel == null) {
            qViewModel = new ViewModelProvider(this).get(QuizViewModel.class);
        }

        alertDialog = new AlertDialog.Builder(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_activity);

        questionTextView = findViewById(R.id.questionTextView);


        mainProgressBar = findViewById(R.id.simpleProgressBar);
        progressText = findViewById(R.id.progressText);

        trueButton = findViewById(R.id.trueButtonQuiz);
        falseButton = findViewById(R.id.falseButtonQuiz);
        leaveButton = findViewById(R.id.leaveQuizButton);

        refreshScreen();

        trueButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        falseButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        leaveButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                Intent fromQuizToMainIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(fromQuizToMainIntent);
            }
        });

    }

    public void refreshScreen() {
        questionTextView.setText(qViewModel.getQuestion());
        progressText.setText(qViewModel.progressText());
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void checkAnswer(boolean userChoose) {


        if (qViewModel.checkAnswer(userChoose)) {

            toasts.showToastByDuration(getApplicationContext(), 2, "Correct Answer!");
            qViewModel.incrementScoreCounter();

        } else {
            toasts.showToastByDuration(getApplicationContext(), 2, "Incorrect Answer!");

        }

        if (qViewModel.getCurrentQuestionIndex() == (qViewModel.getTotalQuestions() - 1)) {
            trueButton.setVisibility(View.INVISIBLE);
            falseButton.setVisibility(View.INVISIBLE);
            leaveButton.setVisibility(View.VISIBLE);

            alertDialog();
        } else {
            nextQuestion();
        }


    }

    public void nextQuestion() {

        qViewModel.moveToNextQuestion();

        mainProgressBar.setProgress(qViewModel.getIncrementBy());

        questionTextView.setText(qViewModel.getQuestion());

        progressText.setText(qViewModel.progressText());
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void alertDialog() {


        trueButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                alertDialog();
            }
        });

        falseButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                alertDialog();
            }
        });


        alertDialog.setTitle("You have completed the quiz!" + '\n' + "Results: " + qViewModel.getScoreCounter() + " questions right out of " + qViewModel.getTotalQuestions() + '\n');
        alertDialog.show();

    }

}
