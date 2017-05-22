package com.example.flickr.list

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.view.MenuItemCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.Menu
import com.example.domain.models.Photo
import com.example.flickr.FlickrApplication
import com.example.flickr.R
import com.example.flickr.detail.DetailActivity
import com.example.flickr.list.adapter.PhotoListAdapter
import com.example.flickr.list.listener.EndlessRecyclerViewScrollListener
import com.example.flickr.list.listener.RxSearch
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_list_images.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject


/**
 * Activity to list the photos
 */
class ListActivity : AppCompatActivity(), ListContract.View, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    lateinit var presenter: ListContract.Presenter
    @Inject
    lateinit var adapter: PhotoListAdapter
    lateinit var scrollListener: EndlessRecyclerViewScrollListener
    lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_images)
        setupInjection()
        setupRecyclerView()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.disposeAll()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.context, menu)
        val searchItem = menu.findItem(R.id.photoSearch)
        searchView = MenuItemCompat.getActionView(searchItem) as SearchView
        RxSearch.fromSearchView(searchView, searchItem)
                .debounce(1, TimeUnit.SECONDS)
                .filter { it.isNotBlank() }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { query ->
                            clearEntries()
                            presenter.searchEntries(1, query)
                        },
                        Throwable::printStackTrace,
                        {
                            clearEntries()
                            presenter.loadEntries(1)
                        }
                )
        return super.onCreateOptionsMenu(menu)
    }

    override fun onRefresh() {
        adapter.dataSet.clear()
        adapter.notifyDataSetChanged()
        presenter.loadEntries(1)
    }

    private fun setupInjection() {
        (application as FlickrApplication).getListComponent(this).inject(this)
    }

    private fun setupRecyclerView() {
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        layoutManager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
        imageList.layoutManager = layoutManager
        imageList.adapter = adapter
        presenter.loadEntries(1)
        swipeRefresh.setOnRefreshListener(this)
        scrollListener = object : EndlessRecyclerViewScrollListener(layoutManager) {

            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                if (searchView.isIconified) presenter.loadEntries(page)
                else presenter.searchEntries(page, searchView.query.toString())
            }

        }
        imageList.addOnScrollListener(scrollListener)
    }

    override fun showProgressIndicator(display: Boolean) {
        if (swipeRefresh.isRefreshing != display) swipeRefresh.isRefreshing = display
    }

    override fun showError(message: String) {
        Snackbar.make(coordinator, message, Snackbar.LENGTH_LONG)
                .setActionTextColor(Color.RED)
                .show()
    }

    override fun clearEntries() {
        adapter.dataSet.clear()
        adapter.notifyDataSetChanged()
    }

    override fun appendEntries(list: List<Photo>) {
        adapter.dataSet.addAll(list)
        adapter.notifyDataSetChanged()
        if (swipeRefresh.isRefreshing) swipeRefresh.isRefreshing = false
    }

    override fun goToEntryDetail(photoId: String, url: String) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.PHOTO_ID, photoId)
        intent.putExtra(DetailActivity.URL, url)
        startActivity(intent)
    }

    override fun getContext() = this

}