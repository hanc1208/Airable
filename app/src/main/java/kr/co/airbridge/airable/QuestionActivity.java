package kr.co.airbridge.airable;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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
import kr.co.airbridge.airable.model.*;
import kr.co.airbridge.airable.model.Process;
import kr.co.airbridge.airable.utility.ActivityUtility;
import kr.co.airbridge.airable.utility.DBManager;

public class QuestionActivity extends AppCompatActivity {

    private List<Question> questions;
    private List<Question> answeredQuestions = new ArrayList<>();
    private Question currentQuestion;
    private DBManager dbManager;

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

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    private String flightId;
    private String departureHour;
    private String departureMinute;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        ButterKnife.bind(this);

        ActivityUtility activityUtility = new ActivityUtility(this);
        activityUtility.setToolbar(R.id.toolbar);
        activityUtility.setNavigationAsBack();
        toolbar.setNavigationIcon(R.drawable.back_key);
        toolbar.setTitleTextColor(0xff40C4FF);
        toolbar.setBackgroundColor(0x00000000);
        dbManager = new DBManager(this);
        pref = getSharedPreferences("airable", MODE_PRIVATE);
        editor = pref.edit();

        String json = "[{\"id\":1,\"content\":\"도심 공항 터미널을 거쳐 공항으로 오거나 Fasttrack 이용 대상자이십니까?\",\"nextWhenYes\":2,\"nextWhenNo\":2,\"nextWhenSkip\":-1}," +
                "{\"id\":2,\"content\":\"무게 50kg 이상 또는 가로 45cm, 세로 90cm, 높이 70cm 이상의 짐을 가지고 가시나요?\",\"nextWhenYes\":3,\"nextWhenNo\":3,\"nextWhenSkip\":-1}," +
                "{\"id\":3,\"content\":\"미화 1만 불 이상의 여행경비나 귀중품을 휴대 반출 하시나요?\",\"nextWhenYes\":-1,\"nextWhenNo\":-1,\"nextWhenSkip\":-1}]";
        questions = new Gson().fromJson(json, new TypeToken<List<Question>>() {}.getType());

        setCurrentQuestion(questions.get(0));

        Intent flightInfoIntent = getIntent();
        flightId = flightInfoIntent.getStringExtra("flightId");
        departureHour = flightInfoIntent.getStringExtra("departure_hour");
        departureMinute = flightInfoIntent.getStringExtra("departure_minute");

        editor.putString("flightId", flightId);
        editor.putString("departure_hour", departureHour);
        editor.putString("departure_minute", departureMinute);
        editor.apply();
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
                switch (currentQuestion.getId()) {
                    case 1:
                        dbManager.changeProcessState(15, Process.EXCLUDE_PROCESS);
                        dbManager.changeProcessState(16, Process.EXCLUDE_PROCESS);
                        dbManager.changeProcessState(17, Process.INCLUDE_PROCESS);
                        break;
                    case 2:
                        dbManager.changeProcessState(11, Process.INCLUDE_PROCESS);
                        break;
                    case 3:
                        dbManager.changeProcessState(10, Process.INCLUDE_PROCESS);
                        break;
                }
                setCurrentQuestion(getQuestionById(currentQuestion.getNextWhenYes()));
                break;
            case NO:
                switch (currentQuestion.getId()) {
                    case 1:
                        dbManager.changeProcessState(15, Process.INCLUDE_PROCESS);
                        dbManager.changeProcessState(16, Process.INCLUDE_PROCESS);
                        dbManager.changeProcessState(17, Process.EXCLUDE_PROCESS);
                        break;
                    case 2:
                        dbManager.changeProcessState(11, Process.EXCLUDE_PROCESS);
                        break;
                    case 3:
                        dbManager.changeProcessState(10, Process.EXCLUDE_PROCESS);
                        break;
                }
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

        if (currentQuestion == null) {
            Intent addOptionsIntent = new Intent(this, AddTravelOptionsActivity.class);
            addOptionsIntent.putExtra("requestCode", 0);
            startActivity(addOptionsIntent);
            finish();
        } else {
            updateLayout();
        }
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
