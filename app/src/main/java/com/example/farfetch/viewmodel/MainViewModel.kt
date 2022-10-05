package com.example.farfetch.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.farfetch.entity.Hire
import com.example.farfetch.repo.Repository
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val itemsLiveData: MutableLiveData<Map<Int, List<Hire>>> by lazy { MutableLiveData<Map<Int, List<Hire>>>() }

    fun fetchItems() {
        viewModelScope.launch {
            try {
                val items = Repository.fetchAPI.getItems()
                val map = items.filter { !it.name.isNullOrEmpty() }
                    .sortedWith(compareBy<Hire> { it.listId }.thenBy { it.name })
                    .groupBy { it.listId }
                itemsLiveData.postValue(map)
            } catch (e: Throwable) {
                Log.e(MainViewModel::class.simpleName, e.message.toString())
            }
        }
    }

    fun getItemsLiveData(): LiveData<Map<Int, List<Hire>>> {
        return itemsLiveData
    }
}