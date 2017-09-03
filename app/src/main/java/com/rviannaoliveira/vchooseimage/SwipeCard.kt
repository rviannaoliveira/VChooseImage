package com.rviannaoliveira.vchooseimage

import android.graphics.Bitmap
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.animation.GlideAnimation
import com.bumptech.glide.request.target.SimpleTarget
import com.mindorks.placeholderview.SwipePlaceHolderView
import com.mindorks.placeholderview.annotations.Click
import com.mindorks.placeholderview.annotations.Layout
import com.mindorks.placeholderview.annotations.Resolve
import com.mindorks.placeholderview.annotations.View
import com.mindorks.placeholderview.annotations.swipe.*

/**
 * Criado por rodrigo on 15/08/17.
 */

@Layout(R.layout.tinder_card_view)
internal class SwipeCard(private val profile: Places,
                         private val swipeView: SwipePlaceHolderView,
                         private val screenView: MainView.ScreenView) {

    @View(R.id.profileImageView)
    private val profileImageView: ImageView? = null

    @View(R.id.nameAgeTxt)
    private val nameAgeTxt: TextView? = null

    @View(R.id.locationNameTxt)
    private val locationNameTxt: TextView? = null

    private var bitMap: Bitmap? = null

    @Resolve
    private fun onResolved() {
        val load = Glide.with(VApplication.context).load(profile.imageUrl)
        load.into(profileImageView)
        load.asBitmap()
                .into(object : SimpleTarget<Bitmap>(100, 100) {
                    override fun onResourceReady(resource: Bitmap?, glideAnimation: GlideAnimation<in Bitmap>?) {
                        this@SwipeCard.bitMap = resource
                    }
                })
        nameAgeTxt?.text = profile.place
        locationNameTxt?.text = profile.country
    }

    @SwipeOut
    private fun onSwipedOut() {
        Log.d("EVENT", "onSwipedOut")
        swipeView.addView(this)
    }

    @SwipeCancelState
    private fun onSwipeCancelState() {
        Log.d("EVENT", "onSwipeCancelState")
    }

    @SwipeIn
    private fun onSwipeIn() {
        Log.d("EVENT", "onSwipedIn")
    }

    @SwipeInState
    private fun onSwipeInState() {
        Log.d("EVENT", "onSwipeInState")
    }

    @SwipeOutState
    private fun onSwipeOutState() {
        Log.d("EVENT", "onSwipeOutState")
    }

    @Click(R.id.profileImageView)
    private fun onClick() {
        Log.d("EVENT", "click")
        screenView.loadVision(bitMap)
    }
}
