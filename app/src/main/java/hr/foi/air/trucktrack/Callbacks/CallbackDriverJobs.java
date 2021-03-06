package hr.foi.air.trucktrack.Callbacks;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import entities.DriverJobsResponse;
import entities.RouteModel;
import hr.foi.air.trucktrack.DriverJobsFragment;
import hr.foi.air.trucktrack.Helpers.DataLoadingProgress;
import hr.foi.air.trucktrack.Helpers.FragmentManager;
import hr.foi.air.trucktrack.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.R.attr.duration;
import static android.media.CamcorderProfile.get;

/**
 * Created by Ivan on 7.12.2017..
 */

public class CallbackDriverJobs extends FragmentManager implements Callback<ArrayList<RouteModel>>{
    DriverJobsResponse djResponse;
    ArrayList<RouteModel> listOfRoutes = new ArrayList<RouteModel>();
    RouteModel mRouteModel;

    public CallbackDriverJobs(Object curr, Fragment next) {
        if (curr instanceof Fragment) {
            mCurrent = ((Fragment) curr).getActivity();
        }
        if (curr instanceof Activity) {
            mCurrent = (FragmentActivity)curr;
        }
        mNnext = next;
    }

    @Override
    public void onResponse(Call<ArrayList<RouteModel>> call, Response<ArrayList<RouteModel>> response) {
        //mRouteModel = new RouteModel();
        AVLoadingIndicatorView avi = mCurrent.findViewById(R.id.avi);
        listOfRoutes = response.body();
        avi.hide();
        Log.d("body",listOfRoutes.get(0).getMjestoUtovara());

        mNnext = DriverJobsFragment.getInstance(listOfRoutes);
        showFragment();
    }

    public void onFailure(Call<ArrayList<RouteModel>> call, Throwable t) {
        Log.d("JakoCoolGreska", t.toString());
    }
}
