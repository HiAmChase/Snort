import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Log {
	private String time;
	private String message;
	private String protocol;
	private String ipDest;
	
	public static HashMap<String, ArrayList<Log>> logs = new HashMap<String, ArrayList<Log>>();
	
	public Log(String ipHost, String time, 
			String message, String protocol, 
			String ipDest) {
		this.time = time;
		this.message = message;
		this.protocol = protocol;
		this.ipDest = ipDest;
		
		//IPHost la` ip key luu lai cac thong tin cua mot Log
		addLog(ipHost);
	}
	
	private void addLog(String ipHost) {
		//ipHost da ton tai
		if (logs.containsKey(ipHost)) {			
			logs.get(ipHost).add(this);
		}
		else {
			ArrayList<Log> temp = new ArrayList<Log>();
	        temp.add(this);
			logs.put(ipHost, temp);
		}
	}
	
	public void showAll() {
		Set<String> keySet = logs.keySet();
        for (String key : keySet) {
        	System.out.println("IP Host: " + key);
            for (Log log : logs.get(key)) {
				log.showLog();
			}
        }
	}
	
	public void showLog() {
		System.out.println("{" + time);
		System.out.println(message);
		System.out.println(protocol);
		System.out.println(ipDest + "}");
	}
}
