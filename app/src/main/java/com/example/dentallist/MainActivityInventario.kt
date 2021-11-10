package com.example.dentallist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main_inventario.*
import java.util.ArrayList

class MainActivityInventario : AppCompatActivity() {
    private lateinit var viewAdapter: ProductoAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    val productoList: List<Producto> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_inventario)


        viewManager = LinearLayoutManager(this)
        viewAdapter = ProductoAdapter(productoList, this, { estud: Producto -> onItemClickListener(estud) })

        rvProductos.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
            addItemDecoration(DividerItemDecoration(this@MainActivityInventario, DividerItemDecoration.VERTICAL))
        }

        // Metodo para implementar la eliminación de un producto, cuando el ususario da un onswiped en
        // el recyclerview
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
                val position = viewHolder.adapterPosition
                val prod= viewAdapter.getTasks()
                val admin = AdminBD(baseContext)
                if (admin.Ejecuta("DELETE FROM Producto WHERE CveProd=" + prod[position].CveProd)){
                    retrieveProductos()
                }
            }
        }).attachToRecyclerView(rvProductos)
    }

    // Evento clic cuando damos clic en un elemento del Recyclerview
    private fun onItemClickListener(prod: Producto) {
        Toast.makeText(this, "Clicked item" + prod.NomProd, Toast.LENGTH_LONG).show()
    }

    override fun onResume() {
        super.onResume()
        retrieveProductos()
    }

    private fun retrieveProductos() {
        val productoex = getProductos()
        viewAdapter.setTask(productoex!!)
    }

    fun getProductos(): MutableList<Producto>{
        var productos:MutableList<Producto> = ArrayList()
        val admin = AdminBD(this)

        //                                          0       1
        val tupla = admin.Consulta("SELECT CveProd,NomProd FROM Producto ORDER BY NomProd")
        while (tupla!!.moveToNext()) {
            val Cve = tupla.getInt(0)
            val nomP = tupla.getString(1)


            productos.add(Producto(Cve,nomP))
        }
        tupla.close()
        admin.close()
        return productos
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val asignaMenu : MenuInflater = menuInflater
        asignaMenu.inflate(R.menu.menu1, menu)
        return true
    }
}

