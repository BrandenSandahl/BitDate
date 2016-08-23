package com.sixtel.bitdate;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.RelativeLayout;

/**
 * Created by branden on 8/23/16.
 *
 * stacks and moves cards
 */
public class CardStackContainer extends RelativeLayout implements View.OnTouchListener {

    private CardAdapter mAdapter;
    //where we actually touched
    private float mLastTouchX;
    private float mLastTouchY;
    //where the card is positioned on the screen
    private float mPositionX;
    private float mPositionY;
    //original positions
    private float mOriginX;
    private float mOriginY;

    private GestureDetector mGestureDetector;

    public CardStackContainer(Context context) {
        this(context, null, 0);
    }

    public CardStackContainer(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CardStackContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mGestureDetector = new GestureDetector(context, new FlingListener());
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

        mGestureDetector.onTouchEvent(event);

        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mLastTouchX = event.getX();
                mLastTouchY = event.getY();

                mPositionX = v.getX();
                mPositionY = v.getY();

                mOriginX = v.getX();
                mOriginY = v.getY();

                break;
            case MotionEvent.ACTION_UP:
                reset(v);
                break;
            case MotionEvent.ACTION_MOVE:
                //get where we have moved to
                float xMove = event.getX();
                float yMove = event.getY();

                //get the change in original position and where we moved to
                float changeX = xMove - mLastTouchX;
                float changeY = yMove - mLastTouchY;

                //update original position with the difference
                mPositionX += changeX;
                mPositionY +=  changeY;

                //reset the position (IE move the card)
                v.setX(mPositionX);
                v.setY(mPositionY);

                break;
        }
        return true;
    }


    private void reset(View v) {
        mPositionX = mOriginX;
        mPositionY = mOriginY;
        v.animate()
                .setDuration(200)
                .setInterpolator(new AccelerateInterpolator())
                .x(mOriginX)
                .y(mOriginY);
    }

    private class FlingListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

            return true;
        }
    }
}
