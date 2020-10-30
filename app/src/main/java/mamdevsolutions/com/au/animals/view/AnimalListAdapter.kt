package mamdevsolutions.com.au.animals.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_animal.view.*
import mamdevsolutions.com.au.animals.R
import mamdevsolutions.com.au.animals.model.Animal
import mamdevsolutions.com.au.animals.util.getProgressDrawable
import mamdevsolutions.com.au.animals.util.loadImage

class AnimalListAdapter(private val animalList: ArrayList<Animal>):
        RecyclerView.Adapter<AnimalListAdapter.AnimalViewHolder>() {

    fun updateAnimalList(newAnimalList :List<Animal>) {
        animalList.clear()
        animalList.addAll(newAnimalList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val inflater :LayoutInflater = LayoutInflater.from(parent.context)
        val view :View = inflater.inflate(R.layout.item_animal, parent, false)

        return AnimalViewHolder(view)
    }

    override fun getItemCount() = animalList.size

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        holder.view.animalName.text = animalList[position].name
        holder.view.animalImage.loadImage(animalList[position].imageUrl, getProgressDrawable(holder.view.context))
    }

    class AnimalViewHolder(var view: View): RecyclerView.ViewHolder(view)
}