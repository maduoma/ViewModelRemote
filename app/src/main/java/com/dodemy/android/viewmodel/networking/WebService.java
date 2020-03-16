package com.dodemy.android.viewmodel.networking;


import com.dodemy.android.viewmodel.model.MenuItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WebService {

    @GET("menu.json")
    Call<List<MenuItem>> getMenu();
}
