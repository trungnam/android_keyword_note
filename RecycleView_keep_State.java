package com.hydraz.trungnam1992.mvpdaggerretrofit;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class Main2Activity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    MyAdapter mAdapter;
    String[] mDataSet= new String[20];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        final View button = toolbar.findViewById(R.id.showevents);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(Main2Activity.this, button);
                //Inflating the Popup using xml file
                popup.getMenuInflater()
                        .inflate(R.menu.pop_menu, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        return false;
                    }
                });

                popup.show();

            }
        });



        for (int i = 0; i<=19; i++){
            mDataSet[i]= "EditText n: "+i;
            Log.d("Test " ,"EditText n: "+i );
        }


        mRecyclerView = (RecyclerView) findViewById(R.id.recycleview);
        mAdapter = new MyAdapter(mDataSet);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);


    }


    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        private String[] mdataset;



        public MyAdapter(String[] myDataset) {
            mdataset = myDataset;
        }

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {

            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_edit_layout, parent, false);

            ViewHolder vh = new ViewHolder(v, new MyCustomEditTextListener());
            return vh;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder,  final int position) {
            holder.myCustomEditTextListener.updatePosition(holder.getAdapterPosition());
            holder.mEditText.setText(mdataset[holder.getAdapterPosition()]);


        }

        @Override
        public int getItemCount() {
            return mdataset.length;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            public EditText mEditText;
            public MyCustomEditTextListener myCustomEditTextListener;

            public ViewHolder(View v, MyCustomEditTextListener myCustomEditTextListener) {
                super(v);

                this.mEditText = (EditText) v.findViewById(R.id.list_item_edittext);
                this.myCustomEditTextListener = myCustomEditTextListener;
                this.mEditText.addTextChangedListener(myCustomEditTextListener);
            }
        }


        private class MyCustomEditTextListener implements TextWatcher {
            private int position;

            public void updatePosition(int position) {
                this.position = position;
            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                // no op
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                mdataset[position] = charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }

        }

    }

    }
