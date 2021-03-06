package com.sixtel.bitdate;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements UserDAO.UserDataCallbacks, CardStackContainer.SwipeCallbacks {

    private CardStackContainer mCardStack;
    private List<User> mUsers;
    private CardAdapter mCardAdapter;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        mCardStack = (CardStackContainer) v.findViewById(R.id.card_stack);

        //List of user from Parse
        UserDAO.getAllUsers(this);
        mUsers = new ArrayList<>();

        //wire up adapter
        mCardAdapter = new CardAdapter(getActivity(), mUsers);
        mCardStack.setAdapter(mCardAdapter);
        mCardStack.setSwipeCallbacks(this);


        ImageButton nahButton = (ImageButton) v.findViewById(R.id.nah_button);
        nahButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCardStack.swipeLeft();
            }
        });

        ImageButton yeahButton = (ImageButton) v.findViewById(R.id.yeah_button);
        yeahButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCardStack.swipeRight();
            }
        });

        return v;
    }

    @Override
    public void onUsersFetched(List<User> users) {
        mUsers.addAll(users);
        mCardAdapter.notifyDataSetChanged();
    }

    @Override
    public void onSwipedRight(User user) {
        ActionDataSource.saveUserLiked(user.getId());
    }

    @Override
    public void onSwipedLeft(User user) {
        ActionDataSource.saveUserSkipped(user.getId());

    }
}
