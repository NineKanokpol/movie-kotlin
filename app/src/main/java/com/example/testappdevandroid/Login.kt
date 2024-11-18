package com.example.testappdevandroid

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class Login : AppCompatActivity() {

    private var fixUsername:String = "Nine"
    private var fixPassword:String = "12345"
    private lateinit var username:EditText
    private lateinit var password:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnLogin.setOnClickListener { v -> CheckPassword() }

    }

//    fun btnToLogin() {
//        btnLogin.setOnClickListener {
//            CheckPassword()
//        }
//    }

    fun CheckPassword() {
        username = findViewById(R.id.username)
        password = findViewById(R.id.password)
        var mUser:String = username.text.toString()
        var mPass:String = password.text.toString() //ต้อง .text เพราะว่าต้อง get string ใน edittext มาใช้

        if (mUser == fixUsername && mPass == fixPassword) {
            var IntentLogin = Intent(this,AfterLogin::class.java)
            startActivity(IntentLogin)
        } else if (username.text.isEmpty()) {
            Toast.makeText(this, "Please Enter Username!", Toast.LENGTH_LONG).show()
        } else if (password.text.isEmpty()){
            Toast.makeText(this,"Please Enter Password!",Toast.LENGTH_LONG).show()
        } else {
            var notCon = Intent(this,Login::class.java)
            startActivity(notCon)
        }
        if(username!!.equals(fixUsername) && password!!.equals(fixPassword)){ //เช็ค text
            var IntentLogin = Intent(this,AfterLogin::class.java)
            startActivity(IntentLogin)
        } else if(username.text == null || password.text == null ){
            var dialog = ProgressDialog(this)
            dialog.setTitle(R.string.Alert)
            dialog.setMessage(R.string.contenting.toString())
            dialog.show()
        } else{

        }


    }

}

