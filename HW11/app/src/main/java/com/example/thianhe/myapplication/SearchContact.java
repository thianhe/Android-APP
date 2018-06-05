package com.example.thianhe.myapplication;

/**
 * Created by Thian He on 5/6/2018.
 */

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class SearchContact extends Fragment {

    private ListView lstContactList;

    private HighlightAdapter<String> dataAdapter;

    // AlertDialogs
    AlertDialog actionAskingDialog;
    AlertDialog confirmDeleteDialog;
    AlertDialog dataModifyDialog;

    // 2 EditText and 1 Spinner in dataModifyDialog
    EditText edtName_Modify, edtPhoneNumber_Modify;
    Spinner spinPhoneType_Modify;

    public SearchContact() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Construct confirmDeleteDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Confirm to delete");
        builder.setPositiveButton("OK", eventDeleteEntry);
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        confirmDeleteDialog = builder.create();

        // Construct dataModifyDialog
        View view = View.inflate(getContext(), R.layout.data_modify_dialog, null);
        edtName_Modify = view.findViewById(R.id.edtName_Modify);
        edtPhoneNumber_Modify = view.findViewById(R.id.edtPhoneNumber_Modify);
        spinPhoneType_Modify = view.findViewById(R.id.spinPhoneType_Modify);
        builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Update contact");
        builder.setView(view);
        builder.setPositiveButton("OK", eventModifyData);
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dataModifyDialog = builder.create();

        // Construct actionAskingDialog
        builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Option");
        builder.setMessage("Please choose options.");
        builder.setPositiveButton("Update", eventModifyAction);
        builder.setNeutralButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                confirmDeleteDialog.show();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        actionAskingDialog = builder.create();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_search_contact, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        View view = getView();
        lstContactList = view.findViewById(R.id.lstContactList);
        lstContactList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        lstContactList.clearChoices();
        lstContactList.setOnItemLongClickListener(lsvContactList_OnLongClick);

        dataAdapter = new HighlightAdapter<>(this.getContext(), android.R.layout.simple_list_item_1);
        lstContactList.setAdapter(dataAdapter);

        // Init datas by database
        Cursor cursor = MainActivity.mContRes.query(ContactProvider.CONTENT_URI, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            // Get data iterator
            while (!cursor.isAfterLast()) {
                dataAdapter.addData(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3));
                cursor.moveToNext();
            }
            cursor.close();
        }
        dataAdapter.notifyDataSetChanged();
    }

    // When button 'Done' in dataModifyDialog is pressed
    private final DialogInterface.OnClickListener eventModifyData = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            HighlightAdapter.DataModel data = dataAdapter.getLongPressedData();
            data.name = edtName_Modify.getText().toString();
            data.phoneNumber = edtPhoneNumber_Modify.getText().toString();
            data.phoneType = spinPhoneType_Modify.getSelectedItem().toString();
            MainActivity.mContRes.update(ContactProvider.CONTENT_URI, data.getContentValues(),
                    "_id = " + String.valueOf(data.id), null);
            dataAdapter.notifyDataSetChanged();
            Toast.makeText(getContext(), "Updated！", Toast.LENGTH_LONG).show();
        }
    };

    // When button 'Modify' in actionAskingDialog is pressed
    private final DialogInterface.OnClickListener eventModifyAction = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            HighlightAdapter.DataModel data = dataAdapter.getLongPressedData();
            edtName_Modify.setText(data.name);
            edtPhoneNumber_Modify.setText(data.phoneNumber);
            String[] phoneType = getResources().getStringArray(R.array.phone_type);
            if (data.phoneType.equals(phoneType[0])) {
                spinPhoneType_Modify.setSelection(0);
            }
            else if (data.phoneType.equals(phoneType[1])) {
                spinPhoneType_Modify.setSelection(1);
            }
            else {
                spinPhoneType_Modify.setSelection(2);
            }
            dataModifyDialog.show();
        }
    };

    // When button 'Delete' in confirmDeleteDialog is pressed
    private final DialogInterface.OnClickListener eventDeleteEntry = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            HighlightAdapter.DataModel data = dataAdapter.getLongPressedData();
            MainActivity.mContRes.delete(ContactProvider.CONTENT_URI, "_id = " + data.id, null);
            dataAdapter.removeLongPressedData();
            Toast.makeText(getContext(), "Deleted！", Toast.LENGTH_LONG).show();
        }
    };

    // When the data is pressed
    private final ListView.OnItemLongClickListener lsvContactList_OnLongClick = new ListView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            actionAskingDialog.show();
            return true;
        }
    };

    // Update list
    public void updateListData() {
        Cursor cursor = MainActivity.mContRes.query(ContactProvider.CONTENT_URI, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            dataAdapter.clearAllData();
            // Get data iterator
            while (!cursor.isAfterLast()) {
                dataAdapter.addData(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3));
                cursor.moveToNext();
            }
            cursor.close();
            dataAdapter.notifyDataSetChanged();
            dataAdapter.notifyDataSetChanged();
        }
        else {
            Toast.makeText(getContext(), "Cannot update!", Toast.LENGTH_LONG).show();
        }
    }

    // Clear all highlight
    public void setListHighlight() {
        dataAdapter.setHighlightList();
    }

    // Set highlights in list by data
    public void setListHighlight(ArrayList<Integer> list) {
        dataAdapter.setHighlightList(list);
    }
}

