package br.com.rafaeldias.gym.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.rafaeldias.gym.R
import br.com.rafaeldias.gym.injection.module.NetworkModule
import br.com.rafaeldias.gym.model.Activity
import br.com.rafaeldias.gym.model.Gym
import br.com.rafaeldias.gym.network.GymApi
import kotlinx.android.synthetic.main.activity_check_in.*
import javax.inject.Inject
import org.json.JSONException
import org.json.JSONObject
import br.com.rafaeldias.gym.model.CheckInRes
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CheckInActivity : AppCompatActivity(), Callback<CheckInRes> {

    private var gym: Gym? = null
    private var activity: Activity? = null

    @Inject
    lateinit var gymApi: GymApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_in)

        gym = intent!!.getParcelableExtra<Gym>("id_gym")
        activity = intent!!.getParcelableExtra<Activity>("id_activity")

        var id_gym = gym!!.id
        var id_activity = activity!!.id

        tvIdGym.setText(id_gym!!.toString())
        tvIdActivity.setText(id_activity!!.toString())

        try {
            val paramObject = JSONObject()
            paramObject.put("gymId", id_gym)
            paramObject.put("activityId", id_activity)

            tvRequest.setText(paramObject.toString())

            val checkIn = NetworkModule.provideGymApi(NetworkModule.provideRetrofitInterface()).checkIn(paramObject.toString())
            checkIn.enqueue(this)
        } catch (e: JSONException) {
            Toast.makeText(this,R.string.checkin_error,Toast.LENGTH_LONG).show()
        }
    }

    override fun onResponse(call: Call<CheckInRes>, response: Response<CheckInRes>) {
        //Toast.makeText(this,response.toString(),Toast.LENGTH_LONG).show()
        tvResponse.setText(response.toString())
    }

    override fun onFailure(call: Call<CheckInRes>, t: Throwable) {
        Toast.makeText(this,R.string.checkin_error,Toast.LENGTH_LONG).show()
    }
}
