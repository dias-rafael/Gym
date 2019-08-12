package br.com.rafaeldias.gym.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.rafaeldias.gym.R
import br.com.rafaeldias.gym.injection.module.NetworkModule
import br.com.rafaeldias.gym.model.Activity
import br.com.rafaeldias.gym.model.CheckInRes
import br.com.rafaeldias.gym.model.Gym
import br.com.rafaeldias.gym.network.GymApi
import br.com.rafaeldias.gym.utils.Format
import kotlinx.android.synthetic.main.activity_check_in.*
import javax.inject.Inject
import org.json.JSONException
import com.google.gson.JsonObject
import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response
import java.lang.String.format
import java.text.SimpleDateFormat
import java.time.LocalDateTime.from
import java.time.format.DateTimeFormatter
import java.util.*

class CheckInActivity : AppCompatActivity(){

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

        try {

            val paramObject = JsonObject()
            paramObject.addProperty("gymId", id_gym)
            paramObject.addProperty("activityId", id_activity)

            val checkIn = NetworkModule.provideGymApi(NetworkModule.provideRetrofitInterface()).checkIn(paramObject)
            checkIn.enqueue(object: Callback<CheckInRes>{
                override fun onResponse(call: Call<CheckInRes>, response: Response<CheckInRes>) {
                    if (response.body() != null) {
                        if (response.body()!!.error == null) {
                            tvGym.setText(response.body()!!.gym!!.title.toString())
                            tvActivity.setText(response.body()!!.gym!!.activity!!.title.toString())
                            tvData.setText(response.body()!!.checkinDate.toString())
                            tvStatus.setText(response.body()!!.checkinStatus.toString())
                        } else {
                            tvStatus.setText(response.body()!!.error.toString())
                        }
                    } else {
                        Toast.makeText(this@CheckInActivity,R.string.checkin_error,Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<CheckInRes>, t: Throwable) {
                    Toast.makeText(this@CheckInActivity,R.string.checkin_error,Toast.LENGTH_LONG).show()
                }
            })

        } catch (e: JSONException) {
            Toast.makeText(this,R.string.checkin_error,Toast.LENGTH_LONG).show()
        }

    }


}
