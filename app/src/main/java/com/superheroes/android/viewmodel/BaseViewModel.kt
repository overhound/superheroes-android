package com.superheroes.android.viewmodel

import androidx.annotation.MainThread
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel<S : Any>(initialState: S) : ViewModel() {
    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<S> = _state.asStateFlow()

    @MainThread
    protected fun setState(reducer: S.() -> S) {
        val currentState = _state.value
        val newState = currentState.reducer()
        if (newState != currentState) {
            _state.value = newState
        }
    }
}