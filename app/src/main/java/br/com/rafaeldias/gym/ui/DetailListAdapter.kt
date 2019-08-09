package br.com.rafaeldias.gym.ui

import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import br.com.rafaeldias.gym.R
import br.com.rafaeldias.gym.databinding.ItemDetailBinding
import br.com.rafaeldias.gym.model.Activity
import br.com.rafaeldias.gym.model.Gym

class DetailListAdapter: RecyclerView.Adapter<DetailListAdapter.ViewHolder>() {
    private lateinit var gymList: Gym
    private lateinit var detailList: List<Activity>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemDetailBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_detail, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(gymList,detailList[position])
    }

    override fun getItemCount(): Int {
        return if (::detailList.isInitialized) detailList.size else 0
    }

    fun updateDetailList(gymList: Gym, detailList: List<Activity>) {
        this.gymList = gymList
        this.detailList = detailList
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemDetailBinding) : RecyclerView.ViewHolder(binding.root) {
        private val viewModel = DetailViewModel()

        fun bind(gym: Gym, detail: Activity) {
            viewModel.bind(gym,detail)
            binding.viewModelDetail = viewModel
            binding.btCheckIn.setOnClickListener{
                val intent = Intent(binding.btCheckIn.context, CheckInActivity::class.java)
                intent.putExtra("id_gym", gym)
                intent.putExtra("id_activity", detail)
                binding.btCheckIn.context.startActivity(intent)
            }
        }

    }
}