package com.cdhillon.rxjavarealm;

import android.content.Context;

import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.rule.PowerMockRule;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.internal.RealmCore;
import io.realm.log.RealmLog;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.whenNew;

/**
 * Created by cdhillon on 6/8/17.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants=BuildConfig.class, manifest="src/main/AndroidManifest.xml", sdk = 21,
        packageName = "com.insulet.myblue.pdm")
@PowerMockIgnore({"org.mockito.*", "org.robolectric.*", "android.*", "org.powermock.*"})
@PrepareForTest({Realm.class, RealmConfiguration.class, RealmQuery.class, RealmResults.class,
        RealmLog.class, RealmCore.class, RealmList.class, RealmObject.class})
public class RobolectricTest {
    //Using Power Mock: https://github.com/robolectric/robolectric/wiki/Using-PowerMock
    @Rule
    public PowerMockRule rule = new PowerMockRule();

    protected Realm mockRealm;
    protected Context context;

    @Before
    public void setup() throws Exception {
        ShadowLog.stream = System.out;
        context = RuntimeEnvironment.application.getApplicationContext();
        mockRealm();
    }

    void mockRealm() throws Exception {

        // Setup Realm to be mocked. The order of these matters
        mockStatic(RealmCore.class);
        mockStatic(RealmLog.class);
        mockStatic(Realm.class);
        mockStatic(RealmConfiguration.class);
        mockStatic(RealmResults.class);
        mockStatic(RealmList.class);
        mockStatic(RealmQuery.class);
        mockStatic(RealmObject.class);
        Realm.init(RuntimeEnvironment.application);

        // Create realm mock objects
        final Realm mockRealm = mock(Realm.class);
        final RealmConfiguration mockRealmConfig = mock(RealmConfiguration.class);
        final RealmConfiguration.Builder mockedBuilder = mock(RealmConfiguration.Builder.class);

        // magically return the mock when a new instance is required
        whenNew(RealmConfiguration.Builder.class).withNoArguments().thenReturn(mockedBuilder);
        when(mockedBuilder.build()).thenReturn(mockRealmConfig);

        // Anytime getInstance is called with any configuration, then return the mockRealm
        when(Realm.getInstance(any(RealmConfiguration.class))).thenReturn(mockRealm);
        when(Realm.getDefaultInstance()).thenReturn(mockRealm);

        this.mockRealm = mockRealm;
    }

    public static void mockSetting(String field, Object object) throws Exception {
//        setFinalStatic(Setting.class.getDeclaredField(field), object);
    }

    static void setFinalStatic(Field field, Object newValue) throws Exception {
        field.setAccessible(true);
        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
        field.set(null, newValue);
    }

    protected String getStringResource(int resId) {
        return context.getResources().getString(resId);
    }
}
