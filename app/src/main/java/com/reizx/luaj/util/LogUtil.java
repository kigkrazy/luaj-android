package com.reizx.luaj.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.blankj.utilcode.constant.TimeConstants;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.elvishew.xlog.LogLevel;
import com.elvishew.xlog.Logger;
import com.elvishew.xlog.XLog;
import com.elvishew.xlog.flattener.DefaultFlattener;
import com.elvishew.xlog.printer.AndroidPrinter;
import com.elvishew.xlog.printer.Printer;
import com.elvishew.xlog.printer.file.FilePrinter;
import com.elvishew.xlog.printer.file.backup.FileSizeBackupStrategy;
import com.elvishew.xlog.printer.file.naming.FileNameGenerator;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;


/**
 * 使用
 *      FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
 *          .showThreadInfo(false)  // (Optional) Whether to show thread info or not. Default true
 *          .methodCount(0)         // (Optional) How many method line to show. Default 2
 *          //.methodOffset(5)        // (Optional) Hides internal method calls up to offset. Default 5
 *          .tag("zues-rium")   // (Optional) Global tag for every log. Default PRETTY_LOGGER
 *          .build();
 *      RiumLog.addLogAdapter(new AndroidLogAdapter(formatStrategy));//默认的安卓打印
 *
 *      //打印带标签的log
 *      RiumLog.d("ffff");
 *      //打印带临时标签的log
 *      RiumLog.t("tmp-tag").d("ffff");
 */
public class LogUtil {
    private static Logger logger;

    static {
        XLog.init(LogLevel.ALL);//初始化否则报错
        Printer androidPrinter = new AndroidPrinter();
        logger = new Logger.Builder()
                .nt()
                .tag("xlog")
                .nb()
                .nst()
                .printers(androidPrinter)
                .build();
    }

    /**
     * 初始化Log，这个初始化会打印到控制台和文件
     *
     * @param tag    日志TAG
     * @param level  低于level的将不会被打印。值请参考 {@link LogLevel}
     * @param logDir 打印日志保存文件的地址，为null的时候标示不打印
     */
    public static void initLog(String tag, int level, String logDir) {
        Printer androidPrinter = new AndroidPrinter();                                     // 通过 android.util.Log 打印日志的打印器

        if (!StringUtils.isEmpty(logDir)){
            LogUtil.HistoryDateFileNameGenerator fileNameGenerator = new LogUtil.HistoryDateFileNameGenerator(3, logDir);
            Printer filePrinter = new FilePrinter                                               // 打印日志到文件的打印器
                    .Builder(logDir)                                                            // 指定保存日志文件的路径
                    .fileNameGenerator(fileNameGenerator)                                       // 指定日志文件名生成器，默认为 ChangelessFileNameGenerator("log")
                    .backupStrategy(new FileSizeBackupStrategy(500 * 1024 * 1024))    // 指定日志文件备份策略，默认为 FileSizeBackupStrategy(1024 * 1024)
                    .logFlattener(new DefaultFlattener())                                       // 指定日志平铺器，默认为 DefaultFlattener
                    .build();
            buildLogger(level, tag, androidPrinter, filePrinter);
        } else {
            buildLogger(level, tag, androidPrinter);
        }

    }

    /**
     * @param level    see {@link LogLevel}低于level的将不会被打印
     * @param tag
     * @param printers
     */
    private static void buildLogger(int level, String tag, Printer... printers) {
        logger = new Logger.Builder()
                .nt()
                .tag(tag)
                .logLevel(level)
                .nb()
                .nst()
                .printers(printers)
                .build();
    }

    public static void d(@NonNull String message) {
        logger.d(message);
    }

    public static void dt(@NonNull String tag, @NonNull String message) {
        message = String.format("[%s] : %s", tag, message);
        logger.d(message);
    }

    public static void dd(@NonNull String message, Object... args) {
        logger.d(String.format(message, args));
    }

    public static void ddt(@NonNull String tag, @NonNull String message, Object... args) {
        message = String.format("[%s] : %s", tag, String.format(message, args));
        logger.d(message);
    }

    public static void i(@NonNull String message) {
        logger.i(message);
    }

    public static void it(@NonNull String tag, @NonNull String message) {
        message = String.format("[%s] : %s", tag, message);
        logger.i(message);
    }

    public static void ii(@NonNull String message, Object... args) {
        logger.i(String.format(message, args));
    }

