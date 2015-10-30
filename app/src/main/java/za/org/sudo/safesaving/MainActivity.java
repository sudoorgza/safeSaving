package za.org.sudo.safesaving;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;

public class MainActivity extends Activity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ActionModel[] myDataset = {new ActionModel("S","Shout","Shout to attract attention", 0),
            new ActionModel("A","Approach","Approach with care", 0),
            new ActionModel("F", "Free", "Free from danger", 0),
            new ActionModel("E", "Evaluate", "Evaluate the potential patient", 0),
            new ActionModel("H", "Hazard", "Check for hazards to you and the patient", 1),
            new ActionModel("H", "Hello", "Check the patient's alertness", 1),
            new ActionModel("A", "Alert", "Patient responds to stimulus", 2),
            new ActionModel("V", "Verbal", "Patient responds to verbal interaction", 2),
            new ActionModel("P", "Pain", "Patient responds to pain", 2),
            new ActionModel("U", "Unconcious", "Patient is unconcious", 2),
            new ActionModel("H", "Help", "Ask someone to standby", 1),
            new ActionModel("A", "Air ways", "Check air ways are clear", 1),
            new ActionModel("B", "Breathing", "Is patient breathing", 1),
            new ActionModel("A", "Ambulance", "Ask person on standby to call an ambulance", 2),
            new ActionModel("B", "Not breathing", "Tell ambulance patient not breathing", 2),
            new ActionModel("C", "Come back", "Come back and let you know what is happening", 2),
            new ActionModel("C", "CPR", "Start CPR", 1)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.safe_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

//        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this,
//                DividerItemDecoration.VERTICAL_LIST);
//        mRecyclerView.addItemDecoration(itemDecoration);
//        RecyclerView.ItemDecoration itemDecoration = new VerticalSpaceItemDecoration(4);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, R.drawable.divider);
        mRecyclerView.addItemDecoration(itemDecoration);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = (RecyclerView.Adapter)(new ListAdapter(myDataset));
        mRecyclerView.setAdapter(mAdapter);
        Toast.makeText(getApplicationContext(), myDataset[0].getDescription(), Toast.LENGTH_LONG).show();


        RecyclerItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(new RecyclerItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                    ListAdapter adaptor = (ListAdapter)recyclerView.getAdapter();
                    adaptor.notifyItemClicked(getApplicationContext(), position);
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_reset) {
            ((ListAdapter)mAdapter).resetItems();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
