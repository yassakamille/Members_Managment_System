package com.example.room

import com.example.room.data.*
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.os.Bundle
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class Registration : AppCompatActivity() {


    private lateinit var userDatabase: UserDatabase
    private lateinit var userDao: UserDao
    private lateinit var regButton: Button
    private lateinit var username: EditText
    private lateinit var userpassword: EditText
    private lateinit var useremail: EditText
    private lateinit var userphone: EditText
    private lateinit var usrdao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        userDatabase = UserDatabase.getDatabase(applicationContext)
        userDao = userDatabase.UserDao()
        regButton = findViewById(R.id.rgstr_btn)
        useremail = findViewById(R.id.usrmail)
        username = findViewById(R.id.usrnm)
        userphone = findViewById(R.id.usrphn)
        userpassword = findViewById(R.id.usrpss)

//        usrdao = UserDatabase.getDatabase(this).UserDao()

        regButton.setOnClickListener {
            val usernametxt = username.text.toString()
            val userpasswordtxt = userpassword.text.toString()
            val useremailtxt = useremail.text.toString()
            val userphonetxt = userphone.text.toString()

            if (usernametxt.isEmpty() || userpasswordtxt.isEmpty() || useremailtxt.isEmpty() || userphonetxt.isEmpty()) {
                Toast.makeText(this@Registration, "Please Fill The Form First", Toast.LENGTH_LONG)
                    .show()
            } else {
                GlobalScope.launch(Dispatchers.IO) {
                    val user = User(0, usernametxt, userpasswordtxt, userphonetxt, useremailtxt)


                        userDao.addUser(user)
                        var checked: Boolean = true
//                    Toast.makeText(
//                        this@Registration,
//                        "Registration Successful!",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                onBackPressed()


                }

            }
        }
    }
}