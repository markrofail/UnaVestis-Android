package org.markrofai.unavestis.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import org.markrofai.unavestis.R
import org.markrofai.unavestis.model.Category
import org.markrofai.unavestis.services.CategoryService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {


    companion object {
        var BaseUrl = "http://192.168.1.5:1234/api/v1/"
    }

    val retrofit = Retrofit.Builder()
        .baseUrl(BaseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private var categoryData: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        categoryData = findViewById(R.id.main_textView)
        findViewById<View>(R.id.main_button).setOnClickListener { getCurrentData() }
    }

    internal fun getCurrentData() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(CategoryService::class.java)
        val call = service.getAll()
        call.enqueue(object : Callback<List<Category>> {
            override fun onResponse(
                call: Call<List<Category>>,
                response: Response<List<Category>>
            ) {
                if (response.code() == 200) {
                    val categories = response.body()

                    val sb = StringBuilder()
                    if (categories != null) {
                        for (category in categories) {
                            sb.append(category.name + "\n")
                        }
                    }
                    categoryData!!.text = sb.toString()
                }
            }

            override fun onFailure(call: Call<List<Category>>, t: Throwable) {
                categoryData!!.text = t.message
            }
        })
    }
}
