package kr.co.healthcare.selfDiagnosis.QuestionDB;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QuesDataAdapter {

    protected static final String TAG = "QuesDataAdapter";
    protected static final String TABLE_NAME = "disease_";

    private final Context mContext;
    private SQLiteDatabase mDB;
    private QuesDBHelper mQuesDBHelper;


    public QuesDataAdapter(Context context){
        this.mContext = context;
        mQuesDBHelper = new QuesDBHelper(mContext);
    }

    public QuesDataAdapter createDatabase() throws SQLException {
        try{
            mQuesDBHelper.createQuesDatabase();
        }catch (IOException mIOException){
            Log.e(TAG, mIOException.toString() + " UnableToCreateDatabase");
            throw new Error("UnableToCreateDatabase");
        }
        return this;
    }

    public QuesDataAdapter open() throws SQLException {
        try{
            mQuesDBHelper.openQuesDatabase();
            mQuesDBHelper.close();
            mDB = mQuesDBHelper.getReadableDatabase();
        }catch (SQLException mSQLException){
            Log.e(TAG,"open>>"+mSQLException.toString());
            throw mSQLException;
        }
        return this;
    }

    public void close(){
        mQuesDBHelper.close();
    }

    public List getTableData(int n){
        try{
            String query = "SELECT * FROM " + TABLE_NAME + n;
            List questionList = new ArrayList();
            Questions questions = null;
            Cursor mCur = mDB.rawQuery(query, null);
            if(mCur!=null){
                while(mCur.moveToNext()){
                    questions = new Questions();
                    questions.setNum(mCur.getString(0));
                    questions.setQuestions(mCur.getString(1));
                    questionList.add(questions);
                }
            }
            return questionList;
        }catch (SQLException mSQLException){
            Log.e(TAG, "getTestData>>"+ mSQLException.toString());
            throw mSQLException;
        }
    }
}
