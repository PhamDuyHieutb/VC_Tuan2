package bai1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class test {
     static int time=0;
      static int olduser=0; 
      static Logger logger = LoggerFactory.getLogger("loguser");
	private static String readAll(Reader rd) throws IOException {
	    StringBuilder sb = new StringBuilder();
	    int cp;
	    while ((cp = rd.read()) != -1) {
	      sb.append((char) cp);
	    }
	    return sb.toString();
	  }

	  public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
	    InputStream is = new URL(url).openStream();
	    try {
	      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
	      String jsonText = readAll(rd);
	      JSONObject json = new JSONObject(jsonText);
	      return json;
	    } finally {
	      is.close();
	    }
	  }
	  
	  public static void check(int use,int olduser1){
		  if(olduser==0) olduser= use;
		  if((use>1.0001*olduser)){
			 logger.info(use+"");
			 olduser=use;
			 time=0;
		  }
		return ;
	  }

	  public static void main(String[] args) throws IOException, JSONException, InterruptedException {
		  JSONObject json = null;
		  
		  
		  
	    while(true){
	    	TimeUnit.SECONDS.sleep(2);
	        json = readJsonFromUrl("http://news.admicro.vn:10002/api/realtime?domain=kenh14.vn");
	    	time+=2;
	    	if(time >12) {
	    		logger.debug(json.getInt("user")+ " ");
	    		olduser= json.getInt("user");
	    		time=0;
	    	}
	    	check(json.getInt("user"), olduser);
	    }

	  }
}
