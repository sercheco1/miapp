package com.example.dentallist

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.lang.Exception

class AdminBD(context : Context) : SQLiteOpenHelper(context, "BDdental",null, 1) {


    override fun onCreate(bd: SQLiteDatabase?) {
        bd?.execSQL("Create Table Registro(" +
                "idReg integer primary Key AUTOINCREMENT," +
                "NomRegistro Text," +
                "CorreoReg Text,"+
                "Contrasena Text"+
                ")")
        bd?.execSQL("Create Table Producto(" +
                "CveProd integer primary Key ," +
                "NomProd text" +
                ")")




    }

    // Función para mandar ejecutar un INSERT, UPDATE, DELETE
    fun Ejecuta(sentencia : String) :Boolean{
        try {
            val bd = this.writableDatabase
            bd.execSQL(sentencia)
            bd.close()
            return true
        }
        catch (ex : Exception){
            Log.d("Zazueta","Error en el Ejecuta:" + ex.toString())
            return false
        }
    }

    // Función para mandar ejecutar una consulta SELECT
    fun Consulta(query : String) : Cursor?{
        try {
            val bd = this.readableDatabase
            return bd.rawQuery(query, null)
        }
        catch (ex : Exception){
            Log.d("Zazueta","Error en la Consulta:" + ex.toString())
            return null
        }
    }


    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
}