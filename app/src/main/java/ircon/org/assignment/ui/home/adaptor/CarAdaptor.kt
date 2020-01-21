package ircon.org.assignment.ui.home.adaptor

import android.app.Activity
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import ircon.org.assignment.R
import ircon.org.assignment.databinding.CarListItemBinding
import ircon.org.assignment.databinding.TeamListItemBinding
import ircon.org.assignment.ui.home.model.CarModel

class CarAdaptor (private var activity: Activity, private val commiteeList: ArrayList<CarModel>) :
        RecyclerView.Adapter<CarAdaptor.MyViewHolder>() {


    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var model: CarModel =commiteeList.get(position)
        holder.binding.committeVielModel=model




    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var layoutInflater: LayoutInflater = LayoutInflater.from(parent!!.context)

        val binding: CarListItemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.car_list_item, parent, false)
        return MyViewHolder(binding)
    }


    override fun getItemCount(): Int {
        return commiteeList.size
    }


    inner class MyViewHolder(val binding: CarListItemBinding) : RecyclerView.ViewHolder(binding.getRoot())



}