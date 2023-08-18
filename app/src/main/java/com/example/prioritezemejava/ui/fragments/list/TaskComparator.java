package com.example.prioritezemejava.ui.fragments.list;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.example.prioritezemejava.data.db.Task;

public class TaskComparator extends DiffUtil.ItemCallback<Task> {
    @Override
    public boolean areItemsTheSame(@NonNull Task oldItem, @NonNull Task newItem) {
        return newItem == oldItem;
    }

    @Override
    public boolean areContentsTheSame(@NonNull Task oldItem, Task newItem) {
        return newItem.equals(oldItem);
    }
}
