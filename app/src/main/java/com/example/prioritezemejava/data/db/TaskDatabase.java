package com.example.prioritezemejava.data.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Task.class}, version = 1, exportSchema = false)
public abstract class TaskDatabase extends RoomDatabase {
    public abstract TaskDao taskDao();

    private static volatile TaskDatabase Instance;

    public static TaskDatabase getDatabase(Context context) {
        if (Instance == null) {
            synchronized (TaskDatabase.class) {
                if (Instance == null) {
                    Instance = Room.databaseBuilder(context.getApplicationContext(),
                                    TaskDatabase.class, "task_database_java")
                            .build();
                }
            }
        }
        return Instance;
    }
}
