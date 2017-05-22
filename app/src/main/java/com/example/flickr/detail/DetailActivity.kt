package com.example.flickr.detail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.data.images.ImageLoader
import com.example.domain.models.PhotoDetail
import com.example.flickr.FlickrApplication
import com.example.flickr.R
import kotlinx.android.synthetic.main.activity_image_detail.*
import javax.inject.Inject

class DetailActivity : AppCompatActivity(), DetailContract.View {

    companion object {

        val PHOTO_ID = "photoId"
        val URL = "url"

    }

    @Inject
    lateinit var presenter: DetailContract.Presenter
    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_detail)
        setSupportActionBar(toolbar)
        setupInjection()
        val photoId = intent.getStringExtra(PHOTO_ID)
        presenter.loadPhotoData(photoId)
        val url = intent.getStringExtra(URL)
        loadImage(url)
    }

    override fun onResume() {
        super.onResume()
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onDestroy() {
        super.onDestroy()
        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    override fun loadData(detail: PhotoDetail) {
        textViewTitle.text = detail.title
        textViewDescription.text = detail.description
        textViewTaken.text = detail.takenDate.toString()
        textViewPublished.text = detail.publishedDate.toString()
        textViewLicense.text = detail.license
        textViewViews.text = detail.views.toString()
        textViewLocation.text = detail.location
        textViewUser.text = detail.user
        textViewName.text = detail.name
    }

    override fun showError() {
        Toast.makeText(this, getString(R.string.detail_error), Toast.LENGTH_LONG).show()
    }

    override fun getContext() = this

    private fun setupInjection() {
        (application as FlickrApplication).getDetailComponent(this).inject(this)
    }

    private fun loadImage(url: String) {
        imageLoader.load(imageView, url)
    }


}