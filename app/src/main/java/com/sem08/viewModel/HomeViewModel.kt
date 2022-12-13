package com.sem08.viewModel

import android.app.Application
import androidx.lifecycle.*
import com.sem08.data.LugarDao
import com.sem08.data.lugarDatabase
import com.sem08.model.Lugar
import com.sem08.repository.LugarRepository
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: LugarRepository
    val obtenerLugares: LiveData<List<Lugar>>

    init {
        val lugarDao = lugarDatabase.getDatabase(application).lugarDao()
        repository = LugarRepository(lugarDao)
        obtenerLugares = repository.obtenerLugares
    }

    fun guardarLugar(lugar: Lugar){
        viewModelScope.launch { repository.guardarLugar(lugar) }

    }
    fun eliminarLugar(lugar: Lugar){
        viewModelScope.launch { repository.eliminarLugar(lugar) }

    }

}