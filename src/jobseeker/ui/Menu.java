package jobseeker.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import jobseeker.db.ifaces.DBManager;
import jobseeker.db.jdbc.JDBCManager;
import jobseeker.db.pojos.Person;

public class Menu {
	
	public static DBManager dbman = new JDBCManager();
	public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) {
		
		dbman.connect();
		System.out.println("Database connection opened.");
		
		try {
			do {
			
				System.out.print("Choose an Option\n"
						+ "1. Add a person\n"
						+ "2. See people with the same name that you select\n"
						+ "0. Exit\n"
						+ "Insert option: ");
				int choice = Integer.parseInt(reader.readLine());
				switch(choice) {
				case 0:
					dbman.disconnect();
					System.out.println("Database connection closed");
					System.exit(0);
				case 1: 
					System.out.print("Name: ");
					String name = reader.readLine();
					dbman.addPerson(new Person(1, name));
					break;
				case 2:
					System.out.print("Name: ");
					name = reader.readLine();
					System.out.println(dbman.searchPersonByName(name));
					List<Person> people = dbman.searchPersonByName(name);
					if(people.isEmpty()) {
						System.out.println("No results");
					}
				}
			}while(true);
		} catch(IOException e) {
			
		}
	}

}
