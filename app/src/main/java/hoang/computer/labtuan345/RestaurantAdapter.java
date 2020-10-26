package hoang.computer.labtuan345;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class RestaurantAdapter extends CursorAdapter {

    RestaurantHelper helper = new RestaurantHelper();
    public RestaurantAdapter(Cursor c)
    {
        super(LunchListLab7.this, c);
    }
    public RestaurantAdapter(Context context, Cursor c) {
        super(context, c);
    }
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        View row = view;
        ((TextView)row.findViewById(R.id.title)).
                setText(helper.getName(cursor));
        ((TextView)row.findViewById(R.id.address)).
                setText(helper.getAddress(cursor));
        ImageView icon = (ImageView)row.findViewById(R.id.icon);
        String type = helper.getType(cursor);
        if (type.equals("Take out"))
            icon.setImageResource(R.drawable.type_t);
        else if (type.equals("Sit down"))
            icon.setImageResource(R.drawable.type_s);
        else
            icon.setImageResource(R.drawable.type_d);
    }// end bindView
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
// TODO Auto-generated method stub
        LayoutInflater inflater = getLayoutInflater();
        View row = inflater.inflate(R.layout.row, parent, false);
        return row;
    }
}

