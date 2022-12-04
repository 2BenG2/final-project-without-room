package com.lectures.finalproject.ui.my_lists;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.lectures.finalproject.R;
import com.lectures.finalproject.adapters.ListAdapter;
import com.lectures.finalproject.controllers.lists.ListManager;
import com.lectures.finalproject.databinding.FragmentMyListsBinding;
import com.lectures.finalproject.enums.ContentType;
import com.lectures.finalproject.listeners.PickListListener;
import com.lectures.finalproject.models.content.Content;
import com.lectures.finalproject.tools.PopupNewList;
import com.lectures.finalproject.tools.PopupPickList;

public class MyListsFragment extends Fragment implements PickListListener {

    private FragmentMyListsBinding binding;
    private MyListsViewModel myListsViewModel;
    private FloatingActionButton btnAddNewList;
    private Button backBtn;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        myListsViewModel = new ViewModelProvider(this).get(MyListsViewModel.class);
        binding = FragmentMyListsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        return root;
    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.rv_my_lists);

        myListsViewModel.getLists().observe(getViewLifecycleOwner(),lists->{
            recyclerView.setAdapter(new ListAdapter(backBtn,this));
            recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), 1));
        });
        backBtn = view.findViewById(R.id.btn_back_to_lists);
        backBtn.setOnClickListener(v->{
            recyclerView.setAdapter(new ListAdapter(backBtn,this));
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
            backBtn.setVisibility(View.INVISIBLE);
        });
        btnAddNewList = view.findViewById(R.id.btn_add_new_list);
        btnAddNewList.setOnClickListener(view1 -> {
            PopupNewList popup = new PopupNewList(getActivity());
            popup.show();
            ListManager.getInstance().listsUpdated();
        });


    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClick(Content content) {
        PopupPickList popupPickList = new PopupPickList(getActivity(), ListAdapter.contentTypeForList,content);
        popupPickList.show();
    }
}