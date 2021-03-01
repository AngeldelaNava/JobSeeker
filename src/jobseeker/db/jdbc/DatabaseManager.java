package jobseeker.db.jdbc;

import java.util.ArrayList;
import java.util.List;

import jobseeker.db.pojos.Job;
import jobseeker.db.pojos.Person;

public class DatabaseManager {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Job garbageColector = new Job();
		Person manolo = new Person("Manolo", null);
		garbageColector.addPerson(manolo);
		List<Job> jobs = new ArrayList<Job>();
		jobs.add(garbageColector);
		manolo.setJobs(jobs);
		System.out.println(manolo);
	}

}
