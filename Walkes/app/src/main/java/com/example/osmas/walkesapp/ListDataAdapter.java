package com.example.osmas.walkesapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by osmas on 07/01/2016.
 */
public class ListDataAdapter extends ArrayAdapter{

    List list = new ArrayList();

    public ListDataAdapter(Context context, int resource) {
        super(context, resource);
    }

    static class LayoutHandler
    {
        TextView DATE, START_TIME, APPOINTMENT_TYPE, NAME, ADDRESS;
    }

    @Override
    public void add(Object object)
    {
        super.add(object);
        list.add(object);

    }

    @Override
    public int getCount()
    {
        return list.size();
    }

    @Override
    public Object getItem(int position)
    {
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View row = convertView;
        LayoutHandler layoutHandler;

        if(row == null){
            LayoutInflater layoutInflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            row = layoutInflater.inflate(R.layout.row_layout,parent,false);

            layoutHandler = new LayoutHandler();

            layoutHandler.DATE = (TextView)row.findViewById(R.id.textViewDate);
            layoutHandler.START_TIME = (TextView)row.findViewById((R.id.textViewStartTime));
            layoutHandler.APPOINTMENT_TYPE = (TextView)row.findViewById(R.id.textViewAppointment);
            layoutHandler.NAME = (TextView)row.findViewById(R.id.textViewStaffName);
            layoutHandler.ADDRESS = (TextView)row.findViewById(R.id.textViewAddress);

            row.setTag(layoutHandler);
        }
        else
        {
            layoutHandler = (LayoutHandler) row.getTag();
        }

        DataProvider dataProvider = (DataProvider)this.getItem(position);

        layoutHandler.DATE.setText(dataProvider.getDate());
        layoutHandler.START_TIME.setText(dataProvider.getStart_time());
        layoutHandler.APPOINTMENT_TYPE.setText(dataProvider.getAppointment_type());
        layoutHandler.NAME.setText(dataProvider.getName());
        layoutHandler.ADDRESS.setText(dataProvider.getAddress());

        return row;
    }
}
