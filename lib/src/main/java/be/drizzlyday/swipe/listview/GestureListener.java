package be.drizzlyday.swipe.listview;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;

/**
 * Created by Kevin on 25/05/2014.
 */
public class GestureListener extends GestureDetector.SimpleOnGestureListener {

    private SwipeListView listView;
    private VelocityTracker velocityTracker;

    private static final int SWIPE_THRESHOLD = 20;
    private static final int SWIPE_VELOCITY_THRESHOLD = 20;

    public GestureListener(SwipeListView listView) {
        this.listView = listView;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        boolean result = false;

        try {
            float diffY = e2.getY() - e1.getY();
            float diffX = e2.getX() - e1.getX();
            if (Math.abs(diffX) > Math.abs(diffY)) {
                if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffX > 0) {
                        onSwipeRight();
                    } else {
                        onSwipeLeft();
                    }
                }
            } else {
                if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffY > 0) {
                        onSwipeBottom();
                    } else {
                        onSwipeTop();
                    }
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return result;
    }

    public void onSwipeRight() {
        Log.d("SwipeListView", "Swipe: right");
    }
    public void onSwipeLeft() {
        Log.d("SwipeListView", "Swipe: left");
    }
    public void onSwipeTop() {
        Log.d("SwipeListView", "Swipe: top");
    }
    public void onSwipeBottom() {
        Log.d("SwipeListView", "Swipe: bottom");
    }
}
