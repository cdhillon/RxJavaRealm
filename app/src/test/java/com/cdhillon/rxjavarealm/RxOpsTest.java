package com.cdhillon.rxjavarealm;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import java.util.jar.Manifest;

/**
 * Created by chetan on 4/20/17.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, manifest = Config.NONE)
public class RxOpsTest {

    @Before
    public void setUp() throws Exception {
        ShadowLog.stream = System.out;
        //you other setup here
    }


    @Test
    public void foo() {
        RxOps rxOps = new RxOps();
        rxOps.concat();
    }

}
