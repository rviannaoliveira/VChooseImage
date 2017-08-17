package com.rviannaoliveira.vchooseimage

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.mindorks.placeholderview.SwipeDecor
import com.mindorks.placeholderview.SwipePlaceHolderView
import com.mindorks.placeholderview.SwipeViewBuilder

class MainActivity : AppCompatActivity() {

    private var swipeView: SwipePlaceHolderView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        swipeView = findViewById<SwipePlaceHolderView>(R.id.swipeView)

        val swipeDecor = SwipeDecor().apply {
            paddingTop = 20
            relativeScale = 0.01f
            swipeInMsgLayoutId = R.layout.swipe_in_msg_view
            swipeOutMsgLayoutId = R.layout.swipe_out_msg_view
        }

        swipeView?.run {
            getBuilder<SwipePlaceHolderView, SwipeViewBuilder<SwipePlaceHolderView>>()
                    .setDisplayViewCount(3)
                    .setSwipeDecor(swipeDecor)
            for (profile in Util.loadProfiles(this@MainActivity.applicationContext)) {
                swipeView?.addView(TinderCard(this@MainActivity, profile, this))
            }
        }

        val btnReject = findViewById<View>(R.id.rejectBtn)
        val btnAccept = findViewById<View>(R.id.acceptBtn)
        btnReject.setOnClickListener { swipeView?.doSwipe(false) }
        btnAccept.setOnClickListener { swipeView?.doSwipe(true) }
    }
}