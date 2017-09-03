package com.rviannaoliveira.vchooseimage

import android.graphics.Bitmap
import com.google.api.services.vision.v1.Vision
import com.google.api.services.vision.v1.model.AnnotateImageRequest
import com.google.api.services.vision.v1.model.BatchAnnotateImagesRequest
import com.google.api.services.vision.v1.model.Feature
import com.google.api.services.vision.v1.model.Image
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.IOException
import java.util.*

/**
 * Criado por rodrigo on 03/09/17.
 */
object VisionApi {

    @Throws(IOException::class)
    fun callCloudVision(bitmap: Bitmap?, vision: Vision, visionView: MainView.VisionView) {
        bitmap?.let {
            val batchRequest = loadVisionParameters(it)
            Observable.fromCallable({ vision.images().annotate(batchRequest).execute() })
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ batchResponse ->
                        visionView.showModalDetail(batchResponse.responses[0].labelAnnotations)
                    })
        }

    }

    private fun loadVisionParameters(bitm: Bitmap): BatchAnnotateImagesRequest {
        val base64EncodedImage = Image()
        base64EncodedImage.content = bitm.compressForVision()

        val annotateImageRequest = AnnotateImageRequest()
        annotateImageRequest.image = base64EncodedImage

        val desiredFeature = Feature()
        desiredFeature.type = "LABEL_DETECTION"

        val request = AnnotateImageRequest()
        request.image = base64EncodedImage
        request.features = Arrays.asList(desiredFeature)

        val batchRequest = BatchAnnotateImagesRequest()
        batchRequest.requests = Arrays.asList(request)

        return batchRequest
    }

}