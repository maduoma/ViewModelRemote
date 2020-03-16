package com.dodemy.android.viewmodel.ui.home;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dodemy.android.viewmodel.R;
import com.dodemy.android.viewmodel.model.MenuItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;



public class MenuListFragment extends Fragment {

    public static final String TAG = MenuListFragment.class.getSimpleName();

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    MenuListViewModel viewModel;
    Unbinder unbinder;
    MenuListAdapter mAdapter;

    public MenuListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_items, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mAdapter = new MenuListAdapter(getActivity());

        // getting ViewModel instance
        viewModel = ViewModelProviders.of(getActivity()).get(MenuListViewModel.class);

        // preparing RecyclerView
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Observing to list items
        viewModel.getMenuItems().observe(this, new Observer<List<MenuItem>>() {
            @Override
            public void onChanged(@Nullable List<MenuItem> menuItems) {
                // displaying item in recycler view
                mAdapter.setItems(menuItems);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        
        if (unbinder != null) {
            unbinder.unbind();
            unbinder = null;
        }
    }
}
