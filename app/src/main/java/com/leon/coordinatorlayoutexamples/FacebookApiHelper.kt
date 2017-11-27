package com.leon.coordinatorlayoutexamples

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

import com.facebook.AccessToken
import com.facebook.AccessTokenTracker
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookSdk
import com.facebook.Profile
import com.facebook.ProfileTracker
import com.facebook.appevents.AppEventsLogger
import com.facebook.login.LoginResult
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseUser

/**
 * Created by Leon on 26.4.2016..
 */
class FacebookApiHelper(private val mContext: Context) {
    private val mActivity: Activity
    val callbackManager: CallbackManager
    private val mAccessTokenTracker: AccessTokenTracker
    private val mAccessToken: AccessToken
    private val mProfileTracker: ProfileTracker


    init {
        mActivity = mContext as Activity

        FacebookSdk.sdkInitialize(mContext)
        callbackManager = CallbackManager.Factory.create()
        mAccessTokenTracker = object : AccessTokenTracker() {
            override fun onCurrentAccessTokenChanged(oldAccessToken: AccessToken, currentAccessToken: AccessToken) {

            }
        }

        mAccessToken = AccessToken.getCurrentAccessToken()


        mProfileTracker = object : ProfileTracker() {
            override fun onCurrentProfileChanged(oldProfile: Profile, currentProfile: Profile) {

            }
        }

    }

    fun handleActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }




    fun handleFacebookAccessToken(token: AccessToken) {
        val credential = FacebookAuthProvider.getCredential(token.token)

    }


    fun destroy() {
        mAccessTokenTracker.stopTracking()
        mProfileTracker.stopTracking()
    }



    fun deactivateApp() {
        AppEventsLogger.deactivateApp(mContext)
    }

    companion object {
        private val TAG = FacebookApiHelper::class.java.simpleName
    }


}
