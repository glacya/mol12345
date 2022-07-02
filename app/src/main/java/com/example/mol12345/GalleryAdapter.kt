package com.example.mol12345

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.recyclerview.widget.RecyclerView

//
class GalleryAdapter(private val launcher: ActivityResultLauncher<Intent>): RecyclerView.Adapter<GalleryAdapter.GalleryHolder>() {
    var imageList = mutableListOf<GalleryListData>()

    inner class GalleryHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
//        private var idImageView: ImageView = itemView.findViewById(R.id.gallery_image)
        private var idImageButton: ImageButton = itemView.findViewById(R.id.gallery_image)

        fun setImage(listData: GalleryListData) {   // for binding
            val uri = "image_${listData.image_number}"

            val context = idImageButton.context
            val imageResource = context.resources.getIdentifier(uri, "drawable", context.packageName)

            idImageButton.setImageResource(imageResource)
            idImageButton.adjustViewBounds = true
            idImageButton.setOnClickListener {
                val intent = Intent(context, GalleryShowActivity::class.java)
                intent.putExtra("image_number", listData.image_number)
                intent.putExtra("image_resource", imageResource)
                context.startActivity(intent)
            }


            val intent = Intent(Intent.ACTION_GET_CONTENT).setType("image/*")
            val chooser = Intent.createChooser(intent, "choose image")
            launcher.launch(intent)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.gallery_list_item, parent, false)

        return GalleryHolder(view)
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun onBindViewHolder(holder: GalleryHolder, position: Int) {
        val data = imageList[position]
        holder.setImage(data)
    }
}
