package com.example.hellokotlin

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.login_page.*


class LoginActivity : AppCompatActivity() {

    lateinit var usersDBHelper : UsersDBHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_page)
        usersDBHelper = UsersDBHelper(this)
    }

    fun login_button_down(v: View){
        var userid:String = this.edittext_userid.text.toString()
        var users = usersDBHelper.read_userid()
        var hasUser = false
        users.forEach{
            if (it == userid)
            {
                UserSingleton.currentuserid = userid
                hasUser= true
            }
        }
        if(hasUser == true) {
            val intent = Intent(this, MainActivity::class.java)
            // start your next activity
            startActivity(intent)
        }
    }
    fun register_button_down(v:View)
    {
        var userid:String = this.edittext_userid.text.toString()
        var users = usersDBHelper.read_userid()
        var hasUser = false
        users.forEach{
            if (it == userid)
            {
                hasUser= true
            }
        }
        if(hasUser == false)
        {
            UserSingleton.currentuserid = userid
            usersDBHelper.insertUser(userid)
            val intent = Intent(this, MainActivity::class.java)
            // start your next activity
            startActivity(intent)
        }
    }
}