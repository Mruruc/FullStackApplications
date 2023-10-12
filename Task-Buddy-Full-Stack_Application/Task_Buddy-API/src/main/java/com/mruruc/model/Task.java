package com.mruruc.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "Task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "task_name")
    private String taskName;

    private Boolean completed;

    /**
     * @Temporal(TemporalType.TIMESTAMP) removed =>Date Annotation:
     * The @Temporal annotation is used with java.util.Date and java.util.Calendar types.
     * Since you're using LocalDateTime, you don't need the @Temporal annotation.
     */

    private LocalDateTime dateTime;

    @ManyToOne
    private Tasks tasks;


    public Task() {
    }

    public Task( String taskName, Boolean completed, LocalDateTime dateTime, Tasks tasks) {

        this.taskName = taskName;
        this.completed = completed;
        this.dateTime = dateTime;
        this.tasks = tasks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Tasks getTasks() {
        return tasks;
    }

    public void setTasks(Tasks tasks) {
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", taskName='" + taskName + '\'' +
                ", completed=" + completed +
                ", dateTime=" + dateTime +
                ", tasks=" + tasks +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id) && Objects.equals(taskName, task.taskName) && Objects.equals(completed, task.completed) && Objects.equals(dateTime, task.dateTime) && Objects.equals(tasks, task.tasks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, taskName, completed, dateTime, tasks);
    }
}



