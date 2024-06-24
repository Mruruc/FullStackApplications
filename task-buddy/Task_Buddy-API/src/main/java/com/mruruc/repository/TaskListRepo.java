package com.mruruc.repository;

import com.mruruc.model.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskListRepo extends JpaRepository<Tasks,Long>{
}
