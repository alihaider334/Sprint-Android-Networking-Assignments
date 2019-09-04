package com.example.networkingassignment3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.networkingassignment3.model.Employee
import kotlinx.android.synthetic.main.activity_get.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PutActivity : AppCompatActivity(), Callback<Employee> {
    lateinit var employeeService:JsonPlaceHolderAPI
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get)

        employeeService= JsonPlaceHolderAPI.Factory.create()
        UpdateEmployee()
    }
    fun UpdateEmployee(){
        val employee= Employee("Steve",1,25,"Principal Engineer")
        employeeService.updateEmployee(employee).enqueue(this)
    }



    override fun onResponse(call: Call<Employee>, response: Response<Employee>) {
        response.body()?.let {
            Toast.makeText(this,it.toString(), Toast.LENGTH_SHORT).show()
            progressBar.isVisible=false
            result.text=it.toString()
        }

    }
    override fun onFailure(call: Call<Employee>, t: Throwable) {
        Toast.makeText(this,"Failed", Toast.LENGTH_SHORT).show()
    }
}
