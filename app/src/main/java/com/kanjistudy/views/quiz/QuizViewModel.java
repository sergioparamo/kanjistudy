package com.kanjistudy.views.quiz;

import androidx.lifecycle.ViewModel;

import com.kanjistudy.R;
import com.kanjistudy.models.Questions;

public class QuizViewModel extends ViewModel {

    private int currentQuestionIndex = 0;
    private int incrementBy;
    private int scoreCounter;
    private final int defaultProgressBarValue = 10;

    private Questions[] questionBank = new Questions[]{

            // array of objects of class QuestionModel
            // providing questions from strings of strings.xml file
            // resource (text) and the answer as a boolean is given

            new Questions(R.string.question_1, true),
            new Questions(R.string.question_2, false),
            new Questions(R.string.question_3, true),
            new Questions(R.string.question_4, false),
            new Questions(R.string.question_5, true),
            new Questions(R.string.question_6, false),
            new Questions(R.string.question_7, true),
            new Questions(R.string.question_8, false),
            new Questions(R.string.question_9, true),
            new Questions(R.string.question_10, false)

    };

    //get question
    public int getQuestion() {
        return questionBank[currentQuestionIndex].getQuestionText();
    }

    //get answer
    public boolean getAnswer() {
        return questionBank[currentQuestionIndex].isAnswerTrue();
    }

    // increment the score
    public void incrementScoreCounter() {
        scoreCounter++;
    }

    // get the score
    public int getScoreCounter() {
        return scoreCounter;
    }

    //check answer
    public boolean checkAnswer(boolean userChoose) {
        return userChoose == getAnswer();
    }

    //This method will update the currentIndex
    public void moveToNextQuestion() {
        //Update the index
        currentQuestionIndex = (currentQuestionIndex + 1) % getTotalQuestions();
    }


    //This method will return the value to increment the progressBar using the index of the question, when the index is 0 the method will return "10" as a value to restart the progress
    public int getIncrementBy() {

        //If the index is 0 we will restart the progress bar
        if (currentQuestionIndex == 0) {
            incrementBy = defaultProgressBarValue;
        } else {
            incrementBy = (currentQuestionIndex + 1) * questionBank.length;
        }

        return incrementBy;
    }

    //This method will return an integer value according to the number of questions in the quiz
    public int getTotalQuestions() {
        return questionBank.length;
    }

    //This method will return an integer value according to the current question in the array of questions
    public int getCurrentQuestionIndex() {
        return currentQuestionIndex;
    }

    //This method will return a String value to set the progress text of the quiz according to the question number and the number of questions
    public String progressText() {
        String text = "Question " + (getCurrentQuestionIndex() + 1) + " of " + (getTotalQuestions());
        return text;
    }
}

