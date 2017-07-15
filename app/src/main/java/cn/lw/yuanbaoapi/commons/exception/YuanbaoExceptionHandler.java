package cn.lw.yuanbaoapi.commons.exception;

import android.os.Environment;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import cn.lw.yuanbaoapi.App;
import cn.lw.yuanbaoapi.BuildConfig;
import cn.lw.yuanbaoapi.utils.DeviceUtils;


/**
 * 捕获异常信息
 */
public class YuanbaoExceptionHandler implements UncaughtExceptionHandler {
    private static final String TAG = "ExceptionHandler";

    private static YuanbaoExceptionHandler UEHandler;

    public static YuanbaoExceptionHandler getInstance() {
        if (UEHandler == null) {
            UEHandler = new YuanbaoExceptionHandler();
        }
        return UEHandler;
    }

    public YuanbaoExceptionHandler() {
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        writerCrashLogToFile(ex);
        App.getApplication().appExit();
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    private void writerCrashLogToFile(Throwable ex) {

        //如果SD卡不存在或无法使用，则无法把异常信息写入SD卡
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            if (BuildConfig.DEBUG) {
                Log.w(TAG, "sdcard unmounted");
                return;
            }
        }

        try {

            StringBuffer buffer = new StringBuffer();
            Writer writer = new StringWriter();
            PrintWriter printWriter = new PrintWriter(writer);
            ex.printStackTrace(printWriter);
            Throwable cause = ex.getCause();
            while (cause != null) {
                cause.printStackTrace(printWriter);
                cause = cause.getCause();
            }

            printWriter.flush();
            printWriter.close();

            String result = writer.toString();
            buffer.append(result);
            writerToFile(buffer.toString());

        } catch (Exception e) {
        }
    }

    private void writerToFile(String s) {
        try {
            /**
             * 创建日志文件名称
             */
            SimpleDateFormat formate = null;
            if (formate == null) {

                formate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            }
            String timer = formate.format(new Date());

            final String parentDirectory = Environment.getExternalStorageDirectory() + File.separator + "coin_error";
            String fileName = parentDirectory + File.separator + "crash.txt";

            /**
             * 创建日志文件
             */
            File fileDirectory = new File(parentDirectory);
            File file = new File(fileName);
            if (!fileDirectory.exists()) {
                fileDirectory.mkdirs();
            }

            if (!file.exists())
                file.createNewFile();

            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            HashMap<String, String> exceptionMap = new HashMap<>();
            exceptionMap.put("exception", s);
            exceptionMap.put("date", timer);
            exceptionMap.put("AppVersion", String.valueOf(DeviceUtils.getVersionCode(App.getApplication())));
            exceptionMap.put("phone", DeviceUtils.getPhoneProductAndModel());
            bufferedWriter.write(new Gson().toJson(exceptionMap));
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (Exception e) {
        }
    }
}