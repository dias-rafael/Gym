package br.com.rafaeldias.gym.ui

import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import br.com.rafaeldias.gym.R
import br.com.rafaeldias.gym.databinding.ItemGymBinding
import br.com.rafaeldias.gym.model.Gym

class GymListAdapter: RecyclerView.Adapter<GymListAdapter.ViewHolder>() {
    private lateinit var gymList:List<Gym>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemGymBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_gym, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(gymList[position])
    }

    override fun getItemCount(): Int {
        return if(::gymList.isInitialized) gymList.size else 0
    }

    fun updateGymList(gymList:List<Gym>){
        this.gymList = gymList
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemGymBinding): RecyclerView.ViewHolder(binding.root){
        private val viewModel = GymViewModel()

        fun bind(gym:Gym){
            viewModel.bind(gym)
            binding.viewModel = viewModel
            binding.btListaGymMap.setOnClickListener{
                var endereco = binding.tvListaGymAddress.text.toString()
                var latitude = gym.location.latitude
                var longitude = gym.location.longitude
                var title = gym.title
                if (endereco != "" && endereco != null && latitude != 0.0 && latitude != null && longitude != 0.0 && longitude != null && title != "" && title != null) {
                    val intent = Intent(binding.btListaGymMap.context, MapsActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    intent.putExtra("endereco", endereco)
                    intent.putExtra("latitude", latitude)
                    intent.putExtra("longitude", longitude)
                    intent.putExtra("title", title)
                    binding.btListaGymMap.context.startActivity(intent)
                }
            }
            binding.btListaGymActivity.setOnClickListener{
                val intent = Intent(binding.btListaGymActivity.context, DetailListActivity::class.java)
                intent.putExtra("detalhe_gym", gym)
                binding.btListaGymActivity.context.startActivity(intent)
            }
        }

    }
}