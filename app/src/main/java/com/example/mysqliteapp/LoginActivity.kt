package com.example.mysqliteapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mysqliteapp.database.DatabaseHelper
import com.example.mysqliteapp.model.User
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private val databaseHelper = DatabaseHelper(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btnRegisterListener()
        btnLoginListener()
    }

    private fun btnRegisterListener() {
        btnRegister.setOnClickListener() {
            if (!databaseHelper.checkUser(txtUsername.text.toString().trim())) {
                val user = User()
                user.username = txtUsername.text.toString().trim()
                user.password = txtPassword.text.toString().trim()
              databaseHelper.addUser(user)
                Toast.makeText(this,"User created successfully",Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this,"User is already exists",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun btnLoginListener(){
        btnLogin.setOnClickListener(){
            if(databaseHelper.checkUser(txtUsername.text.toString().trim(),txtPassword.text.toString().trim())){
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
                Toast.makeText(this,"Login successfully",Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this,"Username or password is wrong, please try again",Toast.LENGTH_SHORT).show()
            }

        }
    }
}