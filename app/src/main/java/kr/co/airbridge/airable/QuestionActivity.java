package kr.co.airbridge.airable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kr.co.airbridge.airable.utility.ActivityUtility;

public class QuestionActivity extends AppCompatActivity {

    private List<Question> questions;
    private List<Question> answeredQuestions = new ArrayList<>();
    private Question currentQuestion;

    @Bind(R.id.question_progress_bar)
    ProgressBar progressBar;

    @Bind(R.id.question_content)
    TextView content;

    @Bind(R.id.question_yes)
    ImageView yes;

    @Bind(R.id.question_no)
    ImageView no;

    @Bind(R.id.question_previous)
    ImageView previous;

    @Bind(R.id.question_skip)
    ImageView skip;

    @Bind(R.id.question_next)
    ImageView next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        ButterKnife.bind(this);

        ActivityUtility activityUtility = new ActivityUtility(this);
        activityUtility.setToolbar(R.id.toolbar);
        activityUtility.setNavigationAsBack();

        String json = "[{\"id\":1,\"content\":\"1 + 1 = 2?\",\"nextWhenYes\":2,\"nextWhenNo\":7,\"nextWhenSkip\":7},{\"id\":2,\"content\":\"2 + 2 = 4?\",\"nextWhenYes\":3,\"nextWhenNo\":7,\"nextWhenSkip\":7},{\"id\":3,\"content\":\"3 + 3 = 6?\",\"nextWhenYes\":4,\"nextWhenNo\":7,\"nextWhenSkip\":7},{\"id\":4,\"content\":\"4 + 4 = 8?\",\"nextWhenYes\":5,\"nextWhenNo\":7,\"nextWhenSkip\":7},{\"id\":5,\"content\":\"5 + 5 = 10?\",\"nextWhenYes\":6,\"nextWhenNo\":7,\"nextWhenSkip\":7},{\"id\":6,\"content\":\"6 + 6 = 12?\",\"nextWhenYes\":9,\"nextWhenNo\":7,\"nextWhenSkip\":7},{\"id\":7,\"content\":\"바보?\",\"nextWhenYes\":8,\"nextWhenNo\":9,\"nextWhenSkip\":8},{\"id\":8,\"content\":\"당신은 여행을 할 수 없습니다.\",\"nextWhenYes\":-1,\"nextWhenNo\":-1,\"nextWhenSkip\":-1},{\"id\":9,\"content\":\"당신은 여행을 할 수 있습니다.\",\"nextWhenYes\":-1,\"nextWhenNo\":-1,\"nextWhenSkip\":-1}]";
        questions = new Gson().fromJson(json, new TypeToken<List<Question>>() {}.getType());

        setCurrentQuestion(questions.get(0));
    }

    public void onDetailClick(View view) {

    }

    @OnClick(R.id.question_yes)
    public void onYesClick() {
        if (currentQuestion.getAnswer() == Question.Answer.YES) {
            currentQuestion.setAnswer(null);
        } else {
            currentQuestion.setAnswer(Question.Answer.YES);
        }
        updateLayout();
    }

    @OnClick(R.id.question_no)
    public void onNoClick() {
        if (currentQuestion.getAnswer() == Question.Answer.NO) {
            currentQuestion.setAnswer(null);
        } else {
            currentQuestion.setAnswer(Question.Answer.NO);
        }
        updateLayout();
    }

    @OnClick(R.id.question_previous)
    public void onPreviousClick() {
        if (answeredQuestions.size() > 0) {
            setCurrentQuestion(answeredQuestions.remove(answeredQuestions.size() - 1));
        }
    }

    @OnClick(R.id.question_skip)
    public void onSkipClick() {
        currentQuestion.setAnswer(Question.Answer.SKIP);
        answeredQuestions.add(currentQuestion);
        setCurrentQuestion(getQuestionById(currentQuestion.getNextWhenSkip()));
    }

    @OnClick(R.id.question_next)
    public void onNextClick() {
        answeredQuestions.add(currentQuestion);
        switch (currentQuestion.getAnswer()) {
            case YES:
                setCurrentQuestion(getQuestionById(currentQuestion.getNextWhenYes()));
                break;
            case NO:
                setCurrentQuestion(getQuestionById(currentQuestion.getNextWhenNo()));
                break;
        }
    }

    public Question getQuestionById(int id) {
        for (Question question : questions) {
            if (question.getId() == id) {
                return question;
            }
        }
        return null;
    }

    public int getExpectedTotalQuestionCount() {
        Question question = currentQuestion;
        int count = 1;
        while (question.getNextWhenSkip() != -1) {
            count++;
            question = getQuestionById(question.getNextWhenSkip());
        }
        return answeredQuestions.size() + count;
    }

    public void setCurrentQuestion(Question currentQuestion) {
        this.currentQuestion = currentQuestion;
        updateLayout();
    }

    public void updateLayout() {
        progressBar.setProgress(answeredQuestions.size() + 1);
        progressBar.setMax(getExpectedTotalQuestionCount());
        content.setText(currentQuestion.getContent());
        yes.setActivated(currentQuestion.getAnswer() == Question.Answer.YES);
        no.setActivated(currentQuestion.getAnswer() == Question.Answer.NO);
        skip.setVisibility(currentQuestion.getAnswer() == null ? View.VISIBLE : View.GONE);
        next.setVisibility(currentQuestion.getAnswer() == null ? View.GONE : View.VISIBLE);
    }
}
