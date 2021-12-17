package com.example.healthycollege.utils

import android.annotation.TargetApi
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.*
import android.os.Build
import android.util.Log
import androidx.lifecycle.LiveData

class NetworkConnection(private val context: Context): LiveData<Boolean>()
{
    private var connectivityManager: ConnectivityManager = MyApplication.instance.context()
        .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    private lateinit var networkCallback: ConnectivityManager.NetworkCallback

    override fun onActive()
    {
        super.onActive()
        updateConnection()
    }

    override fun onInactive()
    {
        super.onInactive()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            try {
                connectivityManager.unregisterNetworkCallback(connectivityManagerCallback())
            }
            catch(e : Exception){
                Log.d("NetworkException",e.toString())
            }

        } else
        {
            context.unregisterReceiver(networkReceiver)
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun lollipopNetworkRequest()
    {
        val requestBuilder = NetworkRequest.Builder()
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_ETHERNET)
        connectivityManager.registerNetworkCallback(
            requestBuilder.build(),
            connectivityManagerCallback()
        )
    }

    private fun connectivityManagerCallback(): ConnectivityManager.NetworkCallback
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            networkCallback = object : ConnectivityManager.NetworkCallback()
            {
                override fun onLost(network: Network)
                {
                    super.onLost(network)
                    postValue(false)
                }

                override fun onAvailable(network: Network)
                {
                    super.onAvailable(network)
                    postValue(true)
                }
            }
            return networkCallback
        } else
        {
            throw IllegalAccessError("Error")
        }
    }

    private val networkReceiver = object : BroadcastReceiver()
    {
        override fun onReceive(context: Context?, intent: Intent?)
        {
            updateConnection()
        }
    }

    private fun updateConnection()
    {
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        postValue(activeNetwork?.isConnected == true)
    }

}