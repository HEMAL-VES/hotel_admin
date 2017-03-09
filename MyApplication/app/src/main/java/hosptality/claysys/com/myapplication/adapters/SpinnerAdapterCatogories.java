package hosptality.claysys.com.myapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;

import java.util.ArrayList;

import hosptality.claysys.com.myapplication.models.CategoriesModel;

/**
 * Created by muhammed on 3/9/2017.
 */

public class SpinnerAdapterCatogories extends BaseAdapter {
    private ArrayList<CategoriesModel> list;
    private Context context;

    public SpinnerAdapterCatogories(Context context, ArrayList<CategoriesModel> value) {
        this.context = context;
        list = value;
    }

    public Context getContext() {
        return context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return list.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
        CheckedTextView checkedTextView = (CheckedTextView) view.findViewById(android.R.id.text1);
        checkedTextView.setText(list.get(position).getName());
        return view;
    }
}
