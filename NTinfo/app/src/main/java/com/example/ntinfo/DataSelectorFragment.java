package com.example.ntinfo;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;

import androidx.fragment.app.Fragment;

import java.util.Calendar;

public class DataSelectorFragment extends Fragment {
    static int myYear = 2020;
    static int myMonth = 06;

    public DataSelectorFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.data_selector_page, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        Button b = getActivity().findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog tpd = new DatePickerDialog(getContext(), myCallBack, myYear, myMonth, 1);
                tpd.getDatePicker().setMaxDate(Calendar.getInstance().getTime().getTime());
                Calendar c = Calendar.getInstance();
                c.set(1860, 0, 1);
                tpd.getDatePicker().setMinDate(c.getTime().getTime());
                tpd.show();
            }
        });
    }

    DatePickerDialog.OnDateSetListener myCallBack = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            myYear = year;
            myMonth = monthOfYear;
            new DataGetter(getContext()).GetForDate(monthOfYear + 1, year);
        }
    };
}
