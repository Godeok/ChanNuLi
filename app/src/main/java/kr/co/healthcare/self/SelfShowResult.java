package kr.co.healthcare.self;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

import kr.co.healthcare.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class SelfShowResult extends AppCompatActivity {

    //private SimpleDateFormat mFormat = new SimpleDateFormat("yyyy.M.d"); // 날짜 포맷

    public class SaveResult {
        Date date;
        int disease_num;
        int countYes;

        SaveResult(Date date, int disease_num, int countYes) {
            this.date = date;
            this.disease_num = disease_num;
            this.countYes = countYes;
        }
    }

    TextView tv_result, tv_desc;
    ArrayList<SaveResult> saveResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_show_result);

        tv_result = findViewById(R.id.tv_result);
        tv_desc = findViewById(R.id.tv_desc);

        Intent intent = getIntent();
        int count = intent.getIntExtra("count", -1);
        int disease_num = intent.getIntExtra("disease_num", -1);

        tv_result.setText("검사 결과 총 "+count+"개의 항목에서 '예'라고 답했습니다.");

        if (count<1) tv_desc.setText("정상 단계입니다.");
        else if (count<3) tv_desc.setText("주의 단계입니다.");
        else tv_desc.setText("위험 단계입니다.");

        Date date = new Date();
        //String time = mFormat.format(date);

        saveResult = new ArrayList<>();

        saveResult.add(new SaveResult(date, disease_num, count));

        SaveResultData(saveResult);


        //shared preferences : 자가진단 결과 저장
        /*
        SharedPreferences sp = getSharedPreferences("saveResults", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        String result = Integer.toString(disease_num) + String.format("%02d", count);
        editor.putString("date", result);          //날짜별 : 날짜&진단결과(_문제번호 __카운트)
        editor.commit();
        */
    }

    public void SaveResultData(ArrayList<SaveResult> result) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(result);
        editor.putString("MyResults", json);
        editor.commit();
    }

    public ArrayList<SaveResult> ReadResultData() {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Gson gson = new Gson();
        String json = sharedPrefs.getString("MyFriends", "EMPTY");
        Type type = new TypeToken<ArrayList<SaveResult>>() {
        }.getType();
        ArrayList<SaveResult> arrayList = gson.fromJson(json, type);
        return arrayList;
    }
}