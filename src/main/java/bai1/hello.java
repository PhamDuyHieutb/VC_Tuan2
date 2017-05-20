package bai1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class hello {

	public static void main(String[] args){
		Logger logger = LoggerFactory.getLogger("Hello");
		logger.debug("world");
	}
}
