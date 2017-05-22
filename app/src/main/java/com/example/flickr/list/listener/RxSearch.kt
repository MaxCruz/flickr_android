package com.example.flickr.list.listener

import android.support.v4.view.MenuItemCompat
import android.support.v7.widget.SearchView
import android.view.MenuItem

import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

object RxSearch {

    fun fromSearchView(searchView: SearchView, searchItem: MenuItem): Observable<String> {
        val subject = BehaviorSubject.create<String>()
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String): Boolean {
                subject.onNext(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return true
            }
        })
        MenuItemCompat.setOnActionExpandListener(searchItem, object: MenuItemCompat.OnActionExpandListener {

            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                subject.onComplete()
                return true
            }

        })
        return subject
    }

}