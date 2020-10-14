package id.ac.ui.cs.muhammarr.testapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

// Source: https://guides.codepath.com/android/Using-an-ArrayAdapter-with-ListView
public class AnimalAdapter extends ArrayAdapter<AnimalContent.Animal> {

    public AnimalAdapter(Context context, ArrayList<AnimalContent.Animal> animals) {
        super(context, 0, animals);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        AnimalContent.Animal animal = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_item, parent, false);
        }
        // Lookup view for data population
        ImageView picture = convertView.findViewById(R.id.animal_picture);
        TextView name = convertView.findViewById(R.id.content);
        // Populate the data into the template view using the data object
        picture.setImageResource(animal.picture);
        name.setText(animal.name);
        // Return the completed view to render on screen
        return convertView;
    }
}
