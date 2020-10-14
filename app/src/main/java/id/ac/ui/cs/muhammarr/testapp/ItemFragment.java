package id.ac.ui.cs.muhammarr.testapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import id.ac.ui.cs.muhammarr.testapp.AnimalContent.Animal;


// Source: https://developer.android.com/training/basics/fragments/communicating.html
public class ItemFragment extends ListFragment implements OnItemClickListener {
    OnItemFragmentClickListener callback;

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;

    public ItemFragment() {
    }

    public interface OnItemFragmentClickListener {
        public void onItemFragmentClicked(Bundle bundle);
    }

    public void setOnItemFragmentClickListener(OnItemFragmentClickListener callback) {
        this.callback = callback;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        AnimalAdapter adapter = new AnimalAdapter(getActivity(), AnimalContent.ITEMS);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i("frag","clicked");
        Bundle bundle = new Bundle();
        Animal value = (Animal) getListView().getItemAtPosition(position);
        bundle.putParcelable("result", value);
        callback.onItemFragmentClicked(bundle);
    }
}