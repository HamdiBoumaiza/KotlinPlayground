package com.hamdi.kotlinplayground

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import timber.log.Timber

class DestructuringDeclarationsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_empty)


        val employee1 = Employee("John Doe", "Developer", 30)
        val employee2 = Employee("Bill Gates", "Buisness man", 41)
        val employee3 = Employee("Warren Buffet", "Investor", 25)
        val employee4 = Employee("Leonardo di caprio", "Actor", 43)


        val (name, job, age) = employee1
        Timber.e(employee1.component1())
        Timber.e("name is : $name \n job is :$job \n age is : $age")


        val (nameEmpl, _, _) = employee1
        Timber.e(nameEmpl)


        val employeesList = listOf(employee1, employee2, employee3, employee4)

        for ((nameEmployee, jobEmployee, ageEmployee) in employeesList) {
            if (ageEmployee > 40) Timber.e("employee over 40 is : $nameEmployee and his job is $jobEmployee")
        }


    }
}

data class Employee(val name: String, val job: String, val age: Int)