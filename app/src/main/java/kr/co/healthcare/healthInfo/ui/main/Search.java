package kr.co.healthcare.healthInfo.ui.main;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebBackForwardList;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import kr.co.healthcare.R;
import kr.co.healthcare.WebActivity;
import kr.co.healthcare.healthInfo.HealthInfoActivity;

import static android.content.Context.INPUT_METHOD_SERVICE;
import static android.view.KeyEvent.KEYCODE_ENTER;

public class Search extends Fragment {

    private WebView webView;
    private String url;
    private String[] keywords;

    LinearLayout layout_init;
    TextInputEditText textInput;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        keywords = getResources().getStringArray(R.array.SEARCH_KEYWORD_LABEL);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        ChipGroup chipGroup = view.findViewById(R.id.CHIPGROUP_keywords);
        layout_init = view.findViewById(R.id.layout_init);
        textInput = view.findViewById(R.id.textInput);
        webView = view.findViewById(R.id.webView);

        //chip
        for(String keyword : keywords){
            final Chip chip = (Chip) this.getLayoutInflater().inflate(
                    R.layout.item_chip_search_keyword, chipGroup, false);
            chip.setText(keyword);
            chipGroup.addView(chip);

            //chip 선택 했을 경우
            chip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    url = "https://www.google.com/search?q=" + keyword + "&tbm=nws";
                    initSearch();
                    setWebView();
                }
            });
        }

        //검색어 입력 후 엔터 눌렀을 경우
        textInput.setOnKeyListener(new View.OnKeyListener(){
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                switch (keyCode){
                    case  KeyEvent.KEYCODE_ENTER:
                        url = "https://www.google.com/search?q=" + textInput.getText() + "&tbm=nws";
                        initSearch();
                        setWebView();
                        break;
                }
                return true;
            }
        });

        return view;
    }

    //webView 설정 및 화면 레이아웃 구성 변경
    void setWebView(){
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebActivity.WebViewClientClass());
        webView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction()!=KeyEvent.ACTION_DOWN)
                    return true;
                if(keyCode == KeyEvent.KEYCODE_BACK) {
                    if (webView.canGoBack()) webView.goBack();
                    else initLayout();
                    return true;
                }
                return false;
            }
        });
    }

    void initSearch(){
        layout_init.setVisibility(View.GONE);
        webView.setVisibility(View.VISIBLE);
    }
    void initLayout(){
        webView.setVisibility(View.GONE);
        layout_init.setVisibility(View.VISIBLE);
    }
}
