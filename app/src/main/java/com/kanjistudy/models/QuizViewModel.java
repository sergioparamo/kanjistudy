package com.kanjistudy.models;

import androidx.lifecycle.ViewModel;

import com.kanjistudy.R;

public class QuizViewModel extends ViewModel {

    private int currentQuestionIndex = 0;
    private int incrementBy;
    private int scoreCounter;
    private final int defaultProgressBarValue = 10;

    private QuestionModel[] questionBank = new QuestionModel[]{

            // array of objects of class QuestionModel
            // providing questions from strings of strings.xml file
            // resource (text) and the answer as a boolean is given
//            new QuestionModel(R.string.question_1, "false"),
//            new QuestionModel(R.string.question_2, true),
//            new QuestionModel(R.string.question_3, false),
//            new QuestionModel(R.string.question_4, true),
//            new QuestionModel(R.string.question_5, true),
//            new QuestionModel(R.string.question_6, false),
//            new QuestionModel(R.string.question_7, true),
//            new QuestionModel(R.string.question_8, false),
//            new QuestionModel(R.string.question_9, true),
//            new QuestionModel(R.string.question_10, false)

    };

    //get question
    public String getQuestion() {
        return questionBank[currentQuestionIndex].getQuestionText();
    }

    //get answer
    public String getAnswer() {
        return questionBank[currentQuestionIndex].getAnswer();
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
    public Boolean checkAnswer(String userChoose) {
        return userChoose.equals(getAnswer());
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
