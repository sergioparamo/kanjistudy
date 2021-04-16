package com.kanjistudy.models;

public class Questions {


    private int questionText;
    private boolean answer;

    public Questions() {

    }

    public Questions(int questionText, boolean answer) {
        this.questionText = questionText;
        this.answer = answer;
    }

    public int getQuestionText() {
        return questionText;
    }

    public void setQuestionText(int questionText) {
        this.questionText = questionText;
    }

    public boolean isAnswerTrue() {
        return answer;
    }

    public void setAnswer(boolean questionAnswer) {
        this.answer = questionAnswer;
    }
}