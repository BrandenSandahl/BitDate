package com.sixtel.bitdate;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        CardStackContainer cardStack = (CardStackContainer) v.findViewById(R.id.card_stack);

        //fake data
        User user = new User();
        user.setFirstName("Ol Grey");
        List<User> users = new ArrayList<>();
        users.add(user);

        //wire up adapter
        CardAdapter cardAdapter = new CardAdapter(getActivity(), users);
        cardStack.setAdapter(cardAdapter);

        return v;
    }
}
