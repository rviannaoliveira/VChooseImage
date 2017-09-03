package br.com.zap.imoveis.ui.fragments

import android.annotation.SuppressLint
import android.app.Dialog
import android.support.design.widget.BottomSheetDialogFragment
import android.view.View
import com.rviannaoliveira.vchooseimage.BasicAdapter
import com.rviannaoliveira.vchooseimage.R
import kotlinx.android.synthetic.main.fragment_detail_bottom.view.*

/**
 * Criado por rodrigo on 22/08/17.
 */

class DetailBottomSheetDialogFragment : BottomSheetDialogFragment() {
    companion object {
        private lateinit var list: ArrayList<String>
        @JvmStatic
        fun newInstance(list: ArrayList<String>): DetailBottomSheetDialogFragment {
            this.list = list
            return DetailBottomSheetDialogFragment()
        }
    }


    @SuppressLint("RestrictedApi")
    override fun setupDialog(dialog: Dialog, style: Int) {
        super.setupDialog(dialog, style)

        val contentView = View.inflate(context, R.layout.fragment_detail_bottom, null)
        contentView.list_details.adapter = BasicAdapter(list)
        dialog.setContentView(contentView)
    }
}
