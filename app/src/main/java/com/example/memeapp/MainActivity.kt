package com.example.memeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val imagee : ImageView = findViewById(R.id.memeimageView)
        loadMeme(imagee)
    }
       private fun loadMeme(imagee:ImageView){
// Instantiate the RequestQueue.
           val queue = Volley.newRequestQueue(this)
           val url = "https://meme-api.com/gimme"

// Request a string response from the provided URL.
           val jsonObjectRequest = JsonObjectRequest(
               Request.Method.GET, url, null,
               { response ->
                   val url = response.getString("url")
                   Glide.with(this).load(url).into(imagee)
               },
               {
               })

// Add the request to the RequestQueue.
           queue.add(jsonObjectRequest)
        }

    fun shareMeme(view: View) {}
    fun nextMeme(view: View) {}
}



