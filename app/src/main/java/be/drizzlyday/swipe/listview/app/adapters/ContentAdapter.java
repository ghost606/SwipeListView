package be.drizzlyday.swipe.listview.app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import be.drizzlyday.samsungswipelistview.app.R;
import be.drizzlyday.swipe.listview.app.models.ContentModel;

/**
 * Created by Kevin on 18/03/14.
 */
public class ContentAdapter extends ArrayAdapter<ContentModel> {

    private final Context context;
    private final int layout;
    private List<ContentModel> data;

    // Constructor
    public ContentAdapter(Context context, int layout, List<ContentModel> data) {
        super(context, layout, data);

        this.context = context;
        this.layout = layout;
        this.data = data;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(layout, null);
            viewHolder = new ViewHolder();
            //TEXTVIEWS
            viewHolder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.tvContent = (TextView) convertView.findViewById(R.id.tv_content);
            //IMAGEVIEWS
            viewHolder.ivIcon = (ImageView) convertView.findViewById(R.id.iv_icon);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final ContentModel model = data.get(position);

        viewHolder.tvName.setText(model.getName());
        viewHolder.tvContent.setText(model.getTempContent());
        viewHolder.ivIcon.setImageResource(R.drawable.ic_action_email);

        return convertView;
    }

    // View Holder
    private static class ViewHolder {
        public TextView tvName, tvContent;
        public ImageView ivIcon;
    }
}
