package com.example.explorecityprovince

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginbtn = findViewById<Button>(R.id.login)
        loginbtn.setOnClickListener{
            if(isvalidateCredentials()) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            else {
                Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isvalidateCredentials(): Boolean {
        val username = findViewById<EditText>(R.id.username)
        val password = findViewById<EditText>(R.id.password)
        if(username.text.toString().equals("test@gmail.com") && password.text.toString().equals("password")){
            return true
        }
        return false
    }
}