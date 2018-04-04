package com.ibrothergang.demorxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import rx.Observable;
import rx.Subscriber;

public class NormalRxjavaActivity extends AppCompatActivity {
    private TextView mTxtInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_rxjava);
        mTxtInfo = findViewById(R.id.text_info);
    }

    public void button01Click(View view) {
        Observable observable = createObsevable();
        Subscriber subscriber = createSubscriber();
        observable.subscribe(subscriber);
    }

    public void button02Click(View view) {
        showText("button02Click");
    }

    public void button03Click(View view) {
        showText("button03Click");
    }

    public void ClearClick(View view) {
        mTxtInfo.setText("");
    }

    private void showText(String value) {
        CharSequence oldString = mTxtInfo.getText();
        mTxtInfo.setText(oldString + "\n" + value);
    }

    private Observable createObsevable() {
        Observable switcher = Observable.create(new Observable.OnSubscribe<String>() {

            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("On");
                subscriber.onNext("Off");
                subscriber.onNext("On");
                subscriber.onNext("On");
                subscriber.onCompleted();
            }
        });

        return switcher;
    }

    private Subscriber createSubscriber() {
        Subscriber light = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                //被观察者的onCompleted()事件会走到这里;
                showText("结束观察...\n");
            }

            @Override
            public void onError(Throwable e) {
                //出现错误会调用这个方法
            }

            @Override
            public void onNext(String s) {
                //处理传过来的onNext事件
                showText("handle this---" + s);
            }
        };
        return light;
    }
}
