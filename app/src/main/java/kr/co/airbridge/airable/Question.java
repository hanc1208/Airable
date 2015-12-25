package kr.co.airbridge.airable;

/**
 * Created by pso99_000 on 2015-12-12.
 */

public class Question {
    private String context;              // 질문 내용
    private String detailUrl;           // 상세 내용 페이지 url
    private Question previousQuestion; // 이전 질문
    private Question nextQuestion;      // 다음 질문
    private Answer answer;               // 답변

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Question getPreviousQuestion() {
        return previousQuestion;
    }

    public void setPreviousQuestion(Question previousQuestion) {
        this.previousQuestion = previousQuestion;
    }

    public Question getNextQuestion() {
        return nextQuestion;
    }

    public void setNextQuestion(Question nextQuestion) {
        this.nextQuestion = nextQuestion;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public enum Answer{yes, no, unselected};
}
