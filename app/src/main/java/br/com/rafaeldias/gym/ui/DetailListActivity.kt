package br.com.rafaeldias.gym.ui

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import br.com.rafaeldias.gym.R
import br.com.rafaeldias.gym.databinding.ActivityDetailListBinding
import br.com.rafaeldias.gym.model.Gym
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_list.*

class DetailListActivity : AppCompatActivity() {

    private var detalhe_gym: Gym? = null

    private lateinit var binding: ActivityDetailListBinding
    private lateinit var viewModel: DetailListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_detail_list)

        detalhe_gym = intent!!.getParcelableExtra<Gym>("detalhe_gym")

        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_list)
        binding.rvDetalheGym.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        viewModel = ViewModelProviders.of(this).get(DetailListViewModel::class.java)
        viewModel.setGym(detalhe_gym!!)
        binding.viewModelDetail = viewModel

        var title_gym = detalhe_gym!!.title
        var address_gym = detalhe_gym!!.address
        var logo_gym = detalhe_gym!!.logo

        tvDetalheGym.setText(title_gym)
        tvDetalheEndereco.setText(address_gym)
        if(logo_gym != null) {
            Picasso.get()
                .load(logo_gym)
                .into(ivDetalheLogo)
        }
    }
}
