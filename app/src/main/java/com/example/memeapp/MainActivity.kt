package com.example.memeapp

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class MainActivity : AppCompatActivity() {
    private var currentImageurl : String? = null
  override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
      loadMeme()

    }
       private fun loadMeme(){
           val imagee : ImageView = findViewById(R.id.memeimageView)
           val progress : ProgressBar = findViewById(R.id.progressBar)
           progress.visibility = View.VISIBLE
// Instantiate the RequestQueue.
           val url = "https://meme-api.com/gimme"

// Request a string response from the provided URL.
           val jsonObjectRequest = JsonObjectRequest(
               Request.Method.GET, url, null,
               { response ->
                   currentImageurl = response.getString("url")

                   Glide.with(this).load(currentImageurl).listener(object:RequestListener<Drawable>{
                       override fun onLoadFailed(
                           e: GlideException?,
                           model: Any?,
                           target: Target<Drawable>?,
                           isFirstResource: Boolean
                       ): Boolean {
                           progress.visibility = View.GONE
                           TODO("Not yet implemented")
                       }

                       override fun onResourceReady(
                           resource: Drawable?,
                           model: Any?,
                           target: Target<Drawable>?,
                           dataSource: DataSource?,
                           isFirstResource: Boolean
                       ): Boolean {
                           progress.visibility = View.GONE
                           return false
                       }
                   }).into(imagee)
               },
               {
               })
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
// Add the request to the RequestQueue.
        }

    fun shareMeme(view: View) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "image/png"
        intent.putExtra(Intent.EXTRA_TEXT,"Hey, Checkout this cool meme I got from Reddit $currentImageurl")
        val chooser = Intent.createChooser(intent,"Share this meme using..")
        startActivity(chooser)
    }
    fun nextMeme(view: View) {
        loadMeme()
    }
}



