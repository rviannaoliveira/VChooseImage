package com.rviannaoliveira.vchooseimage

import android.graphics.Bitmap
import com.google.api.services.vision.v1.model.EntityAnnotation

/**
 * Criado por rodrigo on 03/09/17.
 */
interface MainView {

    interface ScreenView {
        fun loadVision(bitmap: Bitmap?)
        fun sendSomeWhere()
    }

    interface VisionView {
        fun showModalDetail(list: MutableList<EntityAnnotation>)
    }
}