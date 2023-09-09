package com.chetan.kotlindesignpatterns.creational

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

sealed class Country{

    object Canada : Country()
}
object Spain : Country()
class Greece(val prop : String) : Country()
data class USA(val prop : String) : Country()

class Currency( val code : String )

object CurrencyFactory{

    fun currencyForCountry(country: Country) : Currency =
             when(country){
                is Spain -> Currency("EUR")
                is USA -> Currency("USD")
                is Greece -> Currency("EUR")
                is Country.Canada -> Currency("CAD")
            }

}
class FactoryMethodTest {
    @Test
    fun currencyTest(){
        val greeceCurrency = CurrencyFactory.currencyForCountry(Greece("")).code
        val usaCurrency = CurrencyFactory.currencyForCountry(USA("")).code
        assertThat(greeceCurrency).isEqualTo("EUR")
        assertThat(usaCurrency).isEqualTo("USA")

    }
}