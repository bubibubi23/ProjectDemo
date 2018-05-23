package com.example.acer_aspire.demo1;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DatabaseReference mData;
    private Button btnOK, btnXoa;
    private EditText edtName;
    private EditText edtNumber;
    private ListView lvContact;
    private ArrayList<Contact> arrayContact;
    private CustomAdapter adapter;
    private String key;
    Contact contact;
    private int position;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Controls();


        mData = FirebaseDatabase.getInstance().getReference();

        adapter = new CustomAdapter(MainActivity.this, R.layout.row_layout, arrayContact);
        lvContact.setAdapter(adapter);

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                contact = new Contact(edtName.getText().toString(), edtNumber.getText().toString());
                mData.push().setValue(contact);


            }
        });

        lvContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                edtName.setText(arrayContact.get(i).getName());
                edtNumber.setText(arrayContact.get(i).getNumber());
                key = arrayContact.get(i).getKey();



            }
        });

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mData.child(key).removeValue();



            }
        });


        mData.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                String a = dataSnapshot.getValue().toString();
                Contact ct = dataSnapshot.getValue(Contact.class);
                ct.setKey(dataSnapshot.getKey());


//                try {
//                    JSONObject jsonObject = new JSONObject(a);
//
//                    arrayContact.add(ct);
//                    arrayContact.add(new Contact(
//                            jsonObject.getString("name"),
//                            jsonObject.getString("number")
//
//                    ));
//
//                    adapter = new CustomAdapter(MainActivity.this, R.layout.row_layout, arrayContact);
//                    lvContact.setAdapter(adapter);
//                    adapter.notifyDataSetChanged();
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
                arrayContact.add(ct);
                adapter.notifyDataSetChanged();


            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

                for(int i = 0; i < arrayContact.size(); i++){
                    if(arrayContact.get(i).getKey().equals(key)){
                        arrayContact.remove(i);
                    }
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    public void Controls(){
        btnOK = (Button) findViewById(R.id.buttonOK);
        btnXoa = (Button) findViewById(R.id.buttonXoa);
        edtName = (EditText) findViewById(R.id.edittextName);
        edtNumber = (EditText) findViewById(R.id.edittextNumber);
        lvContact = (ListView) findViewById(R.id.listviewContact);
        arrayContact = new ArrayList<>();
    }
}
