package com.izirapcode.helloworld.calendar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.izirapcode.helloworld.R;
import com.izirapcode.helloworld.database.DbManager;

import java.util.Calendar;

public class CalendarAdapter extends BaseAdapter {

    private final Context context;
    private Calendar calendar;
    private DbManager db;

    public CalendarAdapter(Context context,Calendar calendar,DbManager db) {
        this.context = context;
        this.calendar=calendar;
        this.db=db;
    }

    @Override
    public int getCount() {
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.smoke_grid, null);
        }
        ((TextView)convertView.findViewById(R.id.dayNr)).setText(""+(position+1));
        ((TextView)convertView.findViewById(R.id.smokeNr)).setText(""+db.getDayCount(position+1,calendar.get(Calendar.MONTH)));
        return convertView;
    }


}
