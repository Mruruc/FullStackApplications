package employeemanagementsystem.employeemanagementsystembackend;

import employeemanagementsystem.employeemanagementsystembackend.model.Employee;
import employeemanagementsystem.employeemanagementsystembackend.repository.Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmployeeManagementSystemBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeManagementSystemBackendApplication.class, args);
	}

/*	@Autowired
	private Repo repo;

	@Override
	public void run(String... args) throws Exception {
		Employee employee1=new Employee();
		employee1.setFirstName("Ali");
		employee1.setLastName("Guzel");
		employee1.setEmail("guzel@gmail.com");
		repo.save(employee1);

		Employee employee2 = new Employee();
		employee2.setFirstName("John");
		employee2.setLastName("Doe");
		employee2.setEmail("john.doe@gmail.com");
		repo.save(employee2);

		Employee employee3 = new Employee();
		employee3.setFirstName("Jane");
		employee3.setLastName("Smith");
		employee3.setEmail("jane.smith@gmail.com");
		repo.save(employee3);

		Employee employee4 = new Employee();
		employee4.setFirstName("Robert");
		employee4.setLastName("Johnson");
		employee4.setEmail("robert.johnson@gmail.com");
		repo.save(employee4);

		Employee employee5 = new Employee();
		employee5.setFirstName("Emily");
		employee5.setLastName("Davis");
		employee5.setEmail("emily.davis@gmail.com");
		repo.save(employee5);

	}*/
}
