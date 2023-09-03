package com.example.personaidlserver

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator

class Person : Parcelable {
    private val id: Int
    private val name: String?

    constructor(id: Int, name: String?) {
        this.id = id
        this.name = name
    }

    private constructor(inParcel: Parcel) {
        id = inParcel.readInt()
        name = inParcel.readString()
    }

    override fun toString(): String =
        "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}'

    override fun describeContents(): Int = 0

    override fun writeToParcel(parcel: Parcel, i: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
    }

    companion object CREATOR : Creator<Person> {
        override fun createFromParcel(parcel: Parcel): Person = Person(parcel)

        override fun newArray(size: Int): Array<Person?> = arrayOfNulls(size)
    }
}