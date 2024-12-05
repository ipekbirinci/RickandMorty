package com.example.rickandmorty.base

import android.view.MotionEvent

interface BaseFragmentHelper {
    fun onRecreatedView()
    fun onBeforeDispatchTouchEvent(ev: MotionEvent?)
}
