package com.example.simplekahoot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class NewTeacher : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_teacher)

        var btnCreateNewTeacher=findViewById<Button>(R.id.btnCreateNewTeacher)
        var txterrorNewTeacher=findViewById<TextView>(R.id.txtError2)
        var txtemail=findViewById<EditText>(R.id.txtNewTeacherEmail)
        var txtNewTeacherPassword=findViewById<EditText>(R.id.txtNewTeacherPassword)
        var txtNewTeacherName=findViewById<EditText>(R.id.txtTeacherName)
        btnCreateNewTeacher.setOnClickListener(){
            txterrorNewTeacher.visibility= View.INVISIBLE
            if(ValidNewTeacher(txtemail.text.toString(),txtNewTeacherName.text.toString(),txtNewTeacherPassword.text.toString())){
                currentTeacher=Teacher(txtNewTeacherName.text.toString(),txtemail.text.toString(),txtNewTeacherPassword.text.toString())
                allTeachers.add(currentTeacher)
                goToTeacherHomeActivity()
            }  else{
                   txterrorNewTeacher.visibility=View.VISIBLE
                txterrorNewTeacher.text="Invalid New Teacher"
            }

        }
    }

    fun ValidNewTeacher(email:String,name:String,pwd:String):Boolean{
             return (email.trim()!=""&&name.trim()!=""&&pwd.trim()!="")
    }

     fun goToTeacherHomeActivity(){
            var intent= Intent(this,TeacherHome::class.java)
            startActivity(intent)
}


}