package com.cdhillon.rxjavarealm;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.cdhillon.rxjavarealm.model.Person;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

/**
 * Created by cdhillon on 3/19/17.
 */

public class RealmFragment extends Fragment implements RealmChangeListener<RealmResults<Person>> {

    static final String TAG = RealmFragment.class.getSimpleName();

    private TextView textView;
    private Realm realmUiThread;
    private RealmResults<Person> results;

    public static RealmFragment newInstance(int sectionNumber) {
        RealmFragment fragment = new RealmFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        textView = (TextView) rootView.findViewById(R.id.section_label);

        Button button1 =  (Button) rootView.findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addRecordSync();
//                addRecordAsync();
            }
        });

        Button button2 =  (Button) rootView.findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteRecordSync();
            }
        });

        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.v(TAG, "onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        Log.v(TAG, "onStart");
        super.onStart();
        realmUiThread = Realm.getDefaultInstance();
    }

    @Override
    public void onStop() {
        Log.v(TAG, "onStop");
        super.onStop();
        realmUiThread.close();
    }

    @Override
    public void onResume() {
        Log.v(TAG, "onResume");
        super.onResume();
        results = realmUiThread.where(Person.class).findAllAsync();
        results.addChangeListener(this);
    }

    @Override
    public void onPause() {
        Log.v(TAG, "onPause");
        super.onPause();
        results.removeChangeListener(this);
    }

    @Override
    public void onChange(RealmResults<Person> results) {
        textView.setText("# of record " + results.size());
        for (Person person: results) {
//            Log.v(TAG, person.toString());
        }
    }

    @Override
    public void onDestroy() {
        Log.v(TAG, "onDestroy");
        super.onDestroy();
    }

    private void deleteRecordSync() {
        realmUiThread.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Person p = realmUiThread.where(Person.class).equalTo("name", "Mike").findFirst();
                if( p!= null){
                    p.deleteFromRealm();
                } else {
                    Log.v(TAG, "no match found");
                }
            }
        });
    }

    private void addRecordSync() {
        realmUiThread.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Person person = realm.createObject(Person.class);
                person.setName("Mike");
                person.setAge(25);
            }
        });
    }

    private void addRecordAsync() {
        realmUiThread.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Person person = realm.createObject(Person.class);
                person.setName("Mike");
                person.setAge(25);
            }
        });
    }

}
