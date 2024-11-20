package test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;

public class test {

    public static void main(String[] args) {
    	 Logger logger = LoggerFactory.getLogger(test.class);
         
         ch.qos.logback.classic.Logger log = (ch.qos.logback.classic.Logger)logger;
         log.setLevel(Level.TRACE); // ★デフォルトだと trace レベルは出力されないので、出力のレベルを TRACE にしている

         logger.trace("trace message");
         logger.debug("debug message");
         logger.info("info message");
         logger.warn("warn message");
         logger.error("error message");
    }
}