package com.example.personaidlserver

import android.app.Service
import android.content.Intent
import android.os.IBinder

class PersonAIDLService : Service() {
    private val people = ArrayList<Person>()

    override fun onBind(intent: Intent?): IBinder {
        return object : IPersonManager.Stub() {
            override fun addPerson(person: Person) {
                people.add(person)
            }

            override fun getPersonList(): MutableList<Person> = people
        }
    }
}