package com.example.http.ui.theme.api;

import static com.example.http.ui.theme.util.Constants.API_KEY;

import com.example.http.ui.theme.api.res.AuthenRes;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API {
    @GET("/3/authentication/token/new?api_key="+API_KEY)
    Call<AuthenRes> getAuthen();
}
