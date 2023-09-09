package com.chetan.kotlindesignpatterns.structural

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

// third party functionality
data class DisplayDataType( val index : Float, val data : String )

class DataDisplay{
    fun displayData( displayDataType: DisplayDataType ){
        println("display data type ${displayDataType.data}")
    }
}


// our code functionality

data class DataBaseData( val position : Int, val amount  : Int)

class DataBaseDataGenerator(){
    fun generateData() : List<DataBaseData>{
        val databaseDataList = mutableListOf<DataBaseData>()
        databaseDataList.add(DataBaseData(2,10))
        databaseDataList.add(DataBaseData(3,10))
        databaseDataList.add(DataBaseData(4,10))
        databaseDataList.add(DataBaseData(5,10))
        return databaseDataList
    }
}

interface DataBaseDataConverter{
    fun convertData(data : List<DataBaseData>) : List<DisplayDataType>
}

class DataDisplayAdapter(val display: DataDisplay) : DataBaseDataConverter {
    override fun convertData(data: List<DataBaseData>): List<DisplayDataType> {
        val returnList = arrayListOf<DisplayDataType>()
        for ( datavalue in data ){
            val ddd = DisplayDataType(datavalue.position.toFloat(),datavalue.amount.toString())
            returnList.add(ddd)
            display.displayData(ddd)
        }
        return returnList
    }
}

class AdapterTest{
    @Test
    fun dataDisplayTest(){
        val dataGenerator = DataBaseDataGenerator()
        val generatedData = dataGenerator.generateData()
        val modifiedData  = DataDisplayAdapter(DataDisplay()).convertData(generatedData)
         assertThat(modifiedData.size).isEqualTo(4)
         assertThat(modifiedData.get(0).index).isEqualTo(2.0f)
         assertThat(modifiedData.get(0).data).isEqualTo("10")
    }
}