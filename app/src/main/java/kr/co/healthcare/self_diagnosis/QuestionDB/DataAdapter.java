package kr.co.healthcare.self_diagnosis.QuestionDB;


import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataAdapter {

    protected static final String TAG = "DataAdapter";

    protected static final String TABLE_NAME = "disease_";

    private final Context mContext;
    private SQLiteDatabase mDB;
    private DBHelper mDBHelper;

    public DataAdapter(Context context){
        this.mContext = context;
        mDBHelper = new DBHelper(mContext);
    }

    public DataAdapter createDatabase() throws SQLException {
        try{
            mDBHelper.createDatBase();
        }
        catch (IOException mIOException){
            Log.e(TAG, mIOException.toString() + " UnableToCreateDatabase");
            throw new Error("UnableToCreateDatabase");
        }
        return this;
    }

    public DataAdapter open() throws SQLException {
        try{
            mDBHelper.openDataBase();
            mDBHelper.close();
            mDB = mDBHelper.getReadableDatabase();
        }
        catch (SQLException mSQLException){
            Log.e(TAG,"open>>"+mSQLException.toString());
            throw mSQLException;
        }
        return this;
    }


    public void close(){
        mDBHelper.close();
    }


    public List getTableData(int n){
        try{
            String sql = "SELECT * FROM " + TABLE_NAME + n;
            List questionList = new ArrayList();
            Questions questions = null;
            Cursor mCur = mDB.rawQuery(sql, null);
            if (mCur!=null){
                while(mCur.moveToNext()){
                    questions = new Questions();

                    questions.setNum(mCur.getString(0));
                    questions.setQuestions(mCur.getString(1));

                    questionList.add(questions);
                }
            }
            return questionList;
        }
        catch (SQLException mSQLException){
            Log.e(TAG, "getTestData>>"+ mSQLException.toString());
            throw mSQLException;
        }
    }

    //이 코드는 안 씁니다
    public void getTableData2(){
        Cursor c = mDB.rawQuery("SELECT * FROM "+TABLE_NAME, null);
        while(c.moveToNext()) {
            int num = c.getColumnIndex(TABLE_NAME);
            String ques = c.getString(num);
            System.out.println(ques);
        }
    }
}
