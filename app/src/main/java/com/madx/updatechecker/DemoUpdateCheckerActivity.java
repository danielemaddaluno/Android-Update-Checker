package com.madx.updatechecker;

import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;

import com.madx.updatechecker.lib.UpdateRunnable;


public class DemoUpdateCheckerActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_update_checker);
        new UpdateRunnable(this, new Handler()).start();
    }

    /** Called when the user clicks the Send button */
    public void forceUpdateTest(View view) {
        if(view.getId() == R.id.button_force_update_test){
            new UpdateRunnable(this, new Handler()).force(true).start();
        }
    }
}
