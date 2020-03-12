package com.example.hellokotlin

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.login_page.*


class MainActivity : AppCompatActivity() {

    lateinit var usersDBHelper : UsersDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        usersDBHelper = UsersDBHelper(this)

        if(UserSingleton.targetuserid!="")
        {
            display(UserSingleton.targetuserid)
        }
        else
            display()

    }
    fun display()
    {
        var userm = usersDBHelper.readUser(UserSingleton.currentuserid)
        this.main_userid_info.setText( userm[0].userid,TextView.BufferType.EDITABLE)
        this.main_major_info.setText( userm[0].major,TextView.BufferType.EDITABLE)
    }

    fun display(targetuser:String)
    {
        var userm = usersDBHelper.readUser(targetuser)
        this.main_userid_info.setText( userm[0].userid,TextView.BufferType.EDITABLE)
        this.main_major_info.setText( userm[0].major,TextView.BufferType.EDITABLE)
    }

    fun userlist_button_down(v: View)
    {
        val intent = Intent(this, UserlistActivity::class.java)
        // start your next activity
        startActivity(intent)
    }
    fun edit_user_major(v:View)
    {

    }
    fun save_button_down(v:View)
    {
        var userMajor:String = this.main_major_info.text.toString()
        var userModel =UserModel(UserSingleton.currentuserid,"",userMajor)
        try {
            val isUpdate = usersDBHelper.updateUser(UserSingleton.currentuserid,userModel)
            if (isUpdate == true)
                //showToast("Data Updated Successfully")
            else{

            }
                //showToast("Data Not Updated")
        }catch (e: Exception){
            e.printStackTrace()
            //showToast(e.message.toString())
        }
    }

}
