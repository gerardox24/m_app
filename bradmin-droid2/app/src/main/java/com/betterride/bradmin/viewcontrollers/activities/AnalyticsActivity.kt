package com.betterride.bradmin.viewcontrollers.activities

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.betterride.bradmin.R
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import kotlinx.android.synthetic.main.activity_analytics.*
import kotlinx.android.synthetic.main.content_analytics.*


class AnalyticsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_analytics)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        var carTypes = listOf("light", "public", "heavy")
        var nCarTypes = listOf(20f, 70f, 12f)


        var entries = mutableListOf<PieEntry>()
        for (i in 0..2) {
            entries.add(PieEntry(nCarTypes[i], carTypes[i]))
        }

        var pieDataSet = PieDataSet(entries, "")
        pieDataSet.colors = ColorTemplate.MATERIAL_COLORS.toMutableList()
        pieDataSet.sliceSpace = 3f
        pieDataSet.selectionShift = 5f


        var pieData = PieData(pieDataSet)
        pieData.setValueTextSize(15f)
        pieData.setValueTextColor(Color.WHITE)

        carTypesPieChart.data = pieData
        carTypesPieChart.setUsePercentValues(true)
        carTypesPieChart.description.isEnabled = false
        carTypesPieChart.setExtraOffsets(5f, 0f, 5f, 5f)
        carTypesPieChart.isDrawHoleEnabled = true
        carTypesPieChart.legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
        carTypesPieChart.centerText = "Car Types"
        carTypesPieChart.setCenterTextSize(20f)
    }
}
