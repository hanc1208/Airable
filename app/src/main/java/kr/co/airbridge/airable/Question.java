package kr.co.airbridge.airable;

/**
 * Created by pso99_000 on 2015-12-12.
 */

public class Question {
    private int id;
    private String content;
    private int nextWhenYes;
    private int nextWhenNo;
    private int nextWhenSkip;
    private Answer answer;

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public int getNextWhenYes() {
        return nextWhenYes;
    }

    public int getNextWhenNo() {
        return nextWhenNo;
    }

    public int getNextWhenSkip() {
        return nextWhenSkip;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public enum Answer {
        YES, NO, SKIP
    }
}
