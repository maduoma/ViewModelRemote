package com.dodemy.android.viewmodel.ui.home;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.dodemy.android.viewmodel.model.MenuItem;

import java.util.List;

public class MenuListViewModel extends ViewModel {
    private MenuListRepository repository;
    private LiveData<List<MenuItem>> menuItems;

    public MenuListViewModel() {
        repository = new MenuListRepository();
    }

    public LiveData<List<MenuItem>> getMenuItems() {
        // Fetch data only when it's null
        if (menuItems == null) {
            menuItems = repository.getMenu();
        }

        return menuItems;
    }
}
