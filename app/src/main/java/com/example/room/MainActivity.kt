package com.example.room

import androidx.appcompat.app.AppCompatActivity
import  com.example.room.data.*
import android.widget.TextView
import android.widget.Button
import android.widget.EditText
import android.os.Bundle

import android.content.pm.ActivityInfo
import androidx.appcompat.widget.LinearLayoutCompat
import android.graphics.drawable.AnimationDrawable
import android.view.View
import android.widget.Toast

import android.content.Intent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

//import com.apps.myapplication1.databinding.ActivityStartBinding;
class MainActivity : AppCompatActivity() {
    var db: UserDao? = null
    var register_txt: TextView? = null
    var Login: Button? = null
    var Usernme: EditText? = null
    var Userpasswrd: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = UserDatabase.getDatabase(this).UserDao()
        setContentView(R.layout.activity_main)
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        val Main = findViewById<LinearLayoutCompat>(R.id.main_layout)
        val Animation = Main.background as AnimationDrawable
        Animation.setEnterFadeDuration(2500)
        Animation.setExitFadeDuration(5000)
        Animation.start()
        Login = findViewById(R.id.lgn_btn)
        register_txt = findViewById(R.id.rgstr_txt)
        Usernme = findViewById(R.id.usr_edt_lgn)
        Userpasswrd = findViewById(R.id.pass_edt_lgn)
        Login?.setOnClickListener(View.OnClickListener {
            val usernamelogin = Usernme?.getText().toString()
            val userpasslogin = Userpasswrd?.getText().toString()
            if (usernamelogin.isEmpty() || userpasslogin.isEmpty()) {
                Toast.makeText(
                    this@MainActivity, "Please Fill The " +
                            "Form First ", Toast.LENGTH_LONG
                ).show()
            } else {
                GlobalScope.launch(Dispatchers.IO) {
                    val usr = db!!.getUser(usernamelogin, userpasslogin)
                    launch(Dispatchers.Main) {
                        if (usr != null) {
                        Toast.makeText(
                            this@MainActivity,
                            "Welcome! $usernamelogin",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                            val gostart = Intent(this@MainActivity, StartActivity::class.java)
                            startActivity(gostart)
                        } else if (usr == null) {
                            Toast.makeText(
                                this@MainActivity,
                                "wrong user name or password ", Toast.LENGTH_SHORT
                            )
                                .show()
                        }
                    }
                }
            }
        })
        register_txt?.setOnClickListener(View.OnClickListener {
            val goregister = Intent(this@MainActivity, Registration::class.java)
            startActivity(goregister)
        })
    }
}