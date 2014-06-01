package be.drizzlyday.swipe.listview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ListView;

/**
 * Created by Kevin on 25/05/2014.
 */
public class SwipeListView extends ListView {

    private int viewWidth = 1; // 1 and not 0 to prevent dividing by zero
    private float downX;
    private VelocityTracker velocityTracker;
    private View itemView;

    //Preferences
    private int minFlingVelocity;
    private int maxFlingVelocity;
    private int slop;

    public SwipeListView(Context context) {
        super(context);
        ViewConfiguration vc = ViewConfiguration.get(this.getContext());
        minFlingVelocity = vc.getScaledMinimumFlingVelocity();
        maxFlingVelocity = vc.getScaledMaximumFlingVelocity();
        slop = vc.getScaledTouchSlop();
    }

    public SwipeListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        ViewConfiguration vc = ViewConfiguration.get(this.getContext());
        minFlingVelocity = vc.getScaledMinimumFlingVelocity();
        maxFlingVelocity = vc.getScaledMaximumFlingVelocity();
    }

    public SwipeListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        ViewConfiguration vc = ViewConfiguration.get(this.getContext());
        minFlingVelocity = vc.getScaledMinimumFlingVelocity();
        maxFlingVelocity = vc.getScaledMaximumFlingVelocity();
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (viewWidth < 2) {
            viewWidth = getWidth();
        }

        switch (motionEvent.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                // TODO: ensure this is a finger, and set a flag

                // Find the child view that was touched (perform a hit test)
                Rect rect = new Rect();
                int childCount = getChildCount();
                int[] listViewCoords = new int[2];
                getLocationOnScreen(listViewCoords);
                int x = (int) motionEvent.getRawX() - listViewCoords[0];
                int y = (int) motionEvent.getRawY() - listViewCoords[1];
                View child;
                for (int i = 0; i < childCount; i++) {
                    child = getChildAt(i);
                    child.getHitRect(rect);
                    if (rect.contains(x, y)) {
                        itemView = child;
                        downX = motionEvent.getRawX();
                        velocityTracker = VelocityTracker.obtain();
                        velocityTracker.addMovement(motionEvent);

                        Log.d("Swipe", "Swipe down item: " + i);
                        break;
                    }
                }
                super.onTouchEvent(motionEvent);
                return true;
            case MotionEvent.ACTION_UP:
                if (velocityTracker == null) {
                    break;
                }

                float deltaX = motionEvent.getRawX() - downX;
                velocityTracker.addMovement(motionEvent);
                velocityTracker.computeCurrentVelocity(1000);
                float velocityX = Math.abs(velocityTracker.getXVelocity());
                float velocityY = Math.abs(velocityTracker.getYVelocity());
                boolean swapRight;

                if (minFlingVelocity <= velocityX && velocityX <= maxFlingVelocity && velocityY * 2 < velocityX) {
                    swapRight = velocityTracker.getXVelocity() > 0;
                    if (swapRight) {
                        itemView.setBackgroundColor(Color.GREEN);
                        Log.d("SwipeListView", "SwipeRight");


                    } else {
                        itemView.setBackgroundColor(Color.BLUE);
                        Log.d("SwipeListView", "SwipeLeft");
                    }
                }

                velocityTracker.recycle();
                velocityTracker = null;

                break;

            case MotionEvent.ACTION_MOVE:
                if (velocityTracker == null) {
                    break;
                }

                velocityTracker.addMovement(motionEvent);
                velocityTracker.computeCurrentVelocity(1000);
                float velocityX2 = Math.abs(velocityTracker.getXVelocity());
                float velocityY2 = Math.abs(velocityTracker.getYVelocity());

                float deltaX2 = motionEvent.getRawX() - downX;
                float deltaMode = Math.abs(deltaX2);

                if (deltaMode > slop && velocityY2 < velocityX2) {
                    Log.d("SwipeListView", "deltaX: " + deltaX2);

                    requestDisallowInterceptTouchEvent(true);
                    MotionEvent cancelEvent = MotionEvent.obtain(motionEvent);
                    cancelEvent.setAction(MotionEvent.ACTION_CANCEL |
                            (MotionEventCompat.getActionIndex(motionEvent) << MotionEventCompat.ACTION_POINTER_INDEX_SHIFT));
                    super.onTouchEvent(cancelEvent);
                }
                break;
        }
        return super.onTouchEvent(motionEvent);
    }
}
