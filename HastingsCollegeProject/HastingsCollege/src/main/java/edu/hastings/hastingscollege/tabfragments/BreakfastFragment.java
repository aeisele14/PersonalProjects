package edu.hastings.hastingscollege.tabfragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.hastings.hastingscollege.Data;
import edu.hastings.hastingscollege.R;

public class BreakfastFragment extends Fragment {

    final String KEY_ITEM_DATE = "menudate";
    final String KEY_DAY = "dayname";
    final String KEY_MEAL = "meal";
    final String KEY_ITEM_NAME = "item_name";
    final String KEY_ITEM_DESC = "item_desc";
    final String KEY_CALORIES = "calories";
    final String KEY_FAT = "fat";
    final String KEY_SAT_FAT = "satfat";
    final String KEY_SODIUM = "sodium";
    final String KEY_CARBO = "carbo";
    final String KEY_SUGARS = "sugars";
    final String KEY_PROTEIN = "protein";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_breakfast, container, false);
        ListView mListView = (ListView) rootView.findViewById(R.id.breakfastList);
        TextView txtHeaderText = (TextView) rootView.findViewById(R.id.list_item_menu_header_textview);

        final List<HashMap<String, String>> breakfastMenuItems = getBreakfastItems(Data.globalMenuItems);
        String[] from = { KEY_ITEM_NAME, KEY_ITEM_DESC };
        int[] to = {R.id.item_name, R.id.item_desc};
        HashMap<String, String> breakfastItem = breakfastMenuItems.get(0);
        String headerDate = breakfastItem.get(KEY_ITEM_DATE);

//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date date;
//        try {
//            date = dateFormat.parse(headerDate);
//            headerDate = dateFormat.format(date);
//        } catch (ParseException e) {
//            Log.e("Parse Excepetion", e.toString());
//        }

        String headerText = breakfastItem.get(KEY_DAY) + " " + headerDate + "%n" +
            getResources().getString(R.string.sodexo_breakfast_times);
        headerText = String.format(headerText);

        txtHeaderText.setText(headerText);

        SimpleAdapter adapter = new SimpleAdapter(getActivity().getBaseContext(),
                breakfastMenuItems,
                R.layout.list_item_sodexo,
                from,
                to);

        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemName = breakfastMenuItems.get(position).get(KEY_ITEM_NAME);
                String calories = breakfastMenuItems.get(position).get(KEY_CALORIES);
                String fat = breakfastMenuItems.get(position).get(KEY_FAT);
                String satFat = breakfastMenuItems.get(position).get(KEY_SAT_FAT);
                String sodium = breakfastMenuItems.get(position).get(KEY_SODIUM);
                String carbo = breakfastMenuItems.get(position).get(KEY_CARBO);
                String sugars = breakfastMenuItems.get(position).get(KEY_SUGARS);
                String protein = breakfastMenuItems.get(position).get(KEY_PROTEIN);

                Intent in = new Intent(getActivity(), SingleMenuItemNutritionFactsActivity.class);
                in.putExtra(KEY_ITEM_NAME, itemName);
                in.putExtra(KEY_CALORIES, calories);
                in.putExtra(KEY_FAT, fat);
                in.putExtra(KEY_SAT_FAT, satFat);
                in.putExtra(KEY_SODIUM, sodium);
                in.putExtra(KEY_CARBO, carbo);
                in.putExtra(KEY_SUGARS, sugars);
                in.putExtra(KEY_PROTEIN, protein);
                startActivity(in);
            }
        });
        return rootView;
    }

    private List<HashMap<String, String>> getBreakfastItems(List<HashMap<String, String>> menuItems) {
        List<HashMap<String, String>> breakfastItems = new ArrayList<HashMap<String, String>>();

        for (HashMap<String, String> menuItem : menuItems) {
            if (menuItem.get(KEY_MEAL).equals("Breakfast")) {
                breakfastItems.add(menuItem);
            }
        }
        return breakfastItems;
    }
}
