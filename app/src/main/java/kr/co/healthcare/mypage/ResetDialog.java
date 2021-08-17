package kr.co.healthcare.mypage;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import kr.co.healthcare.R;

public class ResetDialog extends Dialog {

    private final Context context;
    private final ResetDialogClickListener resetDialogClickListener;
    private TextView tv_title, tv_content, tv_yes, tv_no;

    public ResetDialog(@NonNull Context context, ResetDialogClickListener resetDialogClickListener) {
        super(context);
        this.context = context;
        this.resetDialogClickListener = resetDialogClickListener;
    }

    public void setDialogText(String title, String content, String yes, String no){
        this.tv_title.setText(title);
        this.tv_content.setText(content);
        this.tv_yes.setText(yes);
        this.tv_no.setText(no);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.widget_reset_dialog);

        tv_title = findViewById(R.id.tv_dialog_title);
        tv_content = findViewById(R.id.tv_dialog_content);
        tv_yes = findViewById(R.id.tv_dialog_answer_yes);
        tv_no = findViewById(R.id.tv_dialog_answer_no);

        tv_yes.setOnClickListener(v -> {
            this.resetDialogClickListener.yesClick();
            dismiss();
        });
        tv_no.setOnClickListener(v -> {
            this.resetDialogClickListener.noClick();
            dismiss();
        });
    }
}
