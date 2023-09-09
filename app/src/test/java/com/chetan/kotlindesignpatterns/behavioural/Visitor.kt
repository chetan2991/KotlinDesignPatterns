package com.chetan.kotlindesignpatterns.behavioural

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

interface ReportElement {
    fun <R> accept(visitor : ReportVisitor<R>) : R
}
class FixedPriceContract(val costPerYear : Long ): ReportElement{
    override fun <R> accept(visitor: ReportVisitor<R>): R {
        return visitor.visit(this)
    }

}
class TimeAndMaterialContract(val costPerHour : Long , val hour : Long ): ReportElement{
    override fun <R> accept(visitor: ReportVisitor<R>): R {
       return visitor.visit(this)
    }

}
class SupportContract(val costPerMonth : Long ) : ReportElement {
    override fun <R> accept(visitor: ReportVisitor<R>): R {
       return visitor.visit(this)
    }

}
interface ReportVisitor<out R >{
    fun visit( contract : FixedPriceContract ) : R
    fun visit( contract : TimeAndMaterialContract ) : R
    fun visit( contract : SupportContract ) : R
}

class MonthlyCostReportVisitor : ReportVisitor<Long>{
    override fun visit(contract: FixedPriceContract): Long {
        return contract.costPerYear / 12
    }

    override fun visit(contract: TimeAndMaterialContract): Long {
        return contract.costPerHour * contract.hour
    }

    override fun visit(contract: SupportContract): Long {
        return contract.costPerMonth
    }

}

class YearlyCostReportVisitor() : ReportVisitor<Long>{
    override fun visit(contract: FixedPriceContract): Long {
        return contract.costPerYear
    }

    override fun visit(contract: TimeAndMaterialContract): Long {
       return contract.costPerHour * contract.hour
    }

    override fun visit(contract: SupportContract): Long {
        return contract.costPerMonth * 12
    }

}
class VisitorTest{
    @Test
    fun testVisitor(){
        val projectAlpha = FixedPriceContract(10_000)
        val projectBeta = SupportContract(500)
        val projectGamma = TimeAndMaterialContract(150, 10)
        val projectKappa = TimeAndMaterialContract(50, 50)
        val project = arrayListOf( projectAlpha, projectBeta, projectGamma, projectKappa)

        val monthlyCostReportVisitor = MonthlyCostReportVisitor()
        val monthlyCost = project.map { it.accept(monthlyCostReportVisitor) }.sum()
        println("monthly cost: $monthlyCost")
        assertThat(monthlyCost).isEqualTo(5333)

        val yearlyCostReportVisitor = YearlyCostReportVisitor()
        val yearlyCost = project.map { it.accept(yearlyCostReportVisitor) }.sum()
        println("yearly cost: $yearlyCost")
        assertThat(yearlyCost).isEqualTo(20000)
    }
}