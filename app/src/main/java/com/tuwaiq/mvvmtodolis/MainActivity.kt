package com.tuwaiq.mvvmtodolis

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.tuwaiq.mvvmtodolis.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    val binding:ActivityMainBinding =
        DataBindingUtil.setContentView(this,R.layout.activity_main)

        val currentFragment = supportFragmentManager.findFragmentById(binding.fragmentContainer.id)

        if (currentFragment == null){
            val fragment = ListFragment()
            supportFragmentManager
                .beginTransaction()
                .add(binding.fragmentContainer.id,fragment)
                .commit()
        }





    }
}