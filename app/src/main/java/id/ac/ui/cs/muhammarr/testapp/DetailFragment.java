package id.ac.ui.cs.muhammarr.testapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import id.ac.ui.cs.muhammarr.testapp.AnimalContent.Animal;

/**
 * A fragment representing a list of Items.
 */
public class DetailFragment extends Fragment {

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public DetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle b = getArguments();
        Animal animal = b.getParcelable("result");

        View view = inflater.inflate(R.layout.item_detail, container, false);
        ImageView picture = view.findViewById(R.id.animal_picture);
        TextView name = view.findViewById(R.id.animal_name);
        TextView detail = view.findViewById(R.id.animal_detail);

        picture.setImageResource(animal.picture);
        name.setText(animal.name);
        detail.setText(animal.detail);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

}