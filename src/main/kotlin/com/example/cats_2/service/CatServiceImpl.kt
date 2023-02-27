package com.example.cats_2.service

import com.example.cats_2.repository.CatRepository
import com.example.cats_2.dto.CatDto
import com.example.cats_2.utils.exceptions.CatException
import com.example.cats_2.utils.mapper.CatMapper
import org.springframework.stereotype.Service

@Service
class CatServiceImpl(
    private val catRepository: CatRepository,
    private val catMapper: CatMapper
) : CatService {
    override fun createCat(catDto: CatDto): CatDto {
        if (catDto.id != -1)
            throw CatException("Id must be null or -1.")

        // with cat id = 25
        val cat = catRepository.save(catMapper.toEntity(catDto))
        return catMapper.fromEntity(cat)
    }

    override fun getCats(): List<CatDto> {
        val cats = catRepository.getAllCats()

        if (cats.isEmpty())
            throw CatException("List of cats is empty.")

        return cats.map {
            catMapper.fromEntity(it)
        }
    }

    override fun getCat(id: Int): CatDto {
        val optionalCat = catRepository.findById(id)
        val cat = optionalCat.orElseThrow { CatException("Cat with id $id is not present") }
        return catMapper.fromEntity(cat)
    }

    override fun updateCat(catDto: CatDto): CatDto {
        val exists = catRepository.existsById(catDto.id)

        if (!exists)
            throw CatException("Cat with id ${catDto.id} is not present")

        if(catDto.name == "Default cat")
            throw CatException("Complete cat object is expected")

        catRepository.save(catMapper.toEntity(catDto))
        return catDto
    }

    override fun deleteCat(id: Int) {
        val exists = catRepository.existsById(id)

        if (!exists)
            throw CatException("Cat with id $id is not present")

        catRepository.deleteById(id)
    }
}