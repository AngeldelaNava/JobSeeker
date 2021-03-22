package jobseeker.db.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
		
		sql1 = "CREATE TABLE people( "
				+ " id INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ " name TEXT NOT NULL)";
		stmnt1.executeUpdate(sql1);
		
		sql1 = "CREATE TABLE jobs_people "
				+ "(job_id INTEGER REFERENCES jobs(id), "
				+ "person_id INTEGER REFERENCES people(id), "
				+ "PRIMARY KEY (job_id, person_id))";
		stmnt1.executeUpdate(sql1);
		} catch(SQLException e) {
			if(!e.getMessage().contains("already exist")) {
				e.printStackTrace();
			}
			System.out.println("There was an exception creating the tables");
			
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
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void addPerson(Person p) {
		Statement stmnt;
		try {
			stmnt = c.createStatement();
			String sql1 = "INSERT INTO people (name) VALUES ('" + p.getName() + "')";
			stmnt.executeUpdate(sql1);
			
		} catch(Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public Person getPerson(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Person> searchPersonByName(String name) {
		Statement stmt;
		List<Person> people = new ArrayList<Person>();
		try {
			stmt = c.createStatement();
			String sql = "SELECT * FROM people WHERE name LIKE '%"+ name +"%'";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int id2 = rs.getInt("id");
				String personName = rs.getString("name");
				Person person = new Person(id2, personName);
				people.add(person);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return people;
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
