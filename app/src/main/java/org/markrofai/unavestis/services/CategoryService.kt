package org.markrofai.unavestis.services

import org.markrofai.unavestis.model.Category
import retrofit2.Call
import retrofit2.http.GET

interface CategoryService {
    @GET("categories")
    fun getAll(): Call<List<Category>>
}

