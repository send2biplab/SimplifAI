package com.ubk.simplifai;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginApiController {
    private static LoginApiController clienobject;
    private static Retrofit retrofit;

    LoginApiController() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Constant.url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized LoginApiController getInstance() {
        if (clienobject == null)
            clienobject = new LoginApiController();
        return clienobject;
    }

    apiset getapi() {
        return retrofit.create(apiset.class);
    }
}
