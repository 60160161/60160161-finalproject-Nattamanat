package com.example.realtimedatabse

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction
import com.bumptech.glide.Glide

/**
 * A simple [Fragment] subclass.
 */
class login : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        val button_log: Button = view.findViewById<Button>(R.id.login)
        button_log!!.setOnClickListener {
            val _detail_product = Recycler_view()//.newInstance(holder.titleTextView.text.toString(),holder.detailTextView.text.toString(),send_img)
            val frgMng = fragmentManager
            val transaction : FragmentTransaction = frgMng!!.beginTransaction()
            transaction.replace(R.id.contentContainer, _detail_product,"_detail_product")
            transaction.addToBackStack("_detail_product")
            transaction.commit()
        }

        return view
    }

}
