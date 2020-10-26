package hoang.computer.labtuan345;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import hoang.computer.labtuan345.Retaurant;
import java.util.ArrayList;
import java.util.List;

public class LunchList extends Activity {
    private List<Retaurant> RestaurantList = new ArrayList<>();
    RetaurantAdater adapter = null;
    private View.OnClickListener onSave= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Retaurant r = new Retaurant();

            EditText name =(EditText)findViewById(R.id.name);
            EditText address =(EditText)findViewById(R.id.addr);

            r.setName(name.getText().toString());

            r.setAddress(address.getText().toString());

            RadioGroup type = (RadioGroup)findViewById(R.id.type);
            switch (type.getCheckedRadioButtonId())
            {
                case R.id.take_out:
                    r.setType("Take out");
                    break;
                case R.id.sit_down:
                    r.setType("Sit down");
                    break;
                case R.id.delivery:
                    r.setType("Delivery");
                    break;
            }
            RestaurantList.add(r);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lab5);
        setContentView(R.layout.lab5);
        Button save = (Button)findViewById(R.id.save);
        save.setOnClickListener(onSave);
        ListView list = (ListView)findViewById(R.id.restaurants);
        adapter = new RetaurantAdater();
        list.setAdapter(adapter);
    }

    class RetaurantAdater extends ArrayAdapter<Retaurant> {
        public RetaurantAdater(Context context, int textViewResourceId) {
            super(context, textViewResourceId);
        }

        public RetaurantAdater() {
            super(LunchList.this, android.R.layout.simple_list_item_1, RestaurantList);
        }
        @Override
        public View getView(int postion, View convertView, ViewGroup parent)
        {
            View row = convertView;
            if(row==null)
            {
                LayoutInflater inflater =getLayoutInflater();
                row = inflater.inflate(R.layout.row,null);
            }
            Retaurant r = RestaurantList.get(postion);
            ((TextView)row.findViewById(R.id.title)).setText(r.getName());
            ((TextView)row.findViewById(R.id.address)).setText(r.getAddress());
            ImageView icon = (ImageView)row.findViewById(R.id.icon);
            String type = r.getType();
            if(type.equals("Take out"))
                icon.setImageResource(R.drawable.type_t);
            if(type.equals("Sit down"))
                icon.setImageResource(R.drawable.type_s);
            if(type.equals("Delivery"))
                icon.setImageResource(R.drawable.type_d);

            return row;
        }
    }
}

