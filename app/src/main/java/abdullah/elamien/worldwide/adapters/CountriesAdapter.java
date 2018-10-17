package abdullah.elamien.worldwide.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import abdullah.elamien.worldwide.R;
import abdullah.elamien.worldwide.models.Result;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by AbdullahAtta on 10/17/2018.
 */
public class CountriesAdapter extends RecyclerView.Adapter<CountriesAdapter.CountriesViewHolder> {
    private List<Result> mCountryList;
    private Context mContext;

    public CountriesAdapter(List<Result> mCountryList, Context mContext) {
        this.mCountryList = mCountryList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public CountriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View countriesView = LayoutInflater.from(mContext).inflate(R.layout.list_item_countries, parent, false);
        return new CountriesViewHolder(countriesView);
    }

    @Override
    public void onBindViewHolder(@NonNull CountriesViewHolder holder, int position) {
        String countryName = mCountryList.get(position).getName();
        holder.mCountryNameTextView.setText(String.valueOf(countryName));
    }

    @Override
    public int getItemCount() {
        return mCountryList == null ? 0 : mCountryList.size();
    }

    public class CountriesViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.listItemCountryNameTextView)
        TextView mCountryNameTextView;

        public CountriesViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.listItemCountryView)
        public void onCountryClick() {
            Toast.makeText(mContext, mCountryList.get(getAdapterPosition()).getName() + " Clicked!", Toast.LENGTH_SHORT).show();
        }
    }
}
