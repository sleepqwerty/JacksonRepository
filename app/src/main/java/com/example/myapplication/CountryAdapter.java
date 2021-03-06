package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> implements Filterable {
    private ArrayList<Country> mCountries;
    private ArrayList<Country> mCountriesFiltered;
    private Listener mListener;
    public CountryAdapter(ArrayList<Country> countries, Listener listener) {
        mCountries = countries;
        mCountriesFiltered = countries;
        mListener = listener;
    }
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String searchQuery = constraint.toString();
                if(searchQuery.isEmpty()) {
                    mCountriesFiltered = mCountries;
                } else {
                    ArrayList<Country> filterList = new ArrayList<>();
                    for (Country country : mCountries) {
                        if(country.getCountry().toLowerCase().contains(searchQuery.toLowerCase())) {
                            filterList.add(country);
                        }
                    }
                    mCountriesFiltered = filterList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mCountriesFiltered;
                return filterResults;
            }
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mCountriesFiltered = (ArrayList<Country>) results.values;
                notifyDataSetChanged();
            }
        };
    }
    public interface Listener {
        void onClick(View view,String countryCode);
    }
    @NonNull
    @Override
    public CountryAdapter.CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_list_row, parent, false);
        CountryViewHolder holder = new CountryViewHolder(v, mListener);
        return holder;
    }
    @Override
    public void onBindViewHolder(@NonNull CountryAdapter.CountryViewHolder holder, int position) {
        Country country = mCountriesFiltered.get(position);
        DecimalFormat df = new DecimalFormat("#,###,###,###");
        holder.country.setText(country.getCountry());
        holder.totalCases.setText(df.format(country.getTotalConfirmed()));
        holder.newCases.setText(df.format(country.getNewConfirmed()));
        holder.itemView.setTag(country.getCountryCode());
    }
    @Override
    public int getItemCount() {
        return mCountriesFiltered.size();
    }
    public class CountryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView country, totalCases, newCases;
        private Listener listener;
        public CountryViewHolder(@NonNull View itemView, Listener listener) {
            super(itemView);
            this.listener = listener;
            itemView.setOnClickListener(this);
            country = itemView.findViewById(R.id.tvCountry);
            totalCases = itemView.findViewById(R.id.tvTotalCases);
            newCases = itemView.findViewById(R.id.tvNewCases);
        }
        @Override
        public void onClick(View v) {
            listener.onClick(v, (String) v.getTag());
        }
    }
    public void sort(final int sortMethod) {
        // sort the list
        if (mCountriesFiltered.size() > 0) {
            Collections.sort(mCountriesFiltered, new Comparator<Country>() {
                @Override
                public int compare(Country o1, Country o2) {
                    if(sortMethod == 1) {
                        //Sort by new cases
                        return o2.getNewConfirmed().compareTo(o1.getNewConfirmed());
                    } else {
                        //Sort by total Cases
                        o2.getTotalConfirmed().compareTo(o1.getTotalConfirmed());
                    }
                    //Default sort method
                    return o2.getTotalConfirmed().compareTo(o1.getTotalConfirmed());
                }
            });
        }
        //Notify the adapter on changes
        notifyDataSetChanged();
    }
}