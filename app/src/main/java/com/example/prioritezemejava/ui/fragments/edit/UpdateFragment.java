package com.example.prioritezemejava.ui.fragments.edit;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.prioritezemejava.R;
import com.example.prioritezemejava.data.db.Priority;
import com.example.prioritezemejava.data.db.Task;
import com.example.prioritezemejava.databinding.FragmentUpdateBinding;
import com.example.prioritezemejava.viewmodel.TaskViewModel;

public class UpdateFragment extends Fragment {

    private FragmentUpdateBinding binding;
    private TaskViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentUpdateBinding.inflate(inflater);

        viewModel = new ViewModelProvider(requireActivity()).get(TaskViewModel.class);

        boolean[] enabled = {true};

        int id = getArguments().getInt("task_id", 0);

        Task task = viewModel.getTask(id);

        binding.title.setText(task.getTitle());
        binding.description.setText(task.getDescription());
        setRadio(task.getPriority());
        binding.checkbox.setChecked(task.isDone());

        binding.checkbox.setOnClickListener(v -> {
            enabled[0] = !enabled[0];
            changeRadioEnabled(enabled[0]);
            if (!enabled[0]) {
                binding.radioDone.setChecked(true);
            }
        });

        binding.update.setOnClickListener(v -> {
            String title = binding.title.getText().toString();
            String description = binding.description.getText().toString();

            if (!title.isEmpty() || !description.isEmpty()) {
                Priority priority = getPriority();
                task.setTitle(title);
                task.setDescription(description);
                task.setDone(binding.checkbox.isChecked());
                task.setPriority(priority);
                viewModel.updateTask(task);

                Navigation
                        .findNavController(requireView())
                        .navigate(UpdateFragmentDirections.actionTaskUpdateFragmentToTaskListFragment());
            }
        });

        binding.back.setOnClickListener(v -> Navigation
                .findNavController(requireView())
                .navigate(UpdateFragmentDirections.actionTaskUpdateFragmentToTaskListFragment()));

        binding.delete.setOnClickListener(v -> {
            viewModel.deleteTask(task);

            Navigation
                    .findNavController(requireView())
                    .navigate(UpdateFragmentDirections.actionTaskUpdateFragmentToTaskListFragment());
        });

        return binding.getRoot();
    }

    private void setRadio(Priority priority) {
        if (priority == Priority.WYSOKI) {
            binding.radioHigh.setChecked(true);
        } else if (priority == Priority.NORMALNY) {
            binding.radioNormal.setChecked(true);
        } else if (priority == Priority.NISKI) {
            binding.radioLow.setChecked(true);
        } else {
            binding.radioDone.setChecked(true);
        }
    }

    private Priority getPriority() {
        int checkedRadioButtonId = binding.radio.getCheckedRadioButtonId();
        if (checkedRadioButtonId == binding.radioHigh.getId()) {
            return Priority.WYSOKI;
        } else if (checkedRadioButtonId == binding.radioNormal.getId()) {
            return Priority.NORMALNY;
        } else if (checkedRadioButtonId == binding.radioLow.getId()) {
            return Priority.NISKI;
        } else {
            return Priority.WYKONANY;
        }
    }

    private void changeRadioEnabled(boolean enabled){
        binding.radioDone.setEnabled(enabled);
        binding.radioNormal.setEnabled(enabled);
        binding.radioHigh.setEnabled(enabled);
        binding.radioLow.setEnabled(enabled);
    }
}