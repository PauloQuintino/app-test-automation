package beans;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerHelper {

    private Logger log;

    @SuppressWarnings("rawtypes")
    public LoggerHelper(Class className) {
        log = LogManager.getLogger(className);
    }

    public void erro() {

        try {
            throw new Exception("New Exception");
        } catch (Exception e) {
            log.error(e);
        }

    }

    public void info(String INFO) {
        log.info(INFO);
    }

    public void debug(String DEBUG) {
        log.debug(DEBUG);
    }


}
