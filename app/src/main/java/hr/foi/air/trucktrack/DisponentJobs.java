package hr.foi.air.trucktrack;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;

import java.util.ArrayList;

import entities.RouteModel;
import hr.foi.air.trucktrack.Interface.CustomDialog;
import hr.foi.air.trucktrack.ViewHolders.DisponentJobsFragment;

public class DisponentJobs extends AppCompatActivity implements CustomDialog{
    final int DIALOG_DELETE_JOB = 100;
    final int DIALOG_SAVE_JOB = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disponent_jobs);
        initToolbar();
        findViewById(R.id.addNewJob).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), NewJob.class));
            }
        });

        //kreiranje dummy podataka u svrhu testiranja bez servera
        RouteModel routeJob1 = new RouteModel();
        RouteModel routeJob2 = new RouteModel();
        routeJob1.CreateTestData();
        routeJob2.CreateTestData();
        ArrayList<RouteModel> testList = new ArrayList<>();
        testList.add(routeJob1);
        testList.add(routeJob2);

        showFragment(DisponentJobsFragment.getInstance(testList));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_standard, menu);
        return true;
    }

    public void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.driver_jobs_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_view_headline_white_48px);
        getSupportActionBar().setTitle(getResources().getString(R.string.toolbarPoslovi));
    }

    private void showFragment(Fragment f) {
        FragmentTransaction mTransactiont = getSupportFragmentManager().beginTransaction();
        mTransactiont.replace(R.id.main_container, f, f.getClass().getName());
        mTransactiont.commit();
    }


    @Override
    public void showCustomDialog(int type) {
        if(type == DIALOG_DELETE_JOB) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setNegativeButton("Odustani", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            dialog.setPositiveButton("Obriši", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //BRISANJE API POZIV
                }
            });
            dialog.setTitle("Brisanje posla");
            dialog.setMessage("Potvrdom brisanja posla će rezultirati brisanjem istog iz baze podataka i kao takav više neće postojati za editiranje ili dodjeljivanje posla vozaču");
            dialog.show();
        } else if (type == DIALOG_SAVE_JOB){
            //SPREMANJE POSLA API POZIV
        }
    }
}
