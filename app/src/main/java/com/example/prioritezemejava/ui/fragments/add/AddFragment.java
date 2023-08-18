package com.example.prioritezemejava.ui.fragments.add;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.example.prioritezemejava.R;
import com.example.prioritezemejava.data.db.Priority;
import com.example.prioritezemejava.data.db.Task;
import com.example.prioritezemejava.databinding.FragmentAddBinding;
import com.example.prioritezemejava.viewmodel.TaskViewModel;

public class AddFragment extends Fragment {
    private FragmentAddBinding binding;
    private TaskViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddBinding.inflate(inflater);

        viewModel = new ViewModelProvider(requireActivity()).get(TaskViewModel.class);

        boolean[] enabled = {true};

        binding.radioNormal.setChecked(true);
        binding.checkbox.setOnClickListener(v -> {
            enabled[0] = !enabled[0];
            changeRadioEnabled(enabled[0]);
            if (!enabled[0]) {
                binding.radioDone.setChecked(true);
            }
        });

        binding.save.setOnClickListener(v -> {
            String title = "";
            String description = "";
            if (binding.title.getText() != null && binding.description.getText() != null) {
                title = binding.title.getText().toString();
                description = binding.description.getText().toString();
            }

            if (!title.isEmpty() || !description.isEmpty()) {
                Priority priority = getPriority();
                Task task = new Task(title, description, binding.checkbox.isChecked(), priority);
                viewModel.addTask(task);

                Navigation
                        .findNavController(requireView())
                        .navigate(AddFragmentDirections.actionTaskAddFragmentToTaskListFragment());
            }
        });

        binding.back.setOnClickListener(v -> Navigation
                .findNavController(requireView())
                .navigate(AddFragmentDirections.actionTaskAddFragmentToTaskListFragment()));

        return binding.getRoot();
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