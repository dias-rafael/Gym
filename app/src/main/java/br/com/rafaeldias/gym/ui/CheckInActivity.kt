package br.com.rafaeldias.gym.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.rafaeldias.gym.R
import br.com.rafaeldias.gym.model.Activity
import br.com.rafaeldias.gym.model.Gym
import kotlinx.android.synthetic.main.activity_check_in.*

class CheckInActivity : AppCompatActivity() {

    private var gym: Gym? = null
    private var activity: Activity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_in)

        gym = intent!!.getParcelableExtra<Gym>("id_gym")
        activity = intent!!.getParcelableExtra<Activity>("id_activity")

        var id_gym = gym!!.id.toString()
        var id_activity = activity!!.id.toString()

        tvIdGym.setText(id_gym)
        tvIdActivity.setText(id_activity)
    }
}
