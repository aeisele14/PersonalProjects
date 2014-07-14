package edu.hastings.hastingscollege.tabfragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import edu.hastings.hastingscollege.R;

public class SingleMenuItemNutritionFactsActivity extends Activity {

    final String KEY_ITEM_NAME = "item_name";
    final String KEY_CALORIES = "calories";
    final String KEY_FAT = "fat";
    final String KEY_SAT_FAT = "satfat";
    final String KEY_SODIUM = "sodium";
    final String KEY_CARBO = "carbo";
    final String KEY_SUGARS = "sugars";
    final String KEY_PROTEIN = "protein";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.nutrition_facts_item);

        Intent in = getIntent();

        String itemName = in.getStringExtra(KEY_ITEM_NAME);
        String calories = in.getStringExtra(KEY_CALORIES);
        String fat = in.getStringExtra(KEY_FAT);
        String satFat = in.getStringExtra(KEY_SAT_FAT);
        String sodium = in.getStringExtra(KEY_SODIUM);
        String carbo = in.getStringExtra(KEY_CARBO);
        String sugars = in.getStringExtra(KEY_SUGARS);
        String protein = in.getStringExtra(KEY_PROTEIN);

        TextView txtItemName = (TextView) findViewById(R.id.menu_item_name);
        TextView txtCalories = (TextView) findViewById(R.id.calories);
        TextView txtFat = (TextView) findViewById(R.id.fat);
        TextView txtSatFat = (TextView) findViewById(R.id.sat_fat);
        TextView txtSodium = (TextView) findViewById(R.id.sodium);
        TextView txtCarbo = (TextView) findViewById(R.id.carbo);
        TextView txtSugars = (TextView) findViewById(R.id.sugars);
        TextView txtProtein = (TextView) findViewById(R.id.protein);

        txtItemName.setText(itemName);
        txtCalories.setText(calories);
        txtFat.setText(fat);
        txtSatFat.setText(satFat);
        txtSodium.setText(sodium);
        txtCarbo.setText(carbo);
        txtSugars.setText(sugars);
        txtProtein.setText(protein);

        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }
}
