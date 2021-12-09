package com.example.lab2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {
    var fragmentInput: FragmentInput? = null
    var fragmentResult: FragmentResult? = null
    var students: MutableList<Student>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        students = getFilledList()

        fragmentResult = FragmentResult()
        fragmentInput = FragmentInput()

        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragmentContainerInput, fragmentInput!!)
        ft.commit()
    }

    fun changeFragmentResultText(text: String) {
        fragmentResult?.changeText(text)
    }

    fun blitResultFragment() {
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragmentContainerResult, fragmentResult!!)
        ft.commit()
    }

    private fun getFilledList(): MutableList<Student> {
        val students: MutableList<Student> = mutableListOf()
        students.add(Student("Worobets Alexandr Sergeevich", "FICT", "1"))
        students.add(Student("Dupak Maksim Sergeevich", "FICT", "1"))
        students.add(Student("Bespalov Anton Andreevich", "FAM", "2"))
        students.add(Student("Derkach Stanislav Antonovich", "FAM", "1"))
        students.add(Student("Ivanov Rodion Aleksandrovich", "IASA", "2"))
        students.add(Student("Kuhlenko Maksim Petrovich", "IASA", "3"))
        students.add(Student("Tkachenko Alexandra Dmitrievna", "FACS", "4"))
        students.add(Student("Proshina Nazariya Olegovna", "FACS", "1"))
        return students
    }
}