package certificacion.td.perritos.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import certificacion.td.perritos.databinding.ItemBreedBinding
import certificacion.td.perritos.model.local.entities.BreedEntity
import certificacion.td.perritos.model.remote.fromInternet.Breed

class BreedAdapter: RecyclerView.Adapter<BreedAdapter.BreedVH>() {

    private var listBreed= listOf<BreedEntity>()
    private val selectedBreed= MutableLiveData<BreedEntity>()

    inner class BreedVH(private val binding: ItemBreedBinding):
            RecyclerView.ViewHolder(binding.root),

            View.OnClickListener{

                fun bind(breedEntity: BreedEntity){
                    binding.tvbreed.text=breedEntity.breed
                    itemView.setOnClickListener(this)
                }

        override fun onClick(v: View?) {
            selectedBreed.value= listBreed[adapterPosition]//revisar aca
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedVH {
        return BreedVH(ItemBreedBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int {
        return listBreed.size
    }

    override fun onBindViewHolder(holder: BreedVH, position: Int) {
        holder.bind(listBreed[position])
    }

    fun update(list: List<BreedEntity>){
        listBreed=list
        notifyDataSetChanged()
    }

    fun selectedBreed(): LiveData<BreedEntity> = selectedBreed
}