    public static void iit(@NonNull String tag, @NonNull String message, Object... args) {
        message = String.format("[%s] : %s", tag, String.format(message, args));
        logger.i(message);
    }

    public static void v(@NonNull String message, @Nullable Object... args) {
        logger.v(message, args);
    }

    public static void vt(@NonNull String tag, @NonNull String message) {
        message = String.format("[%s] : %s", tag, message);
        logger.v(message);
    }

    public static void vv(@NonNull String message, Object ...args) {
        logger.v(String.format(message, args));
    }

    public static void vvt(@NonNull String tag, @NonNull String message, Object ...args) {
        message = String.format("[%s] : %s", tag, String.format(message, args));
        logger.v(message);
    }

    public static void w(@NonNull String message, @Nullable Object... args) {
        logger.w(message, args);
    }

    public static void wt(@NonNull String tag, @NonNull String message) {
        message = String.format("[%s] : %s", tag, message);
        logger.w(message);
    }

    public static void ww(@NonNull String message, Object ...args) {
        logger.w(String.format(message, args));
    }

    public static void wwt(@NonNull String tag, @NonNull String message, Object ...args) {
        message = String.format("[%s] : %s", tag, String.format(message, args));
        logger.w(message);
    }


    public static void e(@NonNull String message, @Nullable Object... args) {
        logger.e(message, args);
    }

    public static void et(@NonNull String tag, @NonNull String message) {
        message = String.format("[%s] : %s", tag, message);
        logger.e(message);
    }

    public static void ee(@NonNull String message, Object ...args) {
        logger.e(String.format(message, args));
    }

    public static void eet(@NonNull String tag, @NonNull String message, Object ...args) {
        message = String.format("[%s] : %s", tag, String.format(message, args));
        logger.e(message);
    }

    /**
     * Formats the given json content and print it
     */
    public static void json(@Nullable String json) {
        logger.json(json);
    }

    /**
     * Formats the given xml content and print it
     */
    public static void xml(@Nullable String xml) {
        logger.xml(xml);
    }


    public static class Setter {
        String tag;
        Printer[] printers;

        private Setter() {
        }

        public static Setter newSetter() {
            return new Setter();
        }

        public Setter tag(String tag) {
            this.tag = tag;
            return this;
        }

        public Setter printers(Printer... printers) {
            this.printers = printers;
            return this;
        }

        public void set() {
            if (printers == null) {
                printers = new Printer[]{new AndroidPrinter()};
            }

            logger = XLog.tag(tag)
                    .nt()
                    .nb()
                    .nst()
                    .printers(printers)
                    .build();
        }
    }

    public static class HistoryDateFileNameGenerator implements FileNameGenerator {
        int history;
        String logdir;

        ThreadLocal<SimpleDateFormat> localDateFormat = new ThreadLocal<SimpleDateFormat>() {
            @Override
            protected SimpleDateFormat initialValue() {
                return new SimpleDateFormat("yyyy-MM-dd", Locale.US);
            }
        };

        /**
         * @param history 保存历史天数
         * @param logdir  log目录
         */
        public HistoryDateFileNameGenerator(int history, String logdir) {
            this.logdir = logdir;

            if (history <= 0) {
                this.history = 3;
            } else {
                this.history = history;
            }
        }

        @Override
        public boolean isFileNameChangeable() {
            return true;
        }

        /**
         * Generate a file name which represent a specific date.
         * 此处设置保留几天的任务
         */
        @Override
        public String generateFileName(int logLevel, long timestamp) {
            SimpleDateFormat sdf = localDateFormat.get();
            sdf.setTimeZone(TimeZone.getDefault());
            String now = sdf.format(new Date(timestamp));
            deleteHistory(sdf, now);
            return now;
        }

        /**
         * 删除7天前的数据
         */
        public void deleteHistory(SimpleDateFormat sdf, String now) {
            List<File> files = FileUtils.listFilesInDir(logdir);
            if (files.size() <= history)
                return;

            for (File file : files) {
                String name = file.getName();
                if (!isDate(sdf, name)) {
                    //文件名格式错误，直接删除
                    FileUtils.deleteFile(file);
                    continue;
                }

                long span = TimeUtils.getTimeSpan(name, now, sdf, TimeConstants.DAY);
                if (span < 3) {
                    FileUtils.deleteFile(file);
                }
            }
        }

        /**
         * 校验date格式是否正确
         *
         * @param sdf
         * @param date
         * @return
         */
        public boolean isDate(SimpleDateFormat sdf, String date) {
            try {
                sdf.parse(date);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
    }
}