package net.ukr.vlsv.hw05.fragments

import android.app.ActionBar
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.media.AudioManager
import android.net.ConnectivityManager
import android.os.BatteryManager
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.system_info_fragment.*
import net.ukr.vlsv.hw05.R
import java.text.SimpleDateFormat
import java.util.*
//import android.support.v7.app.AppCompatActivity

class SystemInfoFragment: Fragment() {
    val systemInfoReceiver: BroadcastReceiver by lazy(this::SystemInfoReceiver)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.system_info_fragment, container, false)

        return view
    }

    override fun onStart() {
        super.onStart()

        (activity as net.ukr.vlsv.hw05.MainActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        activity!!.title = resources.getText(R.string.app_name_sys_info)

        setDateInfo()
        setChargingInfo()
        setInternetInfo()
        setHeadphonesInfo()

        val intentFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION).apply {
            addAction(ConnectivityManager.CONNECTIVITY_ACTION)
            addAction(Intent.ACTION_HEADSET_PLUG)
            addAction(Intent.ACTION_HEADSET_PLUG)
            addAction(Intent.ACTION_POWER_CONNECTED)
            addAction(Intent.ACTION_POWER_DISCONNECTED)
            addAction(Intent.ACTION_TIME_CHANGED)
            addAction(Intent.ACTION_TIMEZONE_CHANGED)
        }

        context!!.registerReceiver(systemInfoReceiver, intentFilter)

    }

    override fun onStop() {
        super.onStop()

        context?.unregisterReceiver(systemInfoReceiver)
    }

    fun setDateInfo() {
        val df = SimpleDateFormat("dd.MM.yyyy")
        val date = df.format(Calendar.getInstance().getTime())

        val tz = TimeZone.getDefault().getDisplayName(false, TimeZone.SHORT)

        DateInfo_tv.setText("Date: $date\n Time zone: $tz")
    }

    fun setChargingInfo() {
        var textConnected: String = "Charger is not connected"

        val ifilter: IntentFilter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        val batteryStatus: Intent = context!!.registerReceiver(null, ifilter)
        val status: Int = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1)
        val isCharging: Boolean = status == BatteryManager.BATTERY_STATUS_CHARGING || status == BatteryManager.BATTERY_STATUS_FULL

        if (isCharging) textConnected = "Charger is connected"

        chargingInfo_tv.setText(textConnected)
    }

    fun setInternetInfo() {
        var textConnected: String = "No internet connection"

        val runtime = Runtime.getRuntime()
        try {
            val ipProcess: Process  = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            val exitValue: Int = ipProcess.waitFor();
            if (exitValue == 0) textConnected = "Internet connected"
        } finally {}

        internetInfo_tv.setText(textConnected)
    }

    fun setHeadphonesInfo() {
        var textConnected: String = "Not is headphones or headset plugged"

        var audioManager: AudioManager =  activity!!.getSystemService(Context.AUDIO_SERVICE) as AudioManager

        if ((audioManager.isWiredHeadsetOn == true) || (audioManager.isBluetoothA2dpOn == true)) {
            textConnected = "Is headphones or headset plugged"
        }

        headphonesInfo_tv.setText(textConnected)
        }

    inner class SystemInfoReceiver: BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            when(intent.action) {
                Intent.ACTION_TIMEZONE_CHANGED -> {
                    setDateInfo()
                }
                Intent.ACTION_DATE_CHANGED -> {
                    setDateInfo()
                }
                Intent.ACTION_POWER_CONNECTED -> {
                    setChargingInfo()
                }
                Intent.ACTION_POWER_DISCONNECTED -> {
                    setChargingInfo()
                }
                ConnectivityManager.CONNECTIVITY_ACTION -> {
                        setInternetInfo()
                }
                Intent.ACTION_HEADSET_PLUG -> {
                    setHeadphonesInfo()
                }
            }
        }
    }
}