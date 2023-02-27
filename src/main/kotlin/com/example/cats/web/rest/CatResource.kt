package com.example.cats.web.rest

import com.example.cats.dto.CatDto
import com.example.cats.service.CatService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class CatResource(
    private val catService: CatService
) {

    @PostMapping("/cat")
    fun createCat(@RequestBody catDTO: CatDto): ResponseEntity<CatDto> {
        return ResponseEntity(catService.createCat(catDTO), HttpStatus.CREATED)
    }

    @GetMapping("/cats")
    fun getCats(): ResponseEntity<List<CatDto>> =
        ResponseEntity.ok(catService.getCats())

    @GetMapping("/cat/{id}")
    fun getCat(@PathVariable id: Int) =
        ResponseEntity.ok(catService.getCat(id))

    @PutMapping("/cat/{id}")
    fun updateCat(@RequestBody catDTO: CatDto): ResponseEntity<CatDto> =
        ResponseEntity.ok(catService.updateCat(catDTO))

    @DeleteMapping("/cat/{id}")
    fun deleteCat(@PathVariable id: Int): ResponseEntity<Unit> =
        ResponseEntity(catService.deleteCat(id), HttpStatus.NO_CONTENT)

}