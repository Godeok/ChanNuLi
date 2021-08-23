package kr.co.healthcare.mypage;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import java.util.function.Function;

import kr.co.healthcare.R;
import kr.co.healthcare.preference.GameResultPreferenceManager;
import kr.co.healthcare.preference.UserInfoPreferenceManger;
import kr.co.healthcare.self_diagnosis.ResultDB.ResultDAO;
import kr.co.healthcare.self_diagnosis.ResultDB.SelfDiagnosisResultDatabase;
import kr.co.healthcare.tutorial.ui.TutorialActivity;

public class ResetActivity extends AppCompatActivity {
    private enum ResetType {
        GAME_HISTORY,
        SELF_DIAGNOSIS_HISTORY,
        ACCOUNT
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);

        setGameOnClickListener();
        setDiagnosisOnClickListener();
        setAccountOnClickListener();
    }

    private void setGameOnClickListener(){
        LinearLayout game = findViewById(R.id.game_history_reset);
        game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Reset", "game 초기화");
                getAlertDialog(
                        R.string.dialog_title_game_reset,
                        R.string.dialog_message_game_reset,
                        ResetType.GAME_HISTORY
                ).show();
            }
        });
    }

    private void setDiagnosisOnClickListener(){
        LinearLayout diagnosis = findViewById(R.id.diagnosis_history_reset);
        diagnosis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Reset", "diagnosis 초기화");
                getAlertDialog(
                        R.string.dialog_title_self_diagnosis_reset,
                        R.string.dialog_message_self_diagnosis_reset,
                        ResetType.SELF_DIAGNOSIS_HISTORY
                ).show();
            }
        });
    }

    private void setAccountOnClickListener(){
        LinearLayout account = findViewById(R.id.account_reset);
        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAlertDialog(
                        R.string.dialog_title_account_reset,
                        R.string.dialog_message_account_reset,
                        ResetType.ACCOUNT
                ).show();
            }
        });
    }

    private void resetGameResult(){
        GameResultPreferenceManager.clearScore(ResetActivity.this);
    }

    private void resetSelfDiagnosisResult(){
        //SelfDiagnosisResultDatabase.getInstance(ResetActivity.this).resultDAO().deleteAllSelfResult();
    }

    private void resetAccount(){
        UserInfoPreferenceManger.clear(ResetActivity.this);
    }

    private void backToStartPage(){
        Intent intent = new Intent(ResetActivity.this, TutorialActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    private AlertDialog.Builder getAlertDialog(int title, int message, ResetType type){
        AlertDialog.Builder alBuilder = new AlertDialog.Builder(this);

        alBuilder//.setView(R.style.AppTheme_ResetDialog)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.dialog_btn_positive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (type) {
                            case GAME_HISTORY:
                                resetGameResult();
                                break;

                            case SELF_DIAGNOSIS_HISTORY:
                                resetSelfDiagnosisResult();
                                break;

                            case ACCOUNT:
                                resetAccount();
                                backToStartPage();
                                break;

                            default:
                                throw new IllegalStateException("Unexpected value: " + type);
                        }
                    }
                }).setNegativeButton(R.string.dialog_btn_negative, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        return alBuilder;
    }
}
