package be.drizzlyday.swipe.listview.app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import be.drizzlyday.samsungswipelistview.app.R;
import be.drizzlyday.swipe.listview.app.adapters.ContentAdapter;
import be.drizzlyday.swipe.listview.app.data.ContentData;
import be.drizzlyday.swipe.listview.app.models.ContentModel;

public class MainActivity extends Activity {

    private ListView listView;
    private ContentAdapter adapter;
    private List<ContentModel> content = new ArrayList<ContentModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        content = ContentData.CreateData();

        listView = (ListView) findViewById(R.id.lv_content);

        adapter = new ContentAdapter(this, R.layout.row_content, content);;
        listView.setAdapter(adapter);
    }
}
