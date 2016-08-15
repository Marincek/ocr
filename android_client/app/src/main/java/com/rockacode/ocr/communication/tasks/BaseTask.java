package com.rockacode.ocr.communication.tasks;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Marincek on 15-Jul-16.
 */

public abstract class BaseTask<T> implements Observable.OnSubscribe<T>{

    protected Subscriber<? super T> subscriber;

    public Observable<T> execute(){
        return Observable.create(this).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void call(Subscriber<? super T> subscriber) {
        this.subscriber = subscriber;
        doInBackground();
    }

    protected abstract void doInBackground();

    protected void onSuccess(T result){
        this.subscriber.onNext(result);
        this.subscriber.onCompleted();
    }

    protected void onError(Throwable t){
        this.subscriber.onError(t);
        this.subscriber.onCompleted();
    }
}
