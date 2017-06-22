package com.example.dbm0204.assignment82;
import android.content.Context;
//Base class for activities that use the support library action bar features
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Collections;
//A comparison function, which imposes a total ordering on some collection of objects
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mAscButton;
    private Button mDescButton;
    private ListView mNameListView;

    private List<String> stringList;
    private StringAdapter stringAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //If the activity is being re-initialized after previously being shut down then this Bundle contains the data it most recently supplied
        super.onCreate(savedInstanceState);

        //Setting Title *(We can add specific title for all pages/activity that we have created)
        setTitle("Sorting List");

        //Set the activity content from a layout resource
        setContentView(R.layout.sort_list);

        //view that identified by the id attribute

        mAscButton = (Button) findViewById(R.id.asc_button);
        mDescButton = (Button) findViewById(R.id.desc_button);
        mNameListView = (ListView) findViewById(R.id.name_list);

        //ArrayList For Months
        stringList = new ArrayList<String>();
        stringList.add("April");
        stringList.add("February");
        stringList.add("January");
        stringList.add("July");
        stringList.add("June");
        stringList.add("March");
        stringList.add("December");
        stringList.add("October");
        stringList.add("November");
        stringList.add("August");
        stringList.add("September");
        stringList.add("May");

        //Setting Adapter
        stringAdapter = new StringAdapter(MainActivity.this, R.layout.sort_list_item, stringList);
        mNameListView.setAdapter(stringAdapter);

        //callback to be invoked when this view is clicked. If this view is not clickable, it becomes clickable
        mAscButton.setOnClickListener(this);
        mDescButton.setOnClickListener(this);


    }

    // Comparator for Ascending Order
    public static Comparator<String> StringAscComparator = new Comparator<String>() {

        public int compare(String app1, String app2) {

            String stringName1 = app1;
            String stringName2 = app2;

            return stringName1.compareToIgnoreCase(stringName2);
        }
    };

    //Comparator for Descending Order
    public static Comparator<String> StringDescComparator = new Comparator<String>() {

        public int compare(String app1, String app2) {

            String stringName1 = app1;
            String stringName2 = app2;

            return stringName2.compareToIgnoreCase(stringName1);
        }
    };

    //Own Custom Adapter
    private class StringAdapter extends ArrayAdapter<String> {
        // Attributes
        private List<String> strModel;

        public StringAdapter(Context context, int textViewResourceId,
                             List<String> strModel) {
            super(context, textViewResourceId, strModel);
            this.strModel = strModel;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            Holder holder = null;

            if (view == null) {
                view = View.inflate(MainActivity.this,
                        R.layout.sort_list_item, null);

                holder = new Holder();
                holder.StringNameTextView = (TextView) view
                        .findViewById(R.id.name_text_view);

                view.setTag(holder);
            } else {
                holder = (Holder) view.getTag();
            }
            String nameText = strModel.get(position);
            holder.StringNameTextView.setText(nameText);
            return view;
        }
    }

    static class Holder {
        private TextView StringNameTextView;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.asc_button:
                Collections.sort(stringList, StringAscComparator);

                // A Toast Notification for user when list arranged in Ascending Order
                Toast.makeText(MainActivity.this, "Sorting in Ascending Order", Toast.LENGTH_LONG).show();
                break;
            case R.id.desc_button:
                Collections.sort(stringList, StringDescComparator);

                // A Toast Notification for user when list arranged in Descending Order
                Toast.makeText(MainActivity.this, "Sorting in Descending Order", Toast.LENGTH_LONG).show();
                break;
        }

        //Notifies the attached observers that the underlying data has been changed and any View reflecting the data set should refresh itself.
        stringAdapter.notifyDataSetChanged();

    }
}