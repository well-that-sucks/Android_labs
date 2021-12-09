package com.example.lab2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentInput.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentInput : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var buttonOk: Button? = null
    var courseRG: RadioGroup? = null
    var facultyRG: RadioGroup? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_input, container, false)
        buttonOk = view.findViewById(R.id.buttonOk)
        courseRG = view.findViewById(R.id.radioGroupCourse)
        facultyRG = view.findViewById(R.id.radioGroupFaculty)
        buttonOk?.setOnClickListener {
            val checkedCourseButtonID = courseRG?.checkedRadioButtonId
            val checkedFacultyButtonID = facultyRG?.checkedRadioButtonId
            if (checkedCourseButtonID == -1 || checkedFacultyButtonID == -1) {
                Toast.makeText(activity?.applicationContext, "Check both radio buttons", Toast.LENGTH_LONG).show();
                return@setOnClickListener
            }
            val checkedCourseRB = checkedCourseButtonID?.let { it1 -> view.findViewById<RadioButton>(it1) }
            val checkedFacultyRB = checkedFacultyButtonID?.let { it1 -> view.findViewById<RadioButton>(it1) }

            var course: String = getValidCourse(checkedCourseRB?.text.toString())
            var faculty: String = getValidFaculty(checkedFacultyRB?.text.toString())
            val students = (activity as MainActivity).students
            var output: String = "Course: $course\nFaculty: $faculty\n"
            for (student in students!!) {
                if (student.faculty == faculty && student.course == course) {
                    output = output + student.fullName + "\n"
                }
            }
            (activity as MainActivity).changeFragmentResultText(output)
            (activity as MainActivity).blitResultFragment()
        }
        return view
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

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentInput.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentInput().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}