package com.androidvigo.cloud;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;


public class MainActivity extends ActionBarActivity
    implements GetMemesCallback {


    private ProgressBar mLoadingProgressBar;
    private Toolbar toolbar;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);


        mLoadingProgressBar = (ProgressBar) findViewById(R.id.activity_main_loading_indicator);



        //Instalando el recyclerview
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        // RecyclerView ItemDecoration (divider)
        final RecyclerView.ItemDecoration itemDecoration = new Divider(this);
        recyclerView.addItemDecoration(itemDecoration);


        View fabView = findViewById(R.id.fab_add);
        fabView.setVisibility(View.INVISIBLE);

        fabView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast toast =
                        Toast.makeText(getApplicationContext(),
                                "Sin acciones definidas", Toast.LENGTH_SHORT);

                toast.show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
      /*  if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }





    public void startGetMemesRequest(View requestButton) {

        requestButton.setVisibility(View.GONE);
        mLoadingProgressBar.setVisibility(View.VISIBLE);

        GetMemesHelper.getInstance().loadMemesWithIon(this, this);
    }

    @Override
    public void onMemesResult(List<MemeEntity> memesList) {

        mLoadingProgressBar.setVisibility(View.GONE);

        String[] memesNames = new String[memesList.size()];

        for (int i = 0; i < memesList.size(); i++)
            memesNames[i] = memesList.get(i).getTitle();

        // MemeAdapter memesAdapter = new MemeAdapter(this, memesList);

        // mMemesListView.setAdapter(memesAdapter);
        View fabView = findViewById(R.id.fab_add);
        fabView.setVisibility(View.VISIBLE);
        final Adapter adapter = new Adapter(memesList, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onMemesError() {

    }
}
