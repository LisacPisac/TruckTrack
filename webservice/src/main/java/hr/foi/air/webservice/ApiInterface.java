package hr.foi.air.webservice;

import java.util.List;

import entities.DriverJobsResponse;
import entities.DriverModel;

import entities.NewJobRequest;

import entities.RouteModel;

import entities.UserModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Ivan on 1.11.2017..
 */

public interface ApiInterface {
    @GET("driver/all")
    Call<List<DriverModel>> getDrivers();

    @POST("user/auth")
    Call<Boolean> authUser(@Body UserModel body);

    @POST("job/new")
    Call<Void> newJob(@Body NewJobRequest body);

    @GET("driver/{id}")
    Call<List<DriverJobsResponse>> getDriverJobs(@Path("id") String id);

    @POST("job/assign")
    Call<Boolean> jobAssign(@Body int idRuta);

}
