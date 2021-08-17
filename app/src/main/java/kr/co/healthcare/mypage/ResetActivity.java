package kr.co.healthcare.mypage;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import kr.co.healthcare.R;
import kr.co.healthcare.preference.GameResultPreferenceManager;
import kr.co.healthcare.preference.UserInfoPreferenceManger;

public class ResetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);
    }

    private void setDialogDefault(ResetDialog dialog){
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        dialog.getWindow().setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT);
    }

    public void showDialogResetGameHistory(View view){
        ResetDialog dialog = new ResetDialog(this, new ResetDialogClickListener() {
            @Override
            public void yesClick() {
                //게임 기록 초기화
                GameResultPreferenceManager.clearScore(ResetActivity.this);
                //TODO: 밑에 확인 알람창
            }

            @Override
            public void noClick() {
                //취소하기
            }
        });
        dialog.setDialogText(
                "게임 기록 초기화",
                "게임 기록이 모두 초기화 됩니다.<br/>그래도 진행하시겠습니까?",
                "초기화하기",
                "취소"
        );

        setDialogDefault(dialog);
        dialog.show();
    }

    public void showDialogResetDiagnosisHistory(View view){
        ResetDialog dialog = new ResetDialog(this, new ResetDialogClickListener() {
            @Override
            public void yesClick() {
                //자가진단 기록 초기화
            }

            @Override
            public void noClick() {
                //취소하기
            }
        });
        dialog.setDialogText(
                "자가진단 기록 초기화",
                "자가진단 기록이 모두 초기화됩니다.<br/>그래도 진행하시겠습니까?",
                "초기화하기",
                "취소"
        );

        setDialogDefault(dialog);
        dialog.show();
    }

    public void showDialogResetAccount(View view){
        ResetDialog dialog = new ResetDialog(this, new ResetDialogClickListener() {
            @Override
            public void yesClick() {
                //계정 초기화
                UserInfoPreferenceManger.clear(ResetActivity.this);
            }

            @Override
            public void noClick() {
                //취소하기
            }
        });
        dialog.setDialogText(
                "계정 초기화",
                "모든 사용자 정보가 초기화됩니다.<br/>정말 초기화하시겠습니까?",
                "초기화하기",
                "취소"
        );

        setDialogDefault(dialog);
        dialog.show();
    }
}

