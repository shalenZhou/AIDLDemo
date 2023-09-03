// IPersonManager.aidl
package com.example.personaidlserver;

// Declare any non-default types here with import statements
import com.example.personaidlserver.Person; //必须手敲，导入包

interface IPersonManager {
    void addPerson(in Person person); //自定义对象需要在前面加in、out、inout
    List<Person> getPersonList();
}