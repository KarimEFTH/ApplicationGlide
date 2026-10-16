package com.example.applicationglide


import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

class MainActivity : AppCompatActivity() {

    // URLs d’exemples
    private val imageUrl: String = "https://images.unsplash.com/photo-1506744038136-46273834b3fb"
    private val gifUrl = "https://media.giphy.com/media/Ju7l5y9osyymQ/giphy.gif"
    private val preloadImageUrl = "https://images.unsplash.com/photo-1470770841072-f978cf4d019e"
    private val invalidImageUrl = "https://image.invalide.url/fake.png"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Récupération des ImageView depuis le layout
        val imageSimple = findViewById<ImageView>(R.id.imageSimple)
        val imageRounded = findViewById<ImageView>(R.id.imageRounded)
        val imageGif = findViewById<ImageView>(R.id.imageGif)
        val imageWithPlaceholder = findViewById<ImageView>(R.id.imageWithPlaceholder)

        // 1. Préchargement d’une image (mise en cache sans affichage direct)
        Glide.with(this@MainActivity)
            .load(preloadImageUrl)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .preload()

        // Affichage de l’image préchargée (instantané depuis le cache)
        Glide.with(this)
            .load(preloadImageUrl)
            .into(imageSimple)

        // 2. Chargement avec coins arrondis
        Glide.with(this)
            .load(imageUrl)
            .transform(CenterCrop(), RoundedCorners(30)) // Important : CenterCrop avec RoundedCorners
            .into(imageRounded)

        // 3. Chargement d’un GIF animé
        Glide.with(this)
            .asGif()
            .load(gifUrl)
            .into(imageGif)

        // 4. Chargement avec placeholder, erreur et redimensionnement
        Glide.with(this)
            .load(invalidImageUrl)
            .placeholder(R.drawable.loading) // doit être dans drawable/
            .error(R.drawable.error)         // doit être dans drawable/
            .override(600, 600)              // redimensionnement
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(imageWithPlaceholder)
    }
}

