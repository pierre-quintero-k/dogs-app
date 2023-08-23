package certificacion.td.perritos.view

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import certificacion.td.perritos.R
import certificacion.td.perritos.databinding.ItemImageBinding
import certificacion.td.perritos.model.local.entities.ImagesEntity
import com.bumptech.glide.Glide

class ImageAdapter: RecyclerView.Adapter<ImageAdapter.ImageVH>() {

    var imageList= listOf<ImagesEntity>()
    val selectedImage= MutableLiveData<ImagesEntity>()

    inner class ImageVH(private val binding: ItemImageBinding):
            RecyclerView.ViewHolder(binding.root),

        //funcionalidad al long click
            View.OnLongClickListener{

                fun bind(imagesEntity: ImagesEntity){
                    Glide.with(binding.root).load(imagesEntity.imageUrl).into(binding.ivDog)

                    //si se ha seleccionado como favorito
                    if (imagesEntity.favourite){
                        binding.ivFab.setImageDrawable(ContextCompat.getDrawable(itemView.context,
                        R.drawable.baseline_star_24))
                        binding.ivFab.setColorFilter(Color.YELLOW)
                        binding.ivFab.visibility=View.VISIBLE
                    } else {
                        //podria buscar un mejor ícono
                        binding.ivFab.setImageDrawable(ContextCompat.getDrawable(itemView.context,
                        R.drawable.baseline_favorite_24))
                        binding.ivFab.setColorFilter(Color.GRAY)
                        binding.ivFab.visibility=View.VISIBLE


                    }

                    itemView.setOnLongClickListener(this)

                }

        override fun onLongClick(v: View?): Boolean {
            selectedImage.value= imageList[adapterPosition]
            return true
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageVH {
        return ImageVH(ItemImageBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun onBindViewHolder(holder: ImageVH, position: Int) {
        holder.bind(imageList[position])
    }

    fun update(list: List<ImagesEntity>){
        imageList=list
        notifyDataSetChanged()
    }

    fun selectedImage(): LiveData<ImagesEntity> = selectedImage

}