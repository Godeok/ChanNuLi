package kr.co.healthcare.selfDiagnosis.QuestionDB;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import kr.co.healthcare.selfDiagnosis.MainRecycler.SelfMainData;

//자가진단 질문 가져오기
public class LoadDbClass {

    public static ArrayList<SelfMainData> dataList = new ArrayList<>();     //SelfMainData
    public static List<Questions> questionsList;                            //자가진단 질문
    static String[] disease_list = {"고혈압", "골관절염", "고지혈증", "요통", "당뇨병", "골다공증", "치매"};

    //자가진단 질문 load
    public static ArrayList InitializeData(Context context){
        dataList = new ArrayList<>();
        for (int i=0; i<7; i++) {
            initLoadDB(i, context);
            //질문 번호, 질문 리스트, 질문 개수
            dataList.add(new SelfMainData(i, disease_list[i], questionsList.size()));
        }
        return dataList;
    }

    //데이터베이스 load
    public static List initLoadDB(int n, Context context){
        QuesDataAdapter mDBHelper = new QuesDataAdapter(context);
        mDBHelper.createDatabase();
        mDBHelper.open();
        questionsList = mDBHelper.getTableData(n);
        mDBHelper.close();
        return questionsList;
    }
}
