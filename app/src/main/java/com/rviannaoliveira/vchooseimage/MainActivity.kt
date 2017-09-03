package com.rviannaoliveira.vchooseimage

import android.graphics.Bitmap
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import br.com.zap.imoveis.ui.fragments.DetailBottomSheetDialogFragment
import com.google.api.client.extensions.android.json.AndroidJsonFactory
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.services.vision.v1.Vision
import com.google.api.services.vision.v1.VisionRequestInitializer
import com.google.api.services.vision.v1.model.EntityAnnotation
import com.mindorks.placeholderview.SwipeDecor
import com.mindorks.placeholderview.SwipePlaceHolderView
import com.mindorks.placeholderview.SwipeViewBuilder
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView.ScreenView, MainView.VisionView {
    private lateinit var vision: Vision

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadUI()
        initVision()
    }

    private fun initVision() {
        val visionBuilder = Vision.Builder(NetHttpTransport(), AndroidJsonFactory(), null)
        visionBuilder.setVisionRequestInitializer(VisionRequestInitializer("AIzaSyDv6g4dzq6nyOC8_gqDcPsDslzQSK1pKwM"))
        vision = visionBuilder.build()
    }

    private fun loadUI() {
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
                swipeView?.addView(SwipeCard(profile, this, this@MainActivity))
            }
        }


        rejectBtn.setOnClickListener { swipeView?.doSwipe(false) }
        acceptBtn.setOnClickListener { swipeView?.doSwipe(true) }
    }


    override fun loadVision(bitmap: Bitmap?) {
        progressBar.visibility = View.VISIBLE
        VisionApi.callCloudVision(bitmap, vision, this@MainActivity)
    }

    override fun showModalDetail(list: MutableList<EntityAnnotation>) {
        progressBar.visibility = View.GONE
        val newInstance = DetailBottomSheetDialogFragment.newInstance(ArrayList(list.map { it.description }))
        newInstance.show(supportFragmentManager, newInstance.tag)
    }
}