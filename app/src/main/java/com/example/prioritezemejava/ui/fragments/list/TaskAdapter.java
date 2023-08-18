package com.example.prioritezemejava.ui.fragments.list;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;

import com.example.prioritezemejava.data.db.Task;
import com.example.prioritezemejava.databinding.RvItemBinding;

public class TaskAdapter extends ListAdapter<Task, TaskViewHolder> {

    private final TaskViewHolder.OnPriorityUpClickListener onPriorityUp;
    private final TaskViewHolder.OnPriorityDownClickListener onPriorityDown;
    private final TaskViewHolder.OnTaskDoneClickListener onTaskDone;
    private final TaskViewHolder.OnDeleteClickListener onDelete;
    private final TaskViewHolder.OnEditClickListener onEdit;

    protected TaskAdapter(TaskComparator taskComparator,
                          TaskViewHolder.OnPriorityUpClickListener onPriorityUp,
                          TaskViewHolder.OnPriorityDownClickListener onPriorityDown,
                          TaskViewHolder.OnTaskDoneClickListener onTaskDone,
                          TaskViewHolder.OnDeleteClickListener onDelete,
                          TaskViewHolder.OnEditClickListener onEdit) {
        super(taskComparator);
        this.onPriorityUp = onPriorityUp;
        this.onPriorityDown = onPriorityDown;
        this.onTaskDone = onTaskDone;
        this.onDelete = onDelete;
        this.onEdit = onEdit;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TaskViewHolder(
                RvItemBinding.inflate(
                        LayoutInflater.from(parent.getContext()), parent, false
                ),
                onPriorityUp,
                onPriorityDown,
                onTaskDone,
                onDelete,
                onEdit
        );
    }

    @Override
    public void onBindViewHolder(TaskViewHolder holder, int position) {
        Task item = getItem(position);
        holder.bind(item);
    }
}