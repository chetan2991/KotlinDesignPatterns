package com.chetan.kotlindesignpatterns.creational

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

interface DataSource

class DataBaseDataSource : DataSource

class NetworkDataSource : DataSource
abstract class DataSourceFactory{
    abstract fun makeDataSource() : DataSource
    companion object {
        inline fun <reified T : DataSource> createFactory() : DataSourceFactory =
            when(T::class){
                DataBaseDataSource::class -> DataBaseFactory()
                NetworkDataSource::class -> NetworkFactory()
                else -> throw  IllegalArgumentException()
            }
    }
}

class DataBaseFactory : DataSourceFactory(){
    override fun makeDataSource() = DataBaseDataSource()

}

class NetworkFactory : DataSourceFactory(){
    override fun makeDataSource() = NetworkDataSource()

}

class AbstractFactoryTest{
    @Test
    fun abstractFactoryTest(){
        val dataSourceFactory = DataSourceFactory.createFactory<DataBaseDataSource>()
        val dataSource = dataSourceFactory.makeDataSource()
        assertThat(dataSource).isInstanceOf(DataBaseDataSource::class.java)
    }
}