package com.example.prioritezemejava.ui.fragments.list;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.prioritezemejava.R;
import com.example.prioritezemejava.databinding.FragmentListBinding;
import com.example.prioritezemejava.viewmodel.TaskViewModel;

public class ListFragment extends Fragment {
    private FragmentListBinding binding;
    private TaskViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentListBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(TaskViewModel.class);

        TaskAdapter taskAdapter = new TaskAdapter(
                new TaskComparator(),
                task -> viewModel.increasePriority(task),
                task -> viewModel.decreasePriority(task),
                task -> viewModel.donePriority(task),
                task -> viewModel.deleteTask(task),
                taskId -> {
                    ListFragmentDirections.ActionTaskListFragmentToTaskUpdateFragment action =
                            ListFragmentDirections.actionTaskListFragmentToTaskUpdateFragment();
                    action.setTaskId(taskId);
                    Navigation.findNavController(requireView()).navigate(action);
                }
        );

        viewModel.tasksState.observe(getViewLifecycleOwner(), taskAdapter::submitList);

        binding.recycler.setAdapter(taskAdapter);
        binding.recycler.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        binding.recycler.setItemAnimator(null);

        binding.addFab.setOnClickListener(v -> {
            Navigation.findNavController(requireView())
                    .navigate(ListFragmentDirections.actionTaskListFragmentToTaskAddFragment());
        });

        return binding.getRoot();
    }
}