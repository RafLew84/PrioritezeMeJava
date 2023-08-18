package com.example.prioritezemejava.viewmodel;

import android.app.Application;
import android.graphics.Color;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.prioritezemejava.data.db.Priority;
import com.example.prioritezemejava.data.db.Task;
import com.example.prioritezemejava.data.dummydata.DataProvider;
import com.example.prioritezemejava.data.repository.TaskRepository;

import java.util.List;

public class TaskViewModel extends AndroidViewModel {

    private final TaskRepository repository;

    private final LiveData<List<Task>> tasksState;

    public TaskViewModel(Application application) {
        super(application);
        repository = new TaskRepository(application);
        tasksState = repository.getTasks();
        reinitializeDatabaseWithDummyData();
    }

    private void reinitializeDatabaseWithDummyData() {
        deleteAll();
        addAll(DataProvider.generateTasks(10));
    }

    private void addAll(List<Task> tasks) {
        for (Task task : tasks) {
            addTask(task);
        }
    }

    private void deleteAll() {
        repository.deleteAll();
    }

    public void updateTask(Task task) {
        repository.updateTask(task);
    }

    public Task getTask(int id) {
        List<Task> tasks = tasksState.getValue();
        return tasks != null ? tasks.stream()
                .filter(task -> task.getId() == id)
                .findFirst()
                .orElse(new Task("", "")) : new Task("", "");
    }

    public void addTask(Task task) {
        repository.insertTask(task);
    }

    public void deleteTask(Task task) {
        repository.deleteTask(task);
    }

    public Color getTaskColor(Task task) {
        switch (task.getPriority()) {
            case WYSOKI:
                return Color.valueOf(Color.RED);
            case NISKI:
                return Color.valueOf(Color.BLUE);
            case NORMALNY:
                return Color.valueOf(Color.GREEN);
            default:
                return Color.valueOf(128f, 203f, 196f);
        }
    }

    public void increasePriority(Task task) {
        Priority nextPriority;
        switch (task.getPriority()) {
            case WYSOKI:
            case NORMALNY:
                nextPriority = Priority.WYSOKI;
                break;
            case NISKI:
                nextPriority = Priority.NORMALNY;
                break;
            default:
                nextPriority = Priority.WYKONANY;
        }
        updateTask(new Task(task.getTitle(), task.getDescription(), task.isDone(), nextPriority));
    }

    public void decreasePriority(Task task) {
        Priority nextPriority;
        switch (task.getPriority()) {
            case WYSOKI:
                nextPriority = Priority.NORMALNY;
                break;
            case NISKI:
            case NORMALNY:
                nextPriority = Priority.NISKI;
                break;
            default:
                nextPriority = Priority.WYKONANY;
        }
        updateTask(new Task(task.getTitle(), task.getDescription(), task.isDone(), nextPriority ));
    }

    public void donePriority(Task task) {
        updateTask(new Task(task.getTitle(), task.getDescription(), task.isDone(), Priority.WYKONANY));
    }
}
