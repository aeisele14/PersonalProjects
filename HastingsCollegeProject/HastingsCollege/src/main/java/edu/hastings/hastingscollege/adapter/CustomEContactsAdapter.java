package edu.hastings.hastingscollege.adapter;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import edu.hastings.hastingscollege.R;

public class CustomEContactsAdapter extends BaseAdapter {

    private static final String TAG = CustomEContactsAdapter.class.getSimpleName();
    ArrayList<ContactsDataModel> listArray;

    public CustomEContactsAdapter(ArrayList<ContactsDataModel> contactsDataModelArrayList) {
        this.listArray = contactsDataModelArrayList;
    }

    @Override
    public int getCount() {
        return listArray.size();
    }

    @Override
    public Object getItem(int position) {
        return listArray.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.list_item_econtacts, parent, false);
        }

        final ContactsDataModel dataModel = listArray.get(position);

        TextView textView = (TextView) convertView.findViewById(R.id.contact_name);
        textView.setText(dataModel.getContactName());

        Button button = (Button) convertView.findViewById(R.id.btn_phone_num);
        button.setText(dataModel.getContactPhoneNumber());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:"+dataModel.getContactPhoneNumber()));
                parent.getContext().startActivity(callIntent);
            }
        });
        return convertView;
    }
}
