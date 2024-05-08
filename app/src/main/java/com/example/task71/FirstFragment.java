package com.example.task71;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;
public class FirstFragment extends Fragment {

    private int position;
    private Item item;

    DatabaseHelper databaseHelper;

    public FirstFragment() {

        this.item = item;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //when fragment entered, get the item clicked data and the position in the recyclerview
            item = (Item) getArguments().getSerializable("data");
            position = getArguments().getInt("position");
        }
    }

    public static FirstFragment newInstance(Item item) {
        FirstFragment fragment = new FirstFragment();
        Bundle args = new Bundle();
        args.putSerializable("data", (Serializable) item);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_item, null);

        if(getArguments() != null) {

            TextView textViewLocation = view.findViewById(R.id.textViewLocation);
            TextView textViewPhone = view.findViewById(R.id.textViewPhone);
            TextView textViewTitle = view.findViewById(R.id.textViewTitle);
            TextView textViewDate = view.findViewById(R.id.textViewDate);
            Button removeButton = view.findViewById(R.id.removeButton);

            textViewTitle.setText(item.getType() + " " + item.getDescription());
            textViewDate.setText("Found on: " + item.getDate());
            textViewLocation.setText("Found at Location: " + item.getLocation());
            textViewPhone.setText("Contact For Further Information: " + item.getPhone());

            removeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //delete the item from the database
                    //pass the postition reference back to the activity to update the recyclerview that an item has been removed
                    databaseHelper = new DatabaseHelper(getContext());
                    databaseHelper.deleteItem(item.getId());
                    ((ListAllActivity) getActivity()).dataChanged(position);
                    getParentFragmentManager().popBackStack();
                }
            });


        }

        return view;
    }
}