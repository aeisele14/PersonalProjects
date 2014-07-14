package edu.hastings.hastingscollege.navdrawerfragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import edu.hastings.hastingscollege.R;
import edu.hastings.hastingscollege.adapter.ContactsDataModel;
import edu.hastings.hastingscollege.adapter.CustomEContactsAdapter;

public class FragmentEmergencyContacts extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.emergency_contacts, container, false);
        ListView contactsList = (ListView) view.findViewById(R.id.contacts_list);

        ArrayList<ContactsDataModel> contactsDataModelArrayList = new ArrayList<ContactsDataModel>();
        String[] contactNames = getResources().getStringArray(R.array.emergency_contact_names);
        String[] contactPhoneNums = getResources().getStringArray(R.array.emergency_contact_numbers);
        for (int i = 0; i < contactNames.length; i++) {
            contactsDataModelArrayList.add(new ContactsDataModel(contactNames[i], contactPhoneNums[i]));
        }
        CustomEContactsAdapter customEContactsAdapter = new CustomEContactsAdapter(contactsDataModelArrayList);
        contactsList.setAdapter(customEContactsAdapter);

        TextView txtHeaderText = (TextView) view.findViewById(R.id.list_item_menu_header_textview);
        String headerText = getResources().getString(R.string.emergency_contacts_header);
        txtHeaderText.setText(headerText);
        return view;
    }
}
