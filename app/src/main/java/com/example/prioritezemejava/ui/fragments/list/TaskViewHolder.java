package com.example.prioritezemejava.ui.fragments.list;

import android.graphics.Color;
import android.graphics.PorterDuff;

import androidx.recyclerview.widget.RecyclerView;

import com.example.prioritezemejava.data.db.Task;
import com.example.prioritezemejava.databinding.RvItemBinding;

public class TaskViewHolder extends RecyclerView.ViewHolder {

    private final RvItemBinding binding;

    private final OnPriorityUpClickListener onPriorityUp;
    private final OnPriorityDownClickListener onPriorityDown;
    private final OnTaskDoneClickListener onTaskDone;
    private final OnDeleteClickListener onDelete;
    private final OnEditClickListener onEdit;

    public TaskViewHolder(RvItemBinding binding,
                          OnPriorityUpClickListener onPriorityUp,
                          OnPriorityDownClickListener onPriorityDown,
                          OnTaskDoneClickListener onTaskDone,
                          OnDeleteClickListener onDelete,
                          OnEditClickListener onEdit) {
        super(binding.getRoot());
        this.binding = binding;

        this.onPriorityUp = onPriorityUp;
        this.onPriorityDown = onPriorityDown;
        this.onTaskDone = onTaskDone;
        this.onDelete = onDelete;
        this.onEdit = onEdit;
    }

    public void bind(Task item) {
        binding.title.setText(item.getTitle());
        binding.description.setText(item.getDescription());
        Color color = priorityColor(item);
        binding.priorityIcon.setColorFilter(Color.rgb(color.red(), color.green(), color.blue()), PorterDuff.Mode.SRC_IN);

        binding.priorityUp.setOnClickListener(v -> onPriorityUp.onPriorityUp(item));
        binding.priorityDown.setOnClickListener(v -> onPriorityDown.onPriorityDown(item));
        binding.done.setOnClickListener(v -> onTaskDone.onTaskDone(item));
        binding.delete.setOnClickListener(v -> onDelete.onDelete(item));
        binding.edit.setOnClickListener(v -> onEdit.onEdit(item.getId()));
    }

    private Color priorityColor(Task task) {
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

    public interface OnPriorityUpClickListener {
        void onPriorityUp(Task task);
    }

    public interface OnPriorityDownClickListener {
        void onPriorityDown(Task task);
    }

    public interface OnTaskDoneClickListener {
        void onTaskDone(Task task);
    }

    public interface OnDeleteClickListener {
        void onDelete(Task task);
    }

    public interface OnEditClickListener {
        void onEdit(int position);
    }
}
