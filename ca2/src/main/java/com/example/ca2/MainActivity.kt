package com.example.ca2

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.ca2.fragments.SenderFragment
import com.example.ca2.fragments.ReceiverFragment

class MainActivity : AppCompatActivity(), SenderFragment.DataSenderListener {

    private val SENDERFRAGMENT="SENDER_FRAGMENT"
    private val RECEIVERFRAGMENT="RECEIVER_FRAGMENT"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            loadFragments()
        }
        val fragmentAButton= findViewById<Button>(R.id.btnLoadFragmentA);
        val fragmentBButton= findViewById<Button>(R.id.btnLoadFragmentB);
        fragmentAButton.setOnClickListener {
            showFragment(SENDERFRAGMENT)
        }
        fragmentBButton.setOnClickListener {
            showFragment(RECEIVERFRAGMENT)
        }
    }

    private fun loadFragments() {
        val fragmentA = SenderFragment()
        val fragmentB = ReceiverFragment()
        supportFragmentManager.beginTransaction().apply {
            add(R.id.fragmentContainer, fragmentA,SENDERFRAGMENT)
            add(R.id.fragmentContainer, fragmentB,RECEIVERFRAGMENT)
            hide(fragmentB)
//            addToBackStack(null)
            commit()
        }
    }

    private fun showFragment(tag: String){
        val manager = supportFragmentManager
        val fragmentToShow = manager.findFragmentByTag(tag)
        if(fragmentToShow == null || fragmentToShow.isVisible) return
        manager.beginTransaction().apply {
            manager.fragments.forEach { fragment ->
                if(fragment.isAdded && fragment.isVisible && fragment!=fragmentToShow) hide(fragment)
            }
            show(fragmentToShow)
            commit()
        }
    }

    override fun onDataSent(message: String) {
        val fragmentB = supportFragmentManager.findFragmentById(R.id.fragmentContainer) as? ReceiverFragment
        fragmentB?.receiveData(message)
    }
}
