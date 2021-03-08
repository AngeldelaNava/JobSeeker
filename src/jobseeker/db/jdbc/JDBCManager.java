package jobseeker.db.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import jobseeker.db.ifaces.DBManager;
import jobseeker.db.pojos.Job;
import jobseeker.db.pojos.Person;

public class JDBCManager implements DBManager {

	private Connection c;
	@Override
	public void connect() {
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:./db/jobSeeker.db");
			c.createStatement().execute("PRAGMA foreign_keys=ON");
			System.out.println("Database connection opened.");
			this.createTables();
		}
		catch (SQLException e) {
			System.out.println("There was a database exception.");
			e.printStackTrace();
		}
		catch (Exception e) {
			System.out.println("There was a general exception");
		}
		
	}

	private void createTables() {
		// If the tables are not created already, create them
		//      Create the jobs table
		try {
		Statement stmnt1 = c.createStatement();
		String sql1 = "CREATE TABLE jobs "
				+ "(id INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ " name TEXT NOT NULL, "
				+ " description TEXT NOT NULL, "
				+ " salary REAL NOT NULL, "
				+ " startDate DATE NOT NULL, "
				+ " endDate DATE NOT NULL)";
		stmnt1.executeUpdate(sql1);
		} catch(SQLException e) {
			System.out.println("There was an exception creating the tables");
			e.printStackTrace();
		}
//		private Integer id;
//		private String name;
//		private String description;
//		private float salary;
//		private Date startDate;
//		private Date endDate;
//		private List<Person> people;
		//      Create the people table
		//      Create the jobs_people table
	}
	
	@Override
	public void disconnect() {
		try {
			c.close();
			System.out.println("Database connection closed.");
		} catch (SQLException e) {
			System.out.println("There was a database exception.");
			e.printStackTrace();
		}

	}

	@Override
	public void addPerson(Person p) {
		// TODO Auto-generated method stub

	}

	@Override
	public Person getPerson(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Person> searchPersonByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addJob(Job b) {
		// TODO Auto-generated method stub

	}

	@Override
	public Job getJob(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Job> searchJobByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void hire(Person p, Job j) {
		// TODO Auto-generated method stub

	}

}
