package com.example.dentallist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

import org.json.JSONArray
import org.json.JSONObject
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    val IP = "http://192.168.1.65/"
    var admin : AdminBD = AdminBD(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }



fun btnRegistro(view: android.view.View) {
        var actividad: Intent = Intent(this, MainActivityRegistro::class.java)
        startActivity(actividad)
    }

}
