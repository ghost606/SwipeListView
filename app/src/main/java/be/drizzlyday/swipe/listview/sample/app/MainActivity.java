package be.drizzlyday.swipe.listview.sample.app;

import android.app.Activity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import be.drizzlyday.swipe.listview.SwipeListView;
import be.drizzlyday.swipe.listview.sample.app.adapters.ContentAdapter;
import be.drizzlyday.swipe.listview.sample.app.data.ContentData;
import be.drizzlyday.swipe.listview.sample.app.models.ContentModel;

public class MainActivity extends Activity {

    private SwipeListView listView;
    private ContentAdapter adapter;
    private List<ContentModel> content = new ArrayList<ContentModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        content = ContentData.CreateData();

        listView = (SwipeListView) findViewById(R.id.lv_content);

        adapter = new ContentAdapter(this, R.layout.row_content, content);;
        listView.setAdapter(adapter);
    }
}
