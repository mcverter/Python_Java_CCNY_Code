/*
  File: PlanetAdapter.java
  Author: Mitchell Verter   
  Last Revision: 7/19/2012

PlanetAdapter subclasses ArrayAdapter.
It is used in conjunction with the Planet Spinner.

By subclassing ArrayAdapter, we can more readily
(1)  Provide an array for the Planet class
(2) customize the Views for the Spinner.
 */

package com.android.example.spinner;

import android.widget.ArrayAdapter;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;


public class PlanetAdapter extends ArrayAdapter<Planet>
{
	Planet [] planets;
	public Activity context;
	
	public PlanetAdapter(Activity context, int resource, Planet [] planets)
	{
		super(context, resource, planets);
		this.context = context;
		this.planets = planets;
	}

	
    public int getCount(){
       return planets.length;
    }

    public Planet getItem(int position){
       return planets[position];
    }

    @Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) 
	{
		LayoutInflater inflater= context.getLayoutInflater();
		View row=inflater.inflate(R.layout.dropdown_row, parent, false);
		TextView label = (TextView) row.findViewById(R.id.PlanetName);
		label.setText(getItem(position).getName());
		ImageView icon=(ImageView)row.findViewById(R.id.PlanetIcon);
		icon.setImageResource(getItem(position).getImageID());
		return row;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) 
	{
		LayoutInflater inflater= context.getLayoutInflater();
		View row=inflater.inflate(R.layout.spinner_row, parent, false);
		TextView label = (TextView) row.findViewById(R.id.PlanetName);
		label.setText(getItem(position).getName());
		ImageView icon=(ImageView)row.findViewById(R.id.PlanetIcon);
		icon.setImageResource(getItem(position).getImageID());
		return row;
	}

}