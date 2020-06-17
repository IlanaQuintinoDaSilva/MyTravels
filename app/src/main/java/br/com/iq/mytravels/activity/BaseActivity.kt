package br.com.iq.mytravels.activity


import android.content.Context
import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {
    // property to access context from anywhere
    protected val context: Context get() = this

    // All common methods are listed here:

}