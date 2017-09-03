package com.rviannaoliveira.vchooseimage

import android.app.Application

/**
 * Criado por rodrigo on 03/09/17.
 */

class VApplication : Application() {

    companion object {
        lateinit var context: VApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }
}