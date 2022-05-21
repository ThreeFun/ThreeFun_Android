package com.example.retrofit.util

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.example.retrofit.BuildConfig


const val Token = "Token"
const val Idx = "Idx"

class TokenManager(context: Context) {
    private val prefs: SharedPreferences by lazy {
        val masterKey =
            MasterKey.Builder(context, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
                .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                .build()

        EncryptedSharedPreferences.create(
            context,
            BuildConfig.APPLICATION_ID,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    fun getIdx():Int{
        return prefs.getInt(Idx, 0)
    }

    fun setIdx(idx:Int){
        prefs.edit().putInt(Idx,idx).apply()
    }

    fun getToken(): String {
        return prefs.getString(Token, "").toString()
    }

    fun setToken(value: String) {
        prefs.edit().putString(Token, value).apply()
    }

    fun removeToken() {
        prefs.edit().remove(Token).apply()
    }

}