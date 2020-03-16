package com.dodemy.android.viewmodel.ui.home;


import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.dodemy.android.viewmodel.model.MenuItem;
import com.dodemy.android.viewmodel.networking.WebClient;
import com.dodemy.android.viewmodel.networking.WebService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MenuListRepository {
    private static final String TAG = MenuListRepository.class.getSimpleName();

    private WebService webClient;

    public MenuListRepository() {
        webClient = WebClient.getClient().create(WebService.class);
    }

    /**
     * The menu should be fetched from local storage first then fetch from
     * network. The local cache can be maintained using Room, but considering the
     * scope, Room is ignored. The calling origin wouldn't know where the data is
     * fetched from whether from local storage or network
     */
    public LiveData<List<MenuItem>> getMenu() {
        final MutableLiveData<List<MenuItem>> data = new MutableLiveData<>();

        Call<List<MenuItem>> call = webClient.getMenu();
        call.enqueue(new Callback<List<MenuItem>>() {
            @Override
            public void onResponse(Call<List<MenuItem>> call, Response<List<MenuItem>> response) {
                data.setValue(response.body());
            }

            // TODO - handle error
            // https://stackoverflow.com/questions/44208618/how-to-handle-error-states-with-livedata
            @Override
            public void onFailure(Call<List<MenuItem>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
        return data;
    }
}
