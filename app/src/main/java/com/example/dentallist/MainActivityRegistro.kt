package com.example.dentallist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main_registro.*

class MainActivityRegistro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_registro)

    }

    fun btnRegistrar(view: android.view.View) {
        if(txtNombre.text.toString().isEmpty() ){
            txtNombre.setError("Error falta de ingresar el nombre")
            txtNombre.requestFocus()
        }
        else{
            if(txtCorr.text.toString().isEmpty()){
                txtCorr.setError("Error fal de ingresar el correo electronico")
                txtCorr.requestFocus()
            }
            else{
                if(txtContrasena.text.toString().isEmpty()){
                    txtContrasena.setError("Error de ingresar la contraseña")
                    txtContrasena.requestFocus()
                }
                else{
                    var admin : AdminBD = AdminBD(this)
                    val nom : String = txtNombre.text.toString()
                    val corr : String = txtCorr.text.toString()
                    val contra : String = txtContrasena.text.toString()
                    var sentencia : String = "Insert Into Registro (NomRegistro,CorreoReg,Contrasena) values ('$nom','$corr','$contra')"
                    if (admin.Ejecuta(sentencia)){ // se ejecuto con exito
                        Toast.makeText(this, "Registro Exitoso", Toast.LENGTH_SHORT).show()
                        var actividad : Intent = Intent(this,MainActivityproductos::class.java)
                        startActivity(actividad)
                    }
                    else{
                        Toast.makeText(this, "error no se pudo registrar", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}
