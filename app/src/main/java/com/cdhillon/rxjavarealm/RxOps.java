package com.cdhillon.rxjavarealm;


import android.util.Log;

import org.reactivestreams.Subscriber;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by chetan on 4/20/17.
 */

public class RxOps {

    static final String TAG = RxOps.class.getSimpleName();


    public void concat() {

        Observable.concat(
                obs1(),
//                obs2(),
//                obs3(),
                obs4()

        )
                .subscribeOn(Schedulers.io())
                .doOnTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        Logger.v(TAG, "doOnTerminate concat");
                    }
                })
                .doAfterTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        Logger.v(TAG, "doAfterTerminate concat");
                    }
                })
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        Logger.v(TAG, "doOnSubscribe concat");
                    }
                })
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        Logger.v(TAG, "doOnUnsubscribe concat");
                    }
                });
    }

    Observable<Integer> obs0() {
        return Observable.range(1, 10);
    }

    Observable<Integer> obs1() {
        return Observable.just(1);
    }

    Observable<Integer> obs2() {
        return Observable.just(2);
    }

    Observable<Integer> obs3() {
        return Observable.just(3);
    }

    Observable<Integer> obs4() {
        return Observable.just(4);
    }
}
