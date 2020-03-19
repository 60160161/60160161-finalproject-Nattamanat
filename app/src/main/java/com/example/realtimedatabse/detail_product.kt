package com.example.realtimedatabse

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.bumptech.glide.Glide


/**
 * A simple [Fragment] subclass.
 */
class detail_product : Fragment() {

    private var title:String?=null
    private var detail:String?=null
    private var image:String?=null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_detail_product, container, false)
        val layout_title =view?.findViewById<TextView>(R.id._title)
        val layout_detail =view?.findViewById<TextView>(R.id._det)
        val layout_image =view.findViewById<ImageView>(R.id.imageView)
        val button:Button = view.findViewById<Button>(R.id.history)


        layout_title?.text = this.title
        layout_detail?.text = this.detail

        Glide.with(this)
            .load(image)
            .into(layout_image)
        button!!.setOnClickListener {
            val builder: AlertDialog.Builder = AlertDialog.Builder(context)
            builder.setMessage("ต้องการทราบประวัติการเข้าชมมั้ย?")
            builder.setPositiveButton("ต้องการ",
                DialogInterface.OnClickListener { dialog, id ->
                    val _detail_product = ShowData()//.newInstance(holder.titleTextView.text.toString(),holder.detailTextView.text.toString(),send_img)
                    val frgMng = fragmentManager
                    val transaction : FragmentTransaction = frgMng!!.beginTransaction()
                    transaction.replace(R.id.contentContainer, _detail_product,"_detail_product")
                    transaction.addToBackStack("_detail_product")
                    transaction.commit()
                })
            builder.setNegativeButton("ไม่ต้องการ",
                DialogInterface.OnClickListener { dialog, which ->
                    //dialog.dismiss();
                })
            builder.show()

        }


        return view
    }

    fun newInstance(title: String,detail: String,image:String): detail_product {
        val fragment = detail_product()
        val bundle = Bundle()
        bundle.putString("title", title)
        bundle.putString("detail", detail)
        bundle.putString("image", image)

        fragment.setArguments(bundle)
        return fragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var bundle = arguments
        if (bundle != null) {
            title = bundle.getString("title").toString()
            detail = bundle.getString("detail").toString()
            image = bundle.getString("image").toString()

        }
    }

}
