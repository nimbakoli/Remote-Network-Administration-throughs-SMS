package info.server.log;
import org.apache.log4j.*;
public class SystemLogger {
	public static Logger logger;
    public Logger getLoggerInstance(String file)
    {
    	try{
    		logger=Logger.getRootLogger();
			SimpleLayout simple=new SimpleLayout();
			FileAppender f=new FileAppender(simple,file);
			logger.addAppender(f);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return logger;
    }	
	public static void passException(Exception er){
		try{		
			logger.error(er.toString(),er.fillInStackTrace());
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
