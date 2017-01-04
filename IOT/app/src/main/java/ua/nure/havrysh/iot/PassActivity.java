package ua.nure.havrysh.iot;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PassActivity extends ListActivity {

    private static final String EXTRA_ACTIVITY = "activity", EXTRA_ID = "login";

    public static Intent prepareIntent(Context context, int activity, long childId) {
        Intent intent = new Intent(context, PassActivity.class);
        intent.putExtra(EXTRA_ACTIVITY, activity);
        intent.putExtra(EXTRA_ID, childId);
        return intent;
    }

    private static String[] gates = {"Main gates", "Dinner room", "Game room"};

    private long login;
    private int activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        login = getIntent().getLongExtra(EXTRA_ID, 0);
        activity = getIntent().getIntExtra(EXTRA_ACTIVITY, 0);

        setListAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, gates));

    }

    void sendData(final String gate) {
        Rest.easyGo.postSensorData(login, gate, activity).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if(response.isSuccessful()) {
                    Toast.makeText(PassActivity.this, String.format("%s passed by %s.\nAll activity info was synchronized.", gate, login), Toast.LENGTH_LONG).show();
                    setResult(RESULT_OK);
                }
                else{
                    Toast.makeText(PassActivity.this, response.message(), Toast.LENGTH_LONG).show();
                }
                finish();
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Toast.makeText(PassActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        String gate = gates[position];

        sendData(gate);

    }
}
