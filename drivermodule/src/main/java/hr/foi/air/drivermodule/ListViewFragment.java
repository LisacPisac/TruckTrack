package hr.foi.air.drivermodule;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import entities.DriverModel;

/**
 * Created by roman on 10/31/17.
 */

public class ListViewFragment extends android.support.v4.app.Fragment {
    ToolbarListener mCallback;
    DriverSelectFromListInterface interfaceSelectedDriver;

    public interface ToolbarListener {
        public void onFragmentAttached(boolean change);
    }

    RecyclerView mRecyclerView;
    static ListViewFragment instance = null;
    static List<DriverModel> data = null;
    static DriversAdapter adapter;

    public static ListViewFragment getInstance(List<DriverModel> dataDrivers) {
        if(instance == null) {
            instance = new ListViewFragment();
        }
        data = dataDrivers;
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rv, container, false);
        mRecyclerView = view.findViewById(R.id.rvDrivers);
        showRecycleView(data);
        mCallback.onFragmentAttached(true);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (ToolbarListener) context;
            interfaceSelectedDriver = (DriverSelectFromListInterface) context;

        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement ToolbarListener");
        }
    }

    private void showRecycleView(List<DriverModel> data) {
        adapter = new DriversAdapter(getContext(),data, 1, interfaceSelectedDriver);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    public void updateAdapter(List<DriverModel> dataJobs) {
        data = dataJobs;
        adapter = new DriversAdapter(getContext(), data, 1, interfaceSelectedDriver);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter.notifyDataSetChanged();
    }
}
