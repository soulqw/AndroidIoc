package com.test.ioc;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.test.ioc.ioc.ContentView;
import com.test.ioc.ioc.InjectView;
import com.test.ioc.ioc.OnClick;
import com.test.ioc.ioc.SoulBinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {
    @InjectView(R.id.fab)
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SoulBinder.inject(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @OnClick({R.id.fab, R.id.button})
    public void onFabClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                Toast.makeText(MainActivity.this, "onFabClick", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button:
                Toast.makeText(MainActivity.this, "onButton", Toast.LENGTH_SHORT).show();
                break;
        }
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
