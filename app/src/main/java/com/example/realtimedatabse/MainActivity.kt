package com.example.realtimedatabse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragment_login_store = login()
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.add(R.id.contentContainer, fragment_login_store,"fragment_login_store")
        transaction.commit()

    }
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//
//        val manager = supportFragmentManager
//        val transaction = manager.beginTransaction()
//
//
//        val ShowData = ShowData()
//        transaction.replace(R.id.show, ShowData,"fragment_ShowData")
//        transaction.addToBackStack("fragment_ShowData")
//
//        val DataRealtime = DataRealtime()
//        transaction.replace(R.id.add, DataRealtime,"fragment_DataRealtime")
//        transaction.addToBackStack("fragment_DataRealtime")
//
//        transaction.commit()
//
//
//    }

}
