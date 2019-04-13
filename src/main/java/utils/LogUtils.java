package utils;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.util.Properties;

/**
 * @author zhouxq
 * @date 2017-2-15
 * @time 19:52
 * description :
 */
public class LogUtils {


    final static String EnvName = "level";


    public static Logger getLogger(Class className) {
        config();
        Logger logger = Logger.getLogger(className);
        setEnvironmentLevelToLevel(logger);
        return logger;
    }

    private static void setEnvironmentLevelToLevel(Logger logger) {
        String level = System.getenv(EnvName);
        if (level != null) {
            logger.setLevel(Level.toLevel(level));
        } else {
            logger.setLevel(Level.DEBUG);
        }
    }

    private static void config() {
        Properties pro = new Properties();
        pro.put("log4j.rootLogger", "info,ConsoleLog");
        pro.put("log4j.appender.ConsoleLog", "org.apache.log4j.ConsoleAppender");
        pro.put("log4j.appender.ConsoleLog.Threshold", "debug");
        pro.put("log4j.appender.ConsoleLog.layout", "org.apache.log4j.PatternLayout");
        pro.put("log4j.appender.ConsoleLog.layout.ConversionPattern", "[%5p] %d{yyyy-MM-dd HH:mm:ss} %c - %m%n");
        PropertyConfigurator.configure(pro);
    }


}
