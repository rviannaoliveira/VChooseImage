package com.rviannaoliveira.vchooseimage

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mindorks.placeholderview.SwipeDecor
import com.mindorks.placeholderview.SwipePlaceHolderView
import com.mindorks.placeholderview.SwipeViewBuilder
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


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

        rejectBtn.setOnClickListener { swipeView?.doSwipe(false) }
        acceptBtn.setOnClickListener { swipeView?.doSwipe(true) }
    }
}