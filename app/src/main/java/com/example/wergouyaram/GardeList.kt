package com.example.wergouyaram

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wergouyaram.data.model.GardeListAdapter
import com.example.wergouyaram.data.model.Gardes
import com.example.wergouyaram.databinding.ActivityGardeListBinding
import com.example.wergouyaram.service.ApiService
import com.example.wergouyaram.service.GardeService
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response

class GardeList : AppCompatActivity() {

    private lateinit var binding: ActivityGardeListBinding
    private lateinit var gardeAdapter: GardeListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGardeListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageViewBack.setOnClickListener {
            finish()
        }


        binding.recycleView.layoutManager = LinearLayoutManager(this)
        fetchGardes()
    }

    private fun fetchGardes() {
        val retrofitService = ApiService.getApiService().create(GardeService::class.java)
        val responseLiveData: LiveData<Response<Gardes>> = liveData {
            val response = try {
                retrofitService.getGardes()
            } catch (e: Exception) {
                Response.error(500, "Erreur lors de la récupération des gardes.".toResponseBody())
            }
            emit(response)
        }
        responseLiveData.observe(this, Observer { response ->
            if (response.isSuccessful) {
                response.body()?.let { gardes ->
                    gardeAdapter = GardeListAdapter(gardes)
                    binding.recycleView.adapter = gardeAdapter
                }
            } else {
                Toast.makeText(this, "Erreur lors de la récupération des pharmacies.", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
