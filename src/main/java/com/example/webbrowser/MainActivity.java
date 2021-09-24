package com.example.webbrowser;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String last_str=" ";
    WebView browser;
    EditText editText;
    String a="Вы изменили текст";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText=findViewById(R.id.editText);
        browser=(WebView)findViewById(R.id.webBrowser);
        browser.setWebViewClient(new MyWebViewClient(){
            public void onPageFinished(){
                ToastShow("Страница загружена!");
            }
        });
        browser.getSettings().setJavaScriptEnabled(true);
        browser.loadUrl("http://metanit.com");
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
               ToastShow("Вы ввели новый адрес");
            }
        });
    }

    public void OnNextClick(View view) {
       ToastShow("Была нажата кнопка Вперед");
        if(browser.canGoForward())browser.goForward();
    }

    public void OnUpdateClick(View view) {
        ToastShow("Была нажата кнопка Обновить");

        String str=editText.getText().toString();
        if(last_str == " " || last_str==str) browser.loadUrl ("javascript: window.location.reload (true)");
        else{
            if(str.contains(".com")||str.contains(".ru")||str.contains(".by"))
                browser.loadUrl(str);
            else browser.loadUrl("https://yandex.by/search/?text="+str);
        }
        browser.loadUrl("https://yandex.by/search/?text="+str);
        last_str=str;
    }

    public void OnBackClick(View view) {
        ToastShow("Была нажата кнопка Назад");
        if(browser.canGoBack())browser.goBack();
    }



    public void ToastShow(String a){
        Toast toast=Toast.makeText(this,a,Toast.LENGTH_LONG);
        toast.show();
    }





    private class MyWebViewClient extends WebViewClient {
        @TargetApi(Build.VERSION_CODES.N)
        MyWebViewClient(){
            super();
        }
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(request.getUrl().toString());
            return true;
        }

        // Для старых устройств
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

}
