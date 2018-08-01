package com.reizx.luaj;

import com.blankj.utilcode.util.TimeUtils;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeUtilJTest {
    @Test
    public void timeTest() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        String nowString = sdf.format(new Date());

        Date date = TimeUtils.string2Date("2018-07-27", sdf);

        TimeUtils.getFitTimeSpan("2018-07-23", "2018-07-25", sdf, 1);
    }
}
