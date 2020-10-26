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
import android.widget.AdapterView;
import hoang.computer.labtuan345.Retaurant;
import java.util.ArrayList;
import java.util.List;
import android.widget.TabHost;
import android.app.TabActivity;

public class LunchListLab7 extends TabActivity {

    private List<Retaurant> RestaurantList = new ArrayList<>();

    LunchListLab7.RetaurantAdater adapter = null;

    RestaurantHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lab7);
        setContentView(R.layout.lab7);
        helper = new RestaurantHelper(this);

        Button save = (Button)findViewById(R.id.save);
        save.setOnClickListener(onSave);
        ListView list = (ListView)findViewById(R.id.restaurants);
        list.setOnItemClickListener(onListClick);
        adapter = new LunchListLab7.RetaurantAdater();
        list.setAdapter(adapter);

        TabHost.TabSpec spec = getTabHost().newTabSpec("tag1");
        spec.setContent(R.id.restaurants);
        spec.setIndicator(null,getResources().getDrawable(R.drawable.list));
        getTabHost().addTab(spec);

        spec = getTabHost().newTabSpec("tag2");
        spec.setContent(R.id.details);
        spec.setIndicator(null,
                getResources().getDrawable(R.drawable.retaurant));
        getTabHost().addTab(spec);

        getTabHost().setCurrentTab(0);
    }
    private AdapterView.OnItemClickListener onListClick = new AdapterView.OnItemClickListener()
        {
        public void onItemClick(AdapterView<?> parent,View view,int postion,long id)
        {
            Retaurant r = RestaurantList.get(postion);
            EditText name;
            EditText address;
            RadioGroup types;
            name = (EditText)findViewById(R.id.name);
            address = (EditText)findViewById(R.id.addr);
            types = (RadioGroup)findViewById(R.id.type);

            name.setText(r.getName());
            address.setText(r.getAddress());
            if (r.getType().equals("Sit down"))
                types.check(R.id.sit_down);
            else if (r.getType().equals("Take out"))
                types.check(R.id.take_out);
            else
                types.check(R.id.delivery);
            getTabHost().setCurrentTab(1);
            helper.insert(r.getName(), r.getAddress(),
                    r.getType());
        }
    };
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
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        // Đóng cơ sở dữ liệu
        helper.close();
    }// end onDestroy

    class RetaurantAdater extends ArrayAdapter<Retaurant> {
        public RetaurantAdater(Context context, int textViewResourceId) {
            super(context, textViewResourceId);
        }

        public RetaurantAdater() {
            super(LunchListLab7.this, android.R.layout.simple_list_item_1, RestaurantList);
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
