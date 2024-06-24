package com.mruruc.repository;

import com.mruruc.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TaskRepo extends JpaRepository<Task,Long> {
    List<Task> getTaskByTasks_Id(Long tasks_id);
}
