package com.example.prioritezemejava.data.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertTask(Task task);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAllTasks(List<Task> tasks);

    @Query("SELECT * FROM task_table ORDER BY id ASC")
    LiveData<List<Task>> getTasks();

    @Delete
    void deleteTask(Task task);

    @Update
    void updateTask(Task task);

    @Query("DELETE FROM task_table")
    void deleteAll();
}
