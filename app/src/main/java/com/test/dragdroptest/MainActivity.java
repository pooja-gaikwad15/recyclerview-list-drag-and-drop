package com.test.dragdroptest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements CustomListAdapter.Listener {

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    @Bind(R.id.textCurrentDate)
    TextView textCurrentDate;

    @Bind(R.id.recyclerPendingList)
    RecyclerView recyclerPendingList;

    @Bind(R.id.textPreviousDate)
    TextView textPreviousDate;

    @Bind(R.id.textEmptyList)
    TextView textEmptyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerPendingList.setLayoutManager(new LinearLayoutManager(this));
        textCurrentDate.setText("TODAY");
        textPreviousDate.setText("YESTERDAY");
        onSuccess();
    }

    private void onSuccess() {
        try {

            CustomListResponse response = getDummyResponseObject(getDummyJsonString());

            CustomListAdapter mYesterdayCustomListAdapter =
                    new CustomListAdapter(this, response.previousDayCustomList, this);
            recyclerPendingList.setAdapter(mYesterdayCustomListAdapter);


            CustomListAdapter mCustomListAdapter =
                    new CustomListAdapter(this, response.customList, this);
            recyclerView.setAdapter(mCustomListAdapter);

            textEmptyList.setOnDragListener(mYesterdayCustomListAdapter
                    .getDragInstance());

            textEmptyList.setVisibility(View.GONE);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setEmptyList(boolean visibility) {
        textEmptyList.setVisibility(visibility ? View.VISIBLE : View.GONE);
        recyclerPendingList.setVisibility(visibility ? View.GONE : View.VISIBLE);
    }

    private String getDummyJsonString() {
        return "{\n" +
                "\"userId\":\"ABC-101\",\n" +
                "\"email\":\"stallone@gmail.com\",\n" +
                "\"customList\":[\n" +
                "{\n" +
                "\"id\":\"DR-012\",\n" +
                "\"name\":\"Dr. A\",\n" +
                "\"address\":\"Address: Sector-1, Four Bunglows, Kandivli East\",\n" +
                "\"mobile\":\"9878678987\",\n" +
                "\"lat\":19.876564,\n" +
                "\"lng\":81.637363,\n" +
                "\"estimatedTime\":\"30\"\n" +
                "},\n" +
                "{\n" +
                "\"id\":\"DR-015\",\n" +
                "\"name\":\"Dr. B\",\n" +
                "\"address\":\"Address: Sector-1, Four Bunglows, Malad East\",\n" +
                "\"mobile\":\"9878678987\",\n" +
                "\"lat\":19.876564,\n" +
                "\"lng\":81.637363,\n" +
                "\"estimatedTime\":\"50\"\n" +
                "},\n" +
                "{\n" +
                "\"id\":\"DR-016\",\n" +
                "\"name\":\"Dr. C\",\n" +
                "\"address\":\"Address: Sector-1, Four Bunglows, Goregaon East\",\n" +
                "\"mobile\":\"9878678987\",\n" +
                "\"lat\":19.876564,\n" +
                "\"lng\":81.637363,\n" +
                "\"estimatedTime\":\"120\"\n" +
                "},\n" +
                "{\n" +
                "\"id\":\"DR-017\",\n" +
                "\"name\":\"Dr. D\",\n" +
                "\"address\":\"Address: Sector-1, Four Bunglows, Juhu East\",\n" +
                "\"mobile\":\"9878678987\",\n" +
                "\"lat\":19.876564,\n" +
                "\"lng\":81.637363,\n" +
                "\"estimatedTime\":\"220\"\n" +
                "},\n" +
                "{\n" +
                "\"id\":\"DR-018\",\n" +
                "\"name\":\"Dr. E\",\n" +
                "\"address\":\"Address: Sector-1, Four Bunglows, Bandra East\",\n" +
                "\"mobile\":\"9878678987\",\n" +
                "\"lat\":19.876564,\n" +
                "\"lng\":81.637363,\n" +
                "\"estimatedTime\":\"320\"\n" +
                "}\n" +
                "],\n" +
                "\"previousDayCustomList\":[\n" +
                "{\n" +
                "\"id\":\"DR-116\",\n" +
                "\"name\":\"Dr. AB\",\n" +
                "\"address\":\"Address: Sector-1, Four Bunglows, Dadar East\",\n" +
                "\"mobile\":\"9878678987\",\n" +
                "\"lat\":19.876564,\n" +
                "\"lng\":81.637363,\n" +
                "\"estimatedTime\":\"30\"\n" +
                "},\n" +
                "{\n" +
                "\"id\":\"DR-118\",\n" +
                "\"name\":\"Dr. BD\",\n" +
                "\"address\":\"Address: Sector-1, Four Bunglows, Parle East\",\n" +
                "\"mobile\":\"9878678987\",\n" +
                "\"lat\":19.876564,\n" +
                "\"lng\":81.637363,\n" +
                "\"estimatedTime\":\"50\"\n" +
                "},\n" +
                "{\n" +
                "\"id\":\"DR-119\",\n" +
                "\"name\":\"Dr. DB\",\n" +
                "\"address\":\"Address: Sector-1, Four Bunglows, Churchgate\",\n" +
                "\"mobile\":\"9878678987\",\n" +
                "\"lat\":19.876564,\n" +
                "\"lng\":81.637363,\n" +
                "\"estimatedTime\":\"70\"\n" +
                "}\n" +
                "],\n" +
                "\"errorCode\":0,\n" +
                "\"errorMessage\":\"success\"\n" +
                "}";
    }

    private CustomListResponse getDummyResponseObject(String jsonObject) {
        CustomListResponse response = new Gson().fromJson(jsonObject, CustomListResponse.class);
        return response;
    }
}
