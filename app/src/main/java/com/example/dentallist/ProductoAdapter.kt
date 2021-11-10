package com.example.dentallist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.celdaprototipo.view.*

//-------------------------------------------------------------------------------------------
//    Alejandro Guzmán Zazueta                        Agosto 2019
//    Adaptador del RecyclerView
//    Un adapter es la clase que hace de puente entre la vista (el recyclerview) y los datos
//    La clase RecyclerAdapter se encargará de recorrer la lista de estudiantes que le
//    pasaremos más tarde, y llamando a otra clase interna que rellenará los campos.
//--------------------------------------------------------------------------------------------
class ProductoAdapter(private var mListaProductos:List<Producto>,
                        private val mContext: Context, private val clickListener: (Producto) -> Unit)
    : RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder>() {


    /**
     * onCreateViewHolder() que como su nombre indica lo que hará será devolvernos
     * un objeto ViewHolder al cual le pasamos la celda prototió que hemos creado.
     *
     * @return Un nuevo EstudianteViewHolder que contiene la vista para cada estudiante
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val layoutInflater = LayoutInflater.from(mContext)
        return ProductoViewHolder(layoutInflater.inflate(R.layout.celdaprototipo, parent, false))
    }

    /**
     * La clase RecyclerView. onBindViewHolder() se encarga de coger cada una de las
     * posiciones de la lista de estudiantes y pasarlas a la clase ViewHolder(
     *
     * @param holder   Vincular los datos del cursor al ViewHolder
     * @param position La posición de los datos en la lista
     */
    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        holder.bind(mListaProductos[position], mContext, clickListener)
    }

    /**
     * El método getItemCount() nos devuelve el tamaño de la lista, que lo necesita
     * el RecyclerView.
     */
    override fun getItemCount(): Int = mListaProductos.size

    /**
     * Cuando los datos cambian, este metodo actualiza la lista de estudiantes
     * y notifica al adaptador a usar estos nuevos valores
     */
    fun setTask(productos: List<Producto>){
        mListaProductos = productos
        notifyDataSetChanged()
    }

    fun getTasks(): List<Producto> = mListaProductos

    /**
     * Clase interna para crear ViewHolders
     * En esta cargamos los datos en los campos del CardView
     */
    class ProductoViewHolder (itemView: View) :RecyclerView.ViewHolder(itemView) {

        fun bind (prod:Producto, context: Context, clickListener: (Producto) -> Unit){
            //Asigna los valores a los elementos del la celda_prototipo_estudiante

            itemView.tvTitulo.text = prod.CveProd.toString()
            itemView.tvSubTitulo.text = prod.NomProd.toString()

            itemView.setOnClickListener{ clickListener(prod)}
        }
    }

}
