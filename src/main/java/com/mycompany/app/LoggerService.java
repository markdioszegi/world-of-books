package com.mycompany.app;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class LoggerService {
    // #region Field vars
    private boolean dumpToFile = true; // Dumping is enabled by default
    private String dumpFolder = "logs/"; // The folder to dump log files
    private boolean disabled = false;
    private String filePath;

    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String MAGENTA = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";
    // #endregion

    // #region Constructors
    public LoggerService() {
        setFileName();
    }

    public LoggerService(String path) {
        setFileName();
        this.dumpFolder = path;
    }

    public LoggerService(boolean dumpToFile) {
        setFileName();
        this.dumpToFile = dumpToFile;
    }

    public LoggerService(String path, boolean dumpToFile) {
        setFileName();
        this.dumpFolder = path;
        this.dumpToFile = dumpToFile;
    }
    // #endregion

    // #region Status methods
    public void info(String msg) {
        String formattedMsg = String.format("[%s][INFO] %s",
                new SimpleDateFormat("yyyy.MM.dd. HH:mm:ss", Locale.getDefault())
                        .format(new Date()),
                msg);

        String coloredMsg = String.format("[%s] %s[INFO]%s %s",
                new SimpleDateFormat("yyyy.MM.dd. HH:mm:ss", Locale.getDefault())
                        .format(new Date()),
                CYAN, WHITE, msg);

        if (!disabled)
            System.out.println(coloredMsg);

        if (dumpToFile)
            dumpToFile(formattedMsg);
    }

    public void error(String msg) {
        String formattedMsg = String.format("[%s][ERROR] %s",
                new SimpleDateFormat("yyyy.MM.dd. HH:mm:ss", Locale.getDefault())
                        .format(new Date()),
                msg);

        String coloredMsg = String.format("[%s] %s[ERROR]%s %s",
                new SimpleDateFormat("yyyy.MM.dd. HH:mm:ss", Locale.getDefault())
                        .format(new Date()),
                RED, WHITE, msg);

        if (!disabled)
            System.out.println(coloredMsg);

        if (dumpToFile)
            dumpToFile(formattedMsg);
    }

    public void success(String msg) {
        String formattedMsg = String.format("[%s][SUCCESS] %s",
                new SimpleDateFormat("yyyy.MM.dd. HH:mm:ss", Locale.getDefault())
                        .format(new Date()),
                msg);

        String coloredMsg = String.format("[%s] %s[SUCCESS]%s %s",
                new SimpleDateFormat("yyyy.MM.dd. HH:mm:ss", Locale.getDefault())
                        .format(new Date()),
                GREEN, WHITE, msg);

        if (!disabled)
            System.out.println(coloredMsg);

        if (dumpToFile)
            dumpToFile(formattedMsg);
    }
    // #endregion

    // Dumping the log file to logs folder
    private void dumpToFile(String msg) {
        // Check if logs directory exists, if not create it
        File logsDir =
                new File(App.class.getProtectionDomain().getCodeSource().getLocation().getPath()
                        + dumpFolder);
        logsDir.mkdirs();

        File f = new File(filePath);
        FileWriter fw;

        try {
            fw = new FileWriter(f, true);
            fw.append(msg + "\n");
            fw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void disable() {
        this.disabled = true;
    }

    public void setFileName() {
        this.filePath = App.class.getProtectionDomain().getCodeSource().getLocation().getPath()
                + dumpFolder + new SimpleDateFormat("yyyy-MM-dd HH-mm-ss", Locale.getDefault())
                        .format(new Date())
                + ".txt";
    }
}
