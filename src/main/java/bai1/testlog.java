package bai1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class testlog implements Runnable {

	Thread trd;

    testlog() {
        trd = new Thread( this);
        trd.start();
    }
    static Logger logger = LoggerFactory.getLogger(testlog.class);

    public void run() {
        MDC.put("logName", "LOG_NAME");
        logger.warn("test1");
        logger.info("test2");
        logger.error("test");
        MDC.remove("logName");
    }

    public static void main(String[] args) {
        String URL = "logback.xml";
        System.out.println(ClassLoader.getSystemResource(URL));
        int count = 1;
        while(count<=3){
            testlog head = new testlog();
            count++;
        }
    }
}
