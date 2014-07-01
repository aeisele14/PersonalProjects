package edu.hastings.hastingscollege;

import android.util.Log;
import android.util.Xml;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SodexoXmlParser {

    private static final String ns = null;
    final String KEY_ITEM_DATE = "menudate";
    final String KEY_DAY = "dayname";
    final String KEY_MEAL = "meal";
    final String KEY_ITEM_NAME = "item_name";
    final String KEY_ITEM_DESC = "item_desc";

    public List<HashMap<String, String>> parse(InputStream in) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            return readFeed(parser);
        } finally {
            in.close();
        }
    }

    private List<HashMap<String, String>> readFeed(XmlPullParser parser) throws XmlPullParserException, IOException {
        List<HashMap<String, String>> menuItems = new ArrayList<HashMap<String, String>>();
        int event;
        try {
            event = parser.getEventType();
            while (event != XmlPullParser.END_DOCUMENT) {
                String name = parser.getName();
                switch (event) {
                    case XmlPullParser.START_TAG:
                        break;
                    case XmlPullParser.END_TAG:
                        if (name.equals("weeklymenu")) {
                            menuItems.add(readMenuItem(parser));
                        }
                        break;
                }
                event = parser.next();
            }
        } catch (Exception e) {
            Log.d("Exception", e.toString());
        }

        return menuItems;
    }

    private HashMap<String, String> readMenuItem(XmlPullParser parser) throws XmlPullParserException, IOException {
        String menuDate = "";
        String dayName = "";
        String meal = "";
        String itemName = "";
        String itemDescription = "";
        int attributeCount = parser.getAttributeCount();
        if (attributeCount != -1) {
            for (int i = 0; i < attributeCount; i++) {
                String attributeName = parser.getAttributeName(i);
                if (attributeName.equals("menudate"))
                    menuDate = parser.getAttributeValue(i);
                else if (attributeName.equals("dayname"))
                    dayName = parser.getAttributeValue(i);
                else if (attributeName.equals("meal"))
                    meal = parser.getAttributeValue(i);
                else if (attributeName.equals("item_name"))
                    itemName = parser.getAttributeValue(i);
                else if (attributeName.equals("item_desc"))
                    itemDescription = parser.getAttributeValue(i);
            }
        }

        HashMap<String, String> menuItem = new HashMap<String, String>();
        menuItem.put(KEY_ITEM_DATE, menuDate);
        menuItem.put(KEY_DAY, dayName);
        menuItem.put(KEY_MEAL, meal);
        menuItem.put(KEY_ITEM_NAME, itemName);
        menuItem.put(KEY_ITEM_DESC, itemDescription);
        return menuItem;
    }
}
