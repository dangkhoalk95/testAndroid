package khoadd2.learning.lession1.learningandroid;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.telephony.CarrierConfigManager;
import android.util.Log;

import com.android.internal.telephony.IccCardConstants;
import com.android.internal.telephony.TelephonyIntents;

public class PhoneGlobals extends ContextWrapper {
    public static final String LOG_TAG = "PhoneGlobals";

    private static final int EVENT_SIM_NETWORK_LOCKED = 3;
    private static final int EVENT_SIM_STATE_CHANGED = 8;
    private static final int EVENT_DATA_ROAMING_DISCONNECTED = 10;
    private static final int EVENT_DATA_ROAMING_OK = 11;
    private static final int EVENT_UNSOL_CDMA_INFO_RECORD = 12;
    private static final int EVENT_RESTART_SIP = 13;
    private static final int EVENT_DATA_ROAMING_SETTINGS_CHANGED = 14;
    private static final int EVENT_MOBILE_DATA_SETTINGS_CHANGED = 15;

    private static PhoneGlobals sMe;

    private final BroadcastReceiver mReceiver = new PhoneAppBroadcastReceiver();


    public PhoneGlobals(Context context) {
        super(context);
        sMe = this;
    }

    public void onCreate() {
        Log.i(LOG_TAG, "[khoadd2] : onCreate()... Phone Globals");
        ContentResolver resolver = getContentResolver();
        // Register for misc other intent broadcasts.
        IntentFilter intentFilter =
                new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        intentFilter.addAction(TelephonyIntents.ACTION_SIM_STATE_CHANGED);
        intentFilter.addAction(TelephonyIntents.ACTION_RADIO_TECHNOLOGY_CHANGED);
        intentFilter.addAction(TelephonyIntents.ACTION_SERVICE_STATE_CHANGED);
        intentFilter.addAction(TelephonyIntents.ACTION_EMERGENCY_CALLBACK_MODE_CHANGED);
        intentFilter.addAction(TelephonyIntents.ACTION_DEFAULT_DATA_SUBSCRIPTION_CHANGED);
        intentFilter.addAction(CarrierConfigManager.ACTION_CARRIER_CONFIG_CHANGED);
        registerReceiver(mReceiver, intentFilter);
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Log.i(LOG_TAG, "[khoadd2] : event = " + msg.what);
            Log.i(LOG_TAG, "[khoadd2] : object = " + msg.obj);
            switch (msg.what) {
                // TODO: This event should be handled by the lock screen, just
                // like the "SIM missing" and "Sim locked" cases (bug 1804111).
                case EVENT_SIM_NETWORK_LOCKED:
                    break;

                case EVENT_DATA_ROAMING_DISCONNECTED:
                    break;

                case EVENT_DATA_ROAMING_OK:
                    break;

                case EVENT_SIM_STATE_CHANGED:
                    break;

                case EVENT_UNSOL_CDMA_INFO_RECORD:
                    break;
                case EVENT_RESTART_SIP:
                    break;
                case EVENT_DATA_ROAMING_SETTINGS_CHANGED:
                    break;
                case EVENT_MOBILE_DATA_SETTINGS_CHANGED:
                    break;
            }
        }
    };

    public static PhoneGlobals getInstance() {
        if (sMe == null) {
            throw new IllegalStateException("No PhoneGlobals here!");
        }
        return sMe;
    }

    static PhoneGlobals getInstanceIfPrimary() {
        return sMe;
    }

    private class PhoneAppBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.i(LOG_TAG,"[khoadd2] : Action " + action);
            if (action.equals(Intent.ACTION_AIRPLANE_MODE_CHANGED)) {

            } else if (action.equals(TelephonyIntents.ACTION_SIM_STATE_CHANGED)) {
                mHandler.sendMessage(mHandler.obtainMessage(EVENT_SIM_STATE_CHANGED,
                        intent.getStringExtra(IccCardConstants.INTENT_KEY_ICC_STATE)));
            } else if (action.equals(TelephonyIntents.ACTION_RADIO_TECHNOLOGY_CHANGED)) {

            } else if (action.equals(TelephonyIntents.ACTION_SERVICE_STATE_CHANGED)) {

            } else if (action.equals(TelephonyIntents.ACTION_EMERGENCY_CALLBACK_MODE_CHANGED)) {

            } else if (action.equals(CarrierConfigManager.ACTION_CARRIER_CONFIG_CHANGED)) {

            } else if (action.equals(TelephonyIntents.ACTION_DEFAULT_DATA_SUBSCRIPTION_CHANGED)) {
            }
        }
    }
}

