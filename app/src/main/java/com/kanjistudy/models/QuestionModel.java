package com.kanjistudy.models;


public class QuestionModel {


    private String questionText;
    private String answer;

    public QuestionModel() {

    }

    public QuestionModel(String questionText, String answer) {
        this.questionText = questionText;
        this.answer = answer;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String questionAnswer) {
        this.answer = questionAnswer;
    }
}

