package com.sixtel.bitdate;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by branden on 8/23/16.
 *
 * stacks and moves cards
 */
public class CardStackContainer extends RelativeLayout implements View.OnTouchListener {

    private CardAdapter mAdapter;

    public CardStackContainer(Context context) {
        this(context, null, 0);
    }

    public CardStackContainer(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CardStackContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setAdapter(CardAdapter adapter) {
        mAdapter = adapter;
        if (mAdapter.getCount() > 0 ) {
            CardView cardView = mAdapter.getView(0, null, this);
            cardView.setOnTouchListener(this);
            addView(cardView);
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
        }
        return true;
    }
}
