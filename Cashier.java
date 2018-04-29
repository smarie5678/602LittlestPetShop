import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Properties;

public class Cashier {

private int cashierID;
private String username;
private String password;
private LocalDateTime lastLogOnDateTime = null;
private LocalDateTime lastLogOffDateTime = null;
private boolean loggedin = false;

private static HashMap<String, Cashier> cashierMap = new HashMap<>();

Cashier(String username, String password) {
		cashierID = username.hashCode();
		this.username = username;
		this.password = password;
		cashierMap.put(username, this);
	}

public String getLogOn() {
	return lastLogOnDateTime.toString();
}

public String getLogOff() {
	return lastLogOffDateTime.toString();
}


public static void initialize() {
	//loads 
	
	Properties cashier = new Properties();
	OutputStream output = new FileOutputStream("cashier.properties");
	InputStream input = new FileInputStream("cashier.properties");
	
	cashier.setProperty("username", "alinaqvi");
	cashier.setProperty("password", "password");
	cashier.setProperty("username", "sarahseitz");
	cashier.setProperty("password", "password");
	cashier.setProperty("username", "russelndip");
	cashier.setProperty("password", "password");
	cashier.setProperty("username", "andyludwig");
	cashier.setProperty("password", "password");
	
	cashier.store(output, null);
	
	
	int count = parseInt(cashier.getProperty("cashier.count", "1"));
	for (int index = 0; index < count; index++) {
		String prefix = "cashier." + index + ".";
		String username = cashier.getProperty(prefix + "username");
		String password = cashier.getProperty(prefix + "password");
		if (username != null && password != null) {
			new Cashier(username, password);
		}
	cashier.load(input);
	}
}

public static void finalize() {
	
	Properties cashierShift = new Properties();
	OutputStream output = new FileOutputStream("cashierShift.properties");
	InputStream  input = new FileInputStream("cashierShift.properties");
	
	cashierShift.setProperty("username", "alinaqvi");
	cashierShift.setProperty("login", Cashier.getLogOn());
	cashierShift.setProperty("logoff", Cashier.getLogOff());
	cashierShift.setProperty("username", "sarahseitz");
	cashierShift.setProperty("login", Cashier.getLogOn());
	cashierShift.setProperty("logoff", Cashier.getLogOff());
	cashierShift.setProperty("username", "russellndip");
	cashierShift.setProperty("login", Cashier.getLogOn());
	cashierShift.setProperty("logoff", Cashier.getLogOff());
	cashierShift.setProperty("username", "andyludwig");
	cashierShift.setProperty("login", Cashier.getLogOn());
	cashierShift.setProperty("logoff", Cashier.getLogOff());
	
	
	cashierShift.store(output, null);
	
	int count = parseInt(cashierShift.getProperty("cashier.count", "1"));
		for (int index = 0; index < count; index++) {
			String prefix = "cashier." + index + ".";
			String username = cashierShift.getProperty(prefix + "username");
			String login = cashierShift.getProperty(prefix + "login");
			String logoff = cashierShift.getProperty(prefix + "logoff");
		}
		cashier.load(input);
}


public String getCashierID() {
	return Integer.toString(cashierID);
}

public boolean login(String username, String password) {
	Cashier cashier = cashierMap.get(username); 
	if(loggedin || cashier == null || cashier.password != password)
		return false;
	lastLogOnDateTime = LocalDateTime.now();
	loggedin = true;
	return true;
}

public void logout() {
	lastLogOffDateTime = LocalDateTime.now();
	loggedin = false;
}

public String logOnDateTime() {
	return lastLogOnDateTime == null ? "N/A" : lastLogOnDateTime.toString();	
}

public String logOffDateTime() {
	return lastLogOffDateTime == null ? "N/A" : lastLogOffDateTime.toString();	
}


}
