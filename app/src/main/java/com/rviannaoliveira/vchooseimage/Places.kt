package com.rviannaoliveira.vchooseimage

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Criado por rodrigo on 15/08/17.
 */

class Places {
    @Expose @SerializedName("place")
    var place: String? = null
    @Expose @SerializedName("url")
    var imageUrl: String? = null
    @Expose @SerializedName("country")
    var country: String? = null
}