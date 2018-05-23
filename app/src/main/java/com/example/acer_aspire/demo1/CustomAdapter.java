package com.example.acer_aspire.demo1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Contact> arrContact;

    public CustomAdapter(Context context, int layout, List<Contact> arrContact) {
        this.context = context;
        this.layout = layout;
        this.arrContact = arrContact;
    }

    @Override
    public int getCount() {
        return arrContact.size();
    }

    @Override
    public Object getItem(int i) {
        return arrContact.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;

        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            viewHolder = new ViewHolder();

            viewHolder.tvName = (TextView) view.findViewById(R.id.textviewName);
            viewHolder.tvNumberPhone = (TextView) view.findViewById(R.id.textviewSDT);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();

        }

        Contact contact = arrContact.get(i);

        viewHolder.tvName.setText(contact.getName());
        viewHolder.tvNumberPhone.setText(contact.getNumber());


        return view;
    }

    public class ViewHolder {
            TextView tvName, tvNumberPhone ;
    }

}
