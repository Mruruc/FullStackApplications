package employeemanagementsystem.employeemanagementsystembackend.controller;

import employeemanagementsystem.employeemanagementsystembackend.exceptions.ResourceNotFoundException;
import employeemanagementsystem.employeemanagementsystembackend.model.Employee;
import employeemanagementsystem.employeemanagementsystembackend.repository.Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:3000/"})
@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {
    @Autowired
    private Repo repo;

    @GetMapping
    public Optional<List<Employee>> getAllEmployee(){
        return Optional.of(repo.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Employee>> getById(@PathVariable("id") Long requestedId){
        if(repo.existsById(requestedId)){
            return ResponseEntity.ok(repo.findById(requestedId));
        }
        throw new ResourceNotFoundException("ID does not match!");
    }

    @PostMapping
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee newEmp){

       if(!newEmp.equals(null)){
           repo.save(newEmp);
          return new ResponseEntity<>(newEmp, HttpStatus.CREATED);
       }
       throw new ResourceNotFoundException("Employee Information Can Not Be Empty!");
    }
  
    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable long id,@RequestBody Employee employeeDetails) {
        Employee updateEmployee = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + id));

        updateEmployee.setFirstName(employeeDetails.getFirstName());
        updateEmployee.setLastName(employeeDetails.getLastName());
        updateEmployee.setEmail(employeeDetails.getEmail());

        repo.save(updateEmployee);

        return ResponseEntity.ok(updateEmployee);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable long id){

        Employee employee = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + id));

        repo.delete(employee);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}
