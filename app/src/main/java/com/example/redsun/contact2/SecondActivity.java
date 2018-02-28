package com.example.redsun.contact2;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SecondActivity extends Activity {

    String status="";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<List_View> cycler;
    private  int counter=0;

    private List<String> li=new ArrayList<>();


    private Map<String,String> contacts=new HashMap<String, String>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.chatpage);



        //recycler view
        recyclerView=(RecyclerView)findViewById(R.id.recyclecontact);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cycler=new ArrayList<>();

        //contact
        ContentResolver resolver=getContentResolver();
        Cursor cursor=resolver.query(ContactsContract.Contacts.CONTENT_URI,null,null,null,null);


        String check="";

        while(cursor.moveToNext())
        {
            String id=cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            String name=cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            Cursor ph=resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID+"= ?",new String[]{id},null);
            while(ph.moveToNext())
            {
                String phonenumber=ph.getString(ph.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                String dis=ph.getString(ph.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                if(phonenumber.substring(0,1).equals("9")||phonenumber.substring(0,1).equals("8")||phonenumber.substring(0,1).equals("7")||phonenumber.substring(0,1).equals("+"))
                {
                   phonenumber=filter(phonenumber);
                    if(!check.equals(phonenumber)) {
                        check=phonenumber;
                        if(!phonenumber.substring(0,3).equals("+91"))
                             phonenumber="+91"+phonenumber;
                             contacts.put(phonenumber,dis);
                             counter++;
                    }
                }
            }
        }
        for(Map.Entry m:contacts.entrySet())
        {


            List_View list=new List_View(m.getValue().toString(),m.getKey().toString(),status);
            cycler.add(list);

        }

        adapter=new MyAdapter(cycler,this);
        recyclerView.setAdapter(adapter);

            //cycler.add(list);





    }





    public String filter(String filterstr)
    {
        String phone = "";
        String[] output = filterstr.split("\\s");
         for(int i=0;i<output.length;i++)
         {
             phone+=output[i];
         }
        return phone;


    }




}
