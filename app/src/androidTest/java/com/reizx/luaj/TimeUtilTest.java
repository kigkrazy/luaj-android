package com.reizx.luaj;

import android.support.test.runner.AndroidJUnit4;

import com.blankj.utilcode.util.TimeUtils;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@RunWith(AndroidJUnit4.class)
public class TimeUtilTest {
    @Test
    public void timeTest() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        String nowString = sdf.format(new Date());

        //Date date = TimeUtils.string2Date("2018-")
    }
}
