package com.example.cats_2.service

import com.example.cats_2.dto.CatDto


interface CatService {


    fun createCat(catDto: CatDto): CatDto

    fun getCats(): List<CatDto>

    fun getCat(id: Int): CatDto

    fun updateCat(catDto: CatDto): CatDto

    fun deleteCat(id: Int)

}