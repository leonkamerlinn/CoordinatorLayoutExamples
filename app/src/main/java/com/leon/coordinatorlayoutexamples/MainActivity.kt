package com.leon.coordinatorlayoutexamples

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.facebook.*
import com.facebook.login.LoginManager
import io.reactivex.*
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Cancellable
import org.jetbrains.anko.find
import com.facebook.login.LoginResult
import org.jetbrains.anko.AnkoLogger
import org.json.JSONObject
import java.util.*




class MainActivity : AppCompatActivity(), AnkoLogger {


    val tabs by lazy { find<TabLayout>(R.id.tabs) }
    val viewPager by lazy { find<ViewPager>(R.id.viewPager) }
    val fab by lazy { find<FloatingActionButton>(R.id.fab) }
    val coordinator by lazy { find<CoordinatorLayout>(R.id.coordinator) }
    
    

    private var callbackManager: CallbackManager? = null
    private lateinit var accessTokenTracker: AccessTokenTracker
    private lateinit var profileTracker: ProfileTracker
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val simplePagerAdapter = SimplePagerAdapter(supportFragmentManager)
        viewPager.adapter = simplePagerAdapter
        tabs.setupWithViewPager(viewPager)

        val snackbar = Snackbar.make(coordinator, "No internet connection!", Snackbar.LENGTH_LONG).setAction("RETRY", { view ->
            val snackbar1 = Snackbar.make(coordinator, "Connecting...", Snackbar.LENGTH_SHORT)
            snackbar1.show()
        })
    
    
    

        callbackManager = CallbackManager.Factory.create()
        accessTokenTracker = object : AccessTokenTracker() {
            override fun onCurrentAccessTokenChanged(oldAccessToken: AccessToken?, currentAccessToken: AccessToken) {
            
            }
        }
    
     
    
    
        profileTracker = object : ProfileTracker() {
            override fun onCurrentProfileChanged(oldProfile: Profile, currentProfile: Profile) {
            
            }
        }
    
        FacebookAuth()
     
    
        /*recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = RecyclerViewAdapter(this)
        recyclerView.itemAnimator = DefaultItemAnimator()*/
        fab.setOnClickListener({ view ->


            /*val snackbar = Snackbar.make(coordinator, "Welcome to AndroidHive", Snackbar.LENGTH_LONG)
                .setAction("UNDO", {view ->
                    val snackbar1 = Snackbar.make(coordinator, "Message is restored!", Snackbar.LENGTH_SHORT)
                    snackbar1.show()
                })
            snackbar.show()*/




            snackbar.setActionTextColor(Color.RED)

            // Changing action button text color
            val sbView = snackbar.getView()
            val textView = sbView.find<TextView>(android.support.design.R.id.snackbar_text)
            textView.setTextColor(Color.YELLOW)

            snackbar.show()


        })


    }
    
    
    
    
 
    object EmptyString : FlowableOnSubscribe<String> {
        override fun subscribe(e: FlowableEmitter<String>) {
        
        }
    }
    
    
    override fun onDestroy() {
        super.onDestroy()
        accessTokenTracker.stopTracking()
        profileTracker.stopTracking()
    }
    
    
    object EmptyFlowableEmitterString: FlowableEmitter<String> {
        override fun serialize(): FlowableEmitter<String> = this
        override fun setDisposable(s: Disposable?) {
        
        }
    
        override fun isCancelled(): Boolean = false
        override fun onComplete() {
   
        }
    
        override fun setCancellable(c: Cancellable?) {
     
        }
    
        override fun onError(error: Throwable) {
   
        }
    
        override fun onNext(value: String) {
       
        }
        
        override fun requested(): Long = 0
    }
    
    
    
    
    fun FacebookAuth() {
  
    
        LoginManager.getInstance().registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onError(error: FacebookException?) {
                println("onError")
            }
    
            override fun onSuccess(result: LoginResult?) {
                val token = result!!.accessToken.token
    
                val request = GraphRequest.newMeRequest(result!!.accessToken, object: GraphRequest.GraphJSONObjectCallback {
                    override fun onCompleted(jsonObject: JSONObject?, response: GraphResponse?) {
                        println(jsonObject)
                    }
                })
                
                
                val parameters = Bundle()
                parameters.putString("fields", "id,name,email,first_name,last_name,gender,cover,age_range,link,locale,picture,timezone,updated_time,verified,about,birthday,education,friends,friendlists,photos")
                request.parameters = parameters
                request.executeAsync()
            }
    
            override fun onCancel() {
                println("onCancel")
            }
        })
    
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "email", "user_friends", "user_birthday", "user_about_me", "read_custom_friendlists"))
    
    }
    
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager!!.onActivityResult(requestCode, resultCode, data)
    }
}


