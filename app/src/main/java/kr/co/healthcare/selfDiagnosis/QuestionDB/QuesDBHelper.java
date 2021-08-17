package kr.co.healthcare.selfDiagnosis.QuestionDB;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

//DB 생성 및 버전 관리 담당
public final class QuesDBHelper extends SQLiteOpenHelper {

    private static String TAG = "QuesDBHelper";
    private static String DB_PATH = "";
    public static final String DB_NAME = "Questions.db";

    private SQLiteDatabase mDataBase;
    private final Context mContext;


    //db 경로 설정
    public QuesDBHelper(Context context) {
        super(context, DB_NAME, null, 1);
        if (Build.VERSION.SDK_INT >= 17)
            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        else
            DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        this.mContext = context;
    }

    //db 생성
    public void createQuesDatabase() throws IOException {
        //db 없으면 복사해서 가져오기
        boolean mDataBaseExist = checkQuesDatabase();
        if (!mDataBaseExist) {
            this.getReadableDatabase();
            this.close();
            try {
                copyQuesDatabase();
                Log.e(TAG, "createDatabase database created");
            } catch (IOException mIOException) {
                throw new Error("ErrorCopyingDataBase");
            }
        }
    }

    //해당 경로에 데이터베이스 존재하는지 확인
    private boolean checkQuesDatabase() {
        File dbFile = new File(DB_PATH + DB_NAME);
        return dbFile.exists();
    }

    //assets 폴더에서 데이터베이스 복사
    private void copyQuesDatabase() throws IOException {
        InputStream mInput = mContext.getAssets().open(DB_NAME);
        String outFileName = DB_PATH + DB_NAME;
        OutputStream mOutput = new FileOutputStream(outFileName);
        byte[] mBuffer = new byte[1024];
        int mLength;
        while((mLength = mInput.read(mBuffer)) > 0)
            mOutput.write(mBuffer, 0, mLength);
        mOutput.flush();
        mOutput.close();
        mInput.close();
    }

    //데이터베이스 열어서 쿼리 쓸 수 있게 만듦
    public boolean openQuesDatabase() throws SQLException {
        String mPath = DB_PATH + DB_NAME;   //경로 설정
        mDataBase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.CREATE_IF_NECESSARY);   //DB open
        return mDataBase != null;
    }

    @Override
    public synchronized void close() {
        if (mDataBase != null) mDataBase.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}