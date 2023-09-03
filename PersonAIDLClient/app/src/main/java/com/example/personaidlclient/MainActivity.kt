package com.example.personaidlclient

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.os.RemoteException
import androidx.appcompat.app.AppCompatActivity
import com.example.personaidlclient.databinding.ActivityMainBinding
import com.example.personaidlserver.IPersonManager
import com.example.personaidlserver.Person

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var iPersonManager: IPersonManager? = null

    private val conn: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            iPersonManager = IPersonManager.Stub.asInterface(service)
        }

        override fun onServiceDisconnected(className: ComponentName) {
            iPersonManager = null
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.setButtonClickListener()
    }

    private fun ActivityMainBinding.setButtonClickListener() {
        bindService.setOnClickListener {
            val intent: Intent = Intent().also {
                it.component = ComponentName(
                    "com.example.personaidlserver",
                    "com.example.personaidlserver.PersonAIDLService"
                )
            }
            bindService(intent, conn, BIND_AUTO_CREATE)
        }
        unbindService.setOnClickListener {
            unbindService(conn)
        }
        addPerson.setOnClickListener {
            try {
                iPersonManager?.addPerson(Person(24, "David"))
            } catch (e: RemoteException) {
                e.printStackTrace()
            }
        }
        getPerson.setOnClickListener {
            try {
                val people = iPersonManager?.personList as ArrayList<Person>?
                showMsg.text = people.toString()
            } catch (e: RemoteException) {
                e.printStackTrace()
            }
        }
    }
}