package com.bram3r.cakeslistapp.common

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

// Para comprobar el estado de la red
fun isOnline(context: Context): Boolean {
    val connManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { // Si API >= 29
        val nw = connManager.activeNetwork ?: return false
        val activeNw = connManager.getNetworkCapabilities(nw) ?: return false
        return when {
            activeNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            activeNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            activeNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
            else -> false
        }
    } else { // Si API < 29
        return connManager.activeNetworkInfo?.isConnected ?: false
    }
}