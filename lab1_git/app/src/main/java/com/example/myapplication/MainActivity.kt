package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val students: MutableList<Student> = getFilledList()

        val buttonOK = findViewById<Button>(R.id.buttonOk)
        val buttonCancel = findViewById<Button>(R.id.buttonCancel)

        val courseRG = findViewById<RadioGroup>(R.id.radioGroupCourse)
        val facultyRG = findViewById<RadioGroup>(R.id.radioGroupFaculty)

        val text = findViewById<TextView>(R.id.textBox)

        buttonOK.setOnClickListener {

            val checkedCourseButtonID = courseRG.checkedRadioButtonId
            val checkedFacultyButtonID = facultyRG.checkedRadioButtonId
            if (checkedCourseButtonID == -1 || checkedFacultyButtonID == -1) {
                Toast.makeText(applicationContext, "Check both radio buttons", Toast.LENGTH_LONG).show();
                return@setOnClickListener
            }
            val checkedCourseRB = findViewById<RadioButton>(checkedCourseButtonID)
            val checkedFacultyRB = findViewById<RadioButton>(checkedFacultyButtonID)

            var course: String = getValidCourse(checkedCourseRB.text.toString())
            var faculty: String = getValidFaculty(checkedFacultyRB.text.toString())

            var output: String = "Course: $course\nFaculty: $faculty\n"
            for (student in students) {
                if (student.faculty == faculty && student.course == course) {
                    output = output + student.fullName + "\n"
                }
            }

            text.text = output
        }

        buttonCancel.setOnClickListener {
            text.text = ""
        }
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

    private fun getValidCourse(chosenCourse: String): String {
        var course: String = ""
        when (chosenCourse) {
            "Курс 1" -> course = "1"
            "Курс 2" -> course = "2"
            "Курс 3" -> course = "3"
            "Курс 4" -> course = "4"
        }
        return course
    }

    private fun getValidFaculty(chosenFaculty: String): String {
        var faculty: String = ""
        when (chosenFaculty) {
            "ФІОТ" -> faculty = "FICT"
            "ФПМ" -> faculty = "FAM"
            "ІПСА" -> faculty = "IASA"
            "ФАКС" -> faculty = "FACS"
        }
        return faculty
    }
}