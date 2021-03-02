package jobseeker.ui;

import jobseeker.db.ifaces.DBManager;
import jobseeker.db.jdbc.JDBCManager;

public class Menu {

	public static void main(String[] args) {
		DBManager dbman = new JDBCManager();
		dbman.connect();
		dbman.disconnect();
	}

}
