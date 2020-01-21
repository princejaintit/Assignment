package ircon.org.assignment.ui.home.adaptor

import android.app.Activity
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import ircon.org.assignment.R
import ircon.org.assignment.databinding.TeamListItemBinding
import ircon.org.assignment.ui.home.model.CelebrityModel


class TeamAdaptor(private var activity: Activity, private val commiteeList: ArrayList<CelebrityModel>) :
        RecyclerView.Adapter<TeamAdaptor.MyViewHolder>() {


    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var model: CelebrityModel =commiteeList.get(position)
        holder.binding.committeVielModel=model




    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var layoutInflater: LayoutInflater = LayoutInflater.from(parent!!.context)

        val binding: TeamListItemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.team_list_item, parent, false)
        return MyViewHolder(binding)
    }


    override fun getItemCount(): Int {
        return commiteeList.size
    }


    inner class MyViewHolder(val binding: TeamListItemBinding) : RecyclerView.ViewHolder(binding.getRoot())



}