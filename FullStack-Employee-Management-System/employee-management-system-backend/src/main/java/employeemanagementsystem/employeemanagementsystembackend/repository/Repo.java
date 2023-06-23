package employeemanagementsystem.employeemanagementsystembackend.repository;

import employeemanagementsystem.employeemanagementsystembackend.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Repo extends JpaRepository<Employee,Long> {
}
