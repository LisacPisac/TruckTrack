package hr.foi.air.trucktrack;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import entities.DriverModel;
import hr.foi.air.drivermodule.ListViewFragment;
import hr.foi.air.drivermodule.GridViewFragment;
import hr.foi.air.trucktrack.Callbacks.CallbackDriverList;
import hr.foi.air.webservice.ApiClient;
import hr.foi.air.webservice.ApiInterface;
import retrofit2.Call;

public class Drivers extends AppCompatActivity implements ListViewFragment.ToolbarListener {

    private List<DriverModel> drivers = null;
    private int changeImage = 1;
    private ApiInterface apiService;
    Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drivers);

        initToolbar();
        getDrivers();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_drivers, menu);

        menu.findItem(R.id.viewIcon).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (changeImage == 1) {
                    item.setIcon(R.drawable.ic_dashboard_white_48px);
                    changeImage = 0;
                    fragment = ListViewFragment.getInstance(drivers);
//                    showFragment(fragment);
                    getDrivers();
                }
                else {
                    item.setIcon(R.drawable.ic_view_list_white_48px);
                    changeImage = 1;
                    fragment = GridViewFragment.getInstance(drivers);
//                    showFragment(fragment);
                    getDrivers();
                }
                return true;
            }
        });

        menu.findItem(R.id.refreshIcon).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                getDrivers();
                return true;
            }
        });
        return true;
    }

    private void showFragment(Fragment f) {
        FragmentTransaction mTransactiont = getSupportFragmentManager().beginTransaction();
        mTransactiont.replace(R.id.main_container, f, f.getClass().getName());
        mTransactiont.commit();
    }

    public void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.drivers_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_view_headline_white_48px);
        getSupportActionBar().setTitle(getResources().getString(R.string.toolbarVozaci));
    }

    private void getDrivers() {
        apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<DriverModel>> call = apiService.getDrivers();
        call.enqueue(new CallbackDriverList(this,fragment));
    }

    @Override
    public void onFragmentAttached(boolean change) {
        //needs to be implemented
    }
}
