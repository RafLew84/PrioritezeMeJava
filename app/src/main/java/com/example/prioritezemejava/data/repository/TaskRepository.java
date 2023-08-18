package com.example.prioritezemejava.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.prioritezemejava.data.db.Task;
import com.example.prioritezemejava.data.db.TaskDao;
import com.example.prioritezemejava.data.db.TaskDatabase;
import com.example.prioritezemejava.util.AppExecutors;

import java.util.List;

public class TaskRepository {
    private TaskDatabase db;
    private TaskDao dao;

    public TaskRepository(Application application) {
        db = TaskDatabase.getDatabase(application);
        dao = db.taskDao();
    }

    public LiveData<List<Task>> getTasks() {
        return dao.getTasks();
    }

    public void insertTask(Task task) {
        AppExecutors.getInstance().diskIO().execute(() -> dao.insertTask(task));
    }

    public void insertAllTasks(List<Task> tasks) {
        AppExecutors.getInstance().diskIO().execute(() -> dao.insertAllTasks(tasks));
    }

    public void deleteTask(Task task) {
        AppExecutors.getInstance().diskIO().execute(() -> dao.deleteTask(task));
    }

    public void updateTask(Task task) {
        AppExecutors.getInstance().diskIO().execute(() -> dao.updateTask(task));
    }

    public void deleteAll() {
        AppExecutors.getInstance().diskIO().execute(() -> dao.deleteAll());
    }
}
