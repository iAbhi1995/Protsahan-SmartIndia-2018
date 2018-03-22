package com.mota.tribal.protsahan.Query.Model.Data;


public class Query {
    private String _id, question, answer;

    public Query(String id, String question, String answer) {
        _id = id;
        this.question = question;
        this.answer = answer;
    }

    public String get_id() {
        return _id;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }
}
