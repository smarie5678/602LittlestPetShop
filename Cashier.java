package possystem;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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

	static Properties defaultProperties = new Properties();

	static {

		try {
			InputStream input = new FileInputStream(Cashier.class.getName().toLowerCase() + "-default.properties");
			defaultProperties.load(input);
		} catch (IOException e) {
			defaultProperties.setProperty("cashier.count", "0");
		}

	}

	private Cashier(String username, String password) {
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


	public static void initializer() throws IOException {

		Properties properties = new Properties(defaultProperties);

		try {
			InputStream input = new FileInputStream(Cashier.class.getName().toLowerCase() + ".properties");
			properties.load(input);
		}
		catch (FileNotFoundException e) {
		}
		catch (IOException e) {
			throw e;
		}

		int count = Integer.parseInt(properties.getProperty("cashier.count", "1").trim());
		for (int index = 0; index < count; index++) {
			String prefix = "cashier." + index + ".";
			String username = properties.getProperty(prefix + "username").trim();
			String password = properties.getProperty(prefix + "password").trim();
			String lastLogOnTime = properties.getProperty(prefix + "lastLogOnTime".trim());
			String lastLogOffTime = properties.getProperty(prefix + "lastLogOffTime".trim());
			if (username != null && password != null) {
				new Cashier(username, password);
			}
		}
	}

	public static void finalizer() throws IOException {

		Properties properties = new Properties();
		properties.setProperty("cashier.count", cashierMap.size() + "");

		int index = 0;
		for (Cashier cashier : cashierMap.values()) { 
			String prefix = "cashier." + index++ + ".";
			properties.setProperty(prefix + "username", cashier.username);
			properties.setProperty(prefix + "password", cashier.password);
			properties.setProperty(prefix + "lastLogOnTime", cashier.getLogOn());
			properties.setProperty(prefix + "lastLogOffTime", cashier.getLogOff());
		}

		OutputStream output = new FileOutputStream(Cashier.class.getName().toLowerCase() + ".properties");
		properties.store(output, null);
	}

	public static Cashier findCashierbyID( int cashierID) {
		return cashierMap.get(cashierID);
	}

	public static Cashier findCashierbyUsername (String username) {
		return cashierMap.get(username);
	}


	public  String getCashierID() {
		return Integer.toString(cashierID);
	}

	public static Cashier login(String username, String password) {
		Cashier cashier = cashierMap.get(username);
		if(cashier == null || cashier.loggedin || cashier.password != password)
			return null;
		cashier.lastLogOnDateTime = LocalDateTime.now();
		cashier.loggedin = true;
		return cashier;
	}

	public void logout() {
		lastLogOffDateTime = LocalDateTime.now();
		loggedin = false;
	}

	public String logOnDateTime() {
		return lastLogOnDateTime == null ? "N/A" : lastLogOnDateTime.toString();	
	}

	public void updateDrawerFromSale( double payment) {
		Drawer.updateDrawerFromSale(payment);
	}

        public void updateDrawerFromReturn(double refundAmt){
            
        }
	public String logOffDateTime() {
		return lastLogOffDateTime == null ? "N/A" : lastLogOffDateTime.toString();	
	}
	public static void main(String[] args) {
		try {
			initializer();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
