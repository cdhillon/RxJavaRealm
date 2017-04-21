package com.cdhillon.rxjavarealm;


import android.util.Log;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subjects.AsyncSubject;

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
                .doOnTerminate(new Action0() {
                    @Override
                    public void call() {
                        Logger.v(TAG, "doOnTerminate concat");
                    }
                })
                .doAfterTerminate(new Action0() {
                    @Override
                    public void call() {
                        Logger.v(TAG, "doAfterTerminate concat");
                    }
                })
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        Logger.v(TAG, "doOnSubscribe concat");
                    }
                })
                .doOnUnsubscribe(new Action0() {
                    @Override
                    public void call() {
                        Logger.v(TAG, "doOnUnsubscribe concat");
                    }
                })
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        Logger.v(TAG, "onCompleted concat");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e(TAG, "onError", e);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Logger.v(TAG, "onNext " + integer);
                    }
                });
    }

    Observable<Integer> obs0() {
        return Observable.range(1, 10)
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        Logger.v(TAG, "doOnSubscribe");
                    }
                }).doOnUnsubscribe(new Action0() {
                    @Override
                    public void call() {
                        Logger.v(TAG, "doOnUnsubscribe");
                    }
                }).doOnTerminate(new Action0() {
                    @Override
                    public void call() {
                        Logger.v(TAG, "doOnTerminate");
                    }
                }).doAfterTerminate(new Action0() {
                    @Override
                    public void call() {
                        Logger.v(TAG, "doAfterTerminate");
                    }
                });
    }

    Observable<Integer> obs1() {
        return Observable.just(1)
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        Logger.v(TAG, "doOnSubscribe " + 1);
                    }
                }).doOnUnsubscribe(new Action0() {
                    @Override
                    public void call() {
                        Logger.v(TAG, "doOnUnsubscribe " + 1);
                    }
                }).doOnTerminate(new Action0() {
                    @Override
                    public void call() {
                        Logger.v(TAG, "doOnTerminate " + 1);
                    }
                }).doAfterTerminate(new Action0() {
                    @Override
                    public void call() {
                        Logger.v(TAG, "doAfterTerminate " + 1);
                    }
                });
    }

    Observable<Integer> obs2() {
        return Observable.just(2)
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        Logger.v(TAG, "doOnSubscribe " + 2);
                    }
                }).doOnUnsubscribe(new Action0() {
                    @Override
                    public void call() {
                        Logger.v(TAG, "doOnUnsubscribe " + 2);
                    }
                }).doOnTerminate(new Action0() {
                    @Override
                    public void call() {
                        Logger.v(TAG, "doOnTerminate " + 2);
                    }
                }).doAfterTerminate(new Action0() {
                    @Override
                    public void call() {
                        Logger.v(TAG, "doAfterTerminate " + 2);
                    }
                });
    }

    Observable<Integer> obs3() {
        return Observable.just(3)
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        Logger.v(TAG, "doOnSubscribe " + 3);
                    }
                }).doOnUnsubscribe(new Action0() {
                    @Override
                    public void call() {
                        Logger.v(TAG, "doOnUnsubscribe " + 3);
                    }
                }).doOnTerminate(new Action0() {
                    @Override
                    public void call() {
                        Logger.v(TAG, "doOnTerminate " + 3);
                    }
                }).doAfterTerminate(new Action0() {
                    @Override
                    public void call() {
                        Logger.v(TAG, "doAfterTerminate " + 3);
                    }
                });
    }

    Observable<Integer> obs4() {
        return Observable.just(4)
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        Logger.v(TAG, "doOnSubscribe " + 4);
                    }
                }).doOnUnsubscribe(new Action0() {
                    @Override
                    public void call() {
                        Logger.v(TAG, "doOnUnsubscribe " + 4);
                    }
                }).doOnTerminate(new Action0() {
                    @Override
                    public void call() {
                        Logger.v(TAG, "doOnTerminate " + 4);
                    }
                }).doAfterTerminate(new Action0() {
                    @Override
                    public void call() {
                        Logger.v(TAG, "doAfterTerminate " + 4);
                    }
                });
    }


    public class MyObservable<T> extends Observable<T> {

        /**
         * Creates an Observable with a Function to execute when it is subscribed to.
         * <p>
         * <em>Note:</em> Use {@link #create(OnSubscribe)} to create an Observable, instead of this constructor,
         * unless you specifically have a need for inheritance.
         *
         * @param f {@link OnSubscribe} to be executed when {@link #subscribe(Subscriber)} is called
         */
        protected MyObservable(OnSubscribe<T> f) {
            super(f);
        }
    }
}
