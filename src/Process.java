
public class Process {
	public static String getMessage(String str) {
		return str.split("\\[\\*\\*\\]")[1].trim();
	}
    
    public static String getTime(String str) {
    	String[] temp = str.split("\\s+");
        
        //Time
        return temp[0];
    }
    
    //IP Source
    public static String getIPHost(String str) {
    	String[] temp = str.split("\\s+");
    	return temp[temp.length - 3];
    }
    
    public static String getIPDestination(String str) {
    	String[] temp = str.split("\\s+");
    	return temp[temp.length - 1];
    }
    
    public static String getProtocol(String str) {
    	String[] temp = str.split("\\s+");
    	return temp[temp.length - 4]
        		.substring(1, temp[temp.length - 4].length() - 1);
    }
    
}
