package com.ubk.simplifai;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface apiset {
    @FormUrlEncoded
    @POST("login")
    Call<LoginResponseSuccess> loginUser(@Field("phone") String phone,
                                         @Field("password") String password,
                                         @Field("userType") String userType);
}
