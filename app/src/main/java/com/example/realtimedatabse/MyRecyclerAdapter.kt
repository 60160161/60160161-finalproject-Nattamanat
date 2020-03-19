import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import com.bumptech.glide.Glide
import com.example.realtimedatabse.R
import com.example.realtimedatabse.detail_product
import com.google.firebase.database.FirebaseDatabase


class MyRecyclerAdapter(fragmentActivity: FragmentActivity, val dataSource: JSONArray) : RecyclerView.Adapter<MyRecyclerAdapter.Holder>() {

    private val thiscontext : Context = fragmentActivity.baseContext
    private val thisActivity = fragmentActivity

    class Holder(view : View) : RecyclerView.ViewHolder(view) {
        private val View = view;

        lateinit var layout : LinearLayout
        lateinit var titleTextView: TextView
        lateinit var titleTextView2: TextView
        lateinit var detailTextView: TextView

        lateinit var image: ImageView

        fun Holder(){

            layout = View.findViewById<View>(R.id.recyview_layout) as LinearLayout
            titleTextView = View.findViewById<View>(R.id.tv_name) as TextView
            titleTextView2 = View.findViewById<View>(R.id.tv_name2) as TextView
            detailTextView = View.findViewById<View>(R.id.tv_description) as TextView
            image = View.findViewById<View>(R.id.imgV) as ImageView


        }

    }

    override fun onCreateViewHolder(parent : ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.recy_layout, parent, false))
    }

    override fun getItemCount(): Int {
        return dataSource.length()
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {


        holder.Holder()
        holder.detailTextView.setText( dataSource.getJSONObject(position).getString("description").toString() )
        holder.titleTextView.setText( dataSource.getJSONObject(position).getString("title").toString() )
        Glide.with(thiscontext)
            .load(dataSource.getJSONObject(position).getString("image").toString())
            .into(holder.image)

        holder.layout.setOnClickListener{
            val send_img :String = dataSource.getJSONObject(position).getString("image").toString()
            //Toast.makeText(thiscontext,holder.titleTextView.text.toString(),Toast.LENGTH_SHORT).show()
            val _detail_product = detail_product().newInstance(holder.titleTextView.text.toString(),holder.detailTextView.text.toString(),send_img)
            val frgMng = thisActivity.supportFragmentManager
            val transaction : FragmentTransaction = frgMng!!.beginTransaction()
            transaction.replace(R.id.contentContainer, _detail_product,"_detail_product")
            transaction.addToBackStack("_detail_product")
            transaction.commit()

            val mRootRef = FirebaseDatabase.getInstance().getReference()

            //อ้างอิงไปที่ path ที่เราต้องการจะจัดการข้อมูล ตัวอย่างคือ users และ messages
            val mMessagesRef = mRootRef.child("messages")
            val mMessagesRef2 = mRootRef.child("data")
            val name :String = dataSource.getJSONObject(position).getString("title").toString()
            val key = mMessagesRef.push().key
            val postValues: HashMap<String, Any> = HashMap()
            postValues["username"] = name
            postValues["text"] = "my pet"

            val childUpdates: MutableMap<String, Any> = HashMap()

            childUpdates["$key"] = postValues

            mMessagesRef2.updateChildren(childUpdates)

        }




    }


}
