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
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import com.example.cats.steps.CatSteps.addCat
import com.example.cats.utils.extractAs


@Epic("Котик")
@Feature("Добавление котика")
class AddCatTest : BaseTest() {

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Проверка респонса после добавления котика в сервис")
    fun addSimpleCat() {
        val cat = Cat(name = "vasiliy", breed = "III — BEN")
        When {
            addCat(cat)
        } Then {
            statusCode(201)
            val catResponse = extractAs<CatResponse>()
            assertThat(catResponse.name).isEqualTo(cat.name)
            assertThat(catResponse.breed).isEqualTo(cat.breed)
        }
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Проверка респонса после добавления котика в сервис V2")
    fun addSimpleCatV2() {
        val cat = Cat(name = "vasiliy", breed = "III — BEN")
        val catResponse = addCat(cat).extractAs<CatResponse>()
        assertAll(
            { assertThat(catResponse.name).isEqualTo(cat.name) },
            { assertThat(catResponse.breed).isEqualTo(cat.breed) }
        )
    }

}