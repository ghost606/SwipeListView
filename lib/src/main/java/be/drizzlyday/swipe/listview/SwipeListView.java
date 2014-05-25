package be.drizzlyday.swipe.listview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * Created by Kevin on 25/05/2014.
 */
public class SwipeListView extends ListView {

    GestureDetector gestureDetector;

    public SwipeListView(Context context) {
        super(context);

        gestureDetector = new GestureDetector(context, new GestureListener());
    }

    public SwipeListView(Context context, AttributeSet attrs) {
        super(context, attrs);

        gestureDetector = new GestureDetector(context, new GestureListener());
    }

    public SwipeListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        gestureDetector = new GestureDetector(context, new GestureListener());
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        gestureDetector.onTouchEvent(ev);
        return super.onTouchEvent(ev);
    }
}
