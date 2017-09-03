package com.rviannaoliveira.vchooseimage

import android.graphics.Bitmap
import android.util.Base64
import java.io.ByteArrayOutputStream


/**
 * Criado por rodrigo on 03/09/17.
 */

fun Bitmap.compressForVision(): String {
    val byteStream = ByteArrayOutputStream()
    this.compress(Bitmap.CompressFormat.JPEG, 90, byteStream)
    return Base64.encodeToString(byteStream.toByteArray(), Base64.URL_SAFE)
}