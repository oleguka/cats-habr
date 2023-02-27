package com.example.cats_2.repository

import com.example.cats_2.entity.Cat
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface CatRepository : CrudRepository<Cat, Int> {

    @Query("SELECT m FROM Cat as m")
    fun getAllCats(): List<Cat>

}