package com.kanjistudy.api;

import com.kanjistudy.models.ApiDataset;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ApiKanji {

    @GET()
    Call<ApiDataset> getKanji(@Url String url);


}
