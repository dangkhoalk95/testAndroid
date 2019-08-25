package khoadd2.learning.lession1.learningandroid;

import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private final String LOG_TAG = "MainActivity";

    Button mButton;
    PhoneGlobals mPhoneGlobals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(LOG_TAG,"[khoadd2] : onCreate MainActivity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton = (Button) findViewById(R.id.bt_test);

        mPhoneGlobals = new PhoneGlobals(this);
        mPhoneGlobals.onCreate();

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setComponent(new ComponentName("net.vsmart.vlocksim","net.vsmart.vlocksim.simlock.LockDeviceActivity"));
//                intent.setComponent(new ComponentName("khoadd2.learning.lession1.testapplication","khoadd2.learning.lession1.testapplication.MainActivity"));


                startActivity(intent);
            }
        });
    }
}
