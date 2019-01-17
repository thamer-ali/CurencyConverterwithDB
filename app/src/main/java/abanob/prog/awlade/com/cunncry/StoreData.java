package abanob.prog.awlade.com.cunncry;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class StoreData extends AppCompatActivity {
    RecyclerView hotelsRecyclerView;
    private RecyclerView.Adapter hotelAdp;
    private RecyclerView.LayoutManager hotelLayoutManager;
    private ArrayList<Currancy> currancies;
    private static final String TAG =  "currancyArrayList";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_data);
        currancies=new ArrayList<>();
        hotelsRecyclerView = (RecyclerView) findViewById(R.id.recycler_countryDialog);
        hotelLayoutManager = new LinearLayoutManager(this);
        hotelsRecyclerView.setLayoutManager(hotelLayoutManager);
        hotelAdp = new StoreViewHoldere(lodeLastSearch(), StoreData.this);
        hotelsRecyclerView.setAdapter(hotelAdp);

    }
    private List<Currancy> lodeLastSearch() {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Gson gson = new Gson();

        String json = sharedPrefs.getString(TAG, "");
        Type type = new TypeToken<List<Currancy>>() {}.getType();
        List<Currancy> arrayList = gson.fromJson(json, type);
        if(arrayList==null)
        {
            return new ArrayList<Currancy>();
        }
        else {

            return arrayList;
        }
    }
}
