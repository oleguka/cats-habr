package com.example.cats.catTests

import com.example.cats.BaseTest
import io.qameta.allure.Epic
import io.qameta.allure.Feature
import io.qameta.allure.Severity
import io.qameta.allure.SeverityLevel
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import com.example.cats.model.Cat
import com.example.cats.model.CatResponse
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import com.example.cats.steps.CatSteps.addCat
import com.example.cats.steps.CatSteps.deleteCat
import com.example.cats.steps.CatSteps.getCat
import com.example.cats.utils.extractAs

@Epic("Котик")
@Feature("Удаление котика")
class DeleteCatTest : BaseTest() {

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("После удаления котик отсутствует в реестре")
    fun deleteSimpleCat() {
        val cat = Cat(name = "semyon", breed = "III — BRI")
        val catResponse = addCat(cat).extractAs<CatResponse>()
        When {
            deleteCat(catResponse.id)
            getCat(catResponse.id)
        } Then {
            statusCode(400)
        }
    }

}