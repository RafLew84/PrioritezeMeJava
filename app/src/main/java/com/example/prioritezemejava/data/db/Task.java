package com.example.prioritezemejava.data.db;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "task_table")
public class Task {
    @PrimaryKey(autoGenerate = true)
    private int id = 0;
    private String title;
    private String description;
    private boolean isDone;
    private Priority priority;

    @Ignore
    public Task(String title, String description) {
        this.title = title;
        this.description = description;
        this.isDone = false;
        this.priority = Priority.NORMALNY;
    }

    public Task(String title, String description, boolean isDone, Priority priority) {
        this.title = title;
        this.description = description;
        this.isDone = isDone;
        this.priority = priority;
    }

    @Ignore
    public Task(String title, String description, Priority priority) {
        this.title = title;
        this.description = description;
        this.isDone = false;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, isDone, priority);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id &&
                isDone == task.isDone &&
                title.equals(task.title) &&
                description.equals(task.description) &&
                priority == task.priority;
    }
}
