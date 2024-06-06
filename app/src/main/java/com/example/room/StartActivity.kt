 package com.example.room
 import android.annotation.SuppressLint
 import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
 import android.widget.ImageView
 import androidx.lifecycle.MutableLiveData
 import androidx.recyclerview.widget.RecyclerView
 import com.example.room.members.MemberDao
 import com.example.room.members.Members
 import com.example.room.members.MembersDatabase
 import com.example.room.rec.Adapter
 import kotlinx.coroutines.*
 import kotlin.system.exitProcess


 class StartActivity : AppCompatActivity() {
     private lateinit var sharedPreference: SharedPreferences
     private lateinit var rec: RecyclerView
     private lateinit var log_out: View
     private lateinit var add: View
     private lateinit var Database: MembersDatabase
     private lateinit var Dao: MemberDao
     private lateinit var memberAdapter:Adapter

     @OptIn(DelicateCoroutinesApi::class)
     @SuppressLint("NotifyDataSetChanged")
     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)

         setContentView(R.layout.activity_start)
         Database = MembersDatabase.getDatabase(applicationContext)
         Dao = Database.MemberDao()

         rec = findViewById(R.id.recyclerView)
         sharedPreference = getSharedPreferences("pref", Context.MODE_PRIVATE)
         add = findViewById(R.id.add)
         log_out = findViewById(R.id.out)


         log_out.setOnClickListener {

             AlertDialog.Builder(this)
                 .setTitle("LOGOUT")
                 .setMessage("Do You Want To Logout ? ")
                 .setCancelable(false)
                 .setNegativeButton("No") { dialogInterface, _ ->
                     dialogInterface.cancel()
                 }
                 .setPositiveButton("Yes") { dialogInterface, _ ->
                     moveTaskToBack(true)
                     val editor = sharedPreference.edit()
                     editor.putBoolean("remember", false)
                     editor.apply()
                     finishAffinity()
                     super.onBackPressed()
                 }
                 .show()
         }

         add.setOnClickListener {
             val intent = Intent(this, AddActivity::class.java)
             startActivity(intent)
         }
         var membersLiveData = MutableLiveData<List<Members>>()

         CoroutineScope(Dispatchers.IO).launch {
             var members = Dao.getallmemebers()
             withContext(Dispatchers.Main) {
                 membersLiveData.value = members
             }
         }

         membersLiveData.observe(this@StartActivity, { members ->
             memberAdapter = Adapter(members)
             rec.adapter = memberAdapter
             rec.post {
                 rec.scrollToPosition(memberAdapter.itemCount - 1)
             }
         })


     }



     @SuppressLint("NotifyDataSetChanged")
     override fun onResume() {
         super.onResume()
         CoroutineScope(Dispatchers.IO).launch {
             var members = Dao.getallmemebers()
             memberAdapter = Adapter(members)
             memberAdapter.list = Dao.getallmemebers()
             memberAdapter.notifyDataSetChanged()
         }
     }

     @Deprecated("Deprecated in Java")
     override  fun onBackPressed() {
         super.onBackPressed()
         finishAffinity() // Finish all activities in the task associated with this activity
         System.exit(0) // Exit the application
         return

     }

 }


