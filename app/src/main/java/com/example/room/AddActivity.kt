package com.example.room
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.room.members.MemberDao
import com.example.room.members.Members
import com.example.room.members.MembersDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddActivity : AppCompatActivity() {

    private lateinit var add_btn: Button
    private lateinit var name: EditText

    private lateinit var Database: MembersDatabase
    private lateinit var Dao: MemberDao

    var checked: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        Database = MembersDatabase.getDatabase(applicationContext)
        Dao = Database.MemberDao()
        add_btn = findViewById(R.id.add_btn)
        name = findViewById(R.id.edit_name)
        val context: Context = applicationContext

        val spinner: Spinner = findViewById(R.id.role)
        val optionsArray = arrayOf("Head", "Vice Head", "Member")
        val adapter_ = ArrayAdapter(this, android.R.layout.simple_spinner_item, optionsArray)
        adapter_.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter_
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedItem = parent.getItemAtPosition(position).toString()
                handleSelectedItem(selectedItem)

            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
            @SuppressLint("NotifyDataSetChanged")
            fun handleSelectedItem(selectedItem: String){
                val userroletxt = selectedItem
                add_btn.setOnClickListener {
                    val usernametxt = name.text.toString()
                    if (usernametxt.isEmpty()) {
                        Toast.makeText(context, "Please Fill The Form First", Toast.LENGTH_LONG)
                            .show()
                    } else {
                        checked = true
                        GlobalScope.launch(Dispatchers.IO) {
                            val member = Members(0, usernametxt, userroletxt)
                            Dao.addMember(member)
                        }
                        if (checked){
                            Toast.makeText(context, usernametxt+" added succesfully !", Toast.LENGTH_SHORT).show()
                            val goback = Intent(this@AddActivity, StartActivity::class.java)
                            startActivity(goback)
                        }

                    }
                }
            }

        }


    }
}
