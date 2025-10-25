package com.example.fitnesstracker.ui

import android.graphics.Color
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.example.fitnesstracker.R
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun StatisticsChart(modifier: Modifier = Modifier, chartData: List<ChartData>) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            LineChart(context).apply chart@{
                description.isEnabled = false
                legend.isEnabled = true
                xAxis.position = XAxis.XAxisPosition.BOTTOM
                xAxis.setDrawGridLines(false)
                xAxis.granularity = 1f
                xAxis.valueFormatter = DateAxisFormatter(chartData.map { it.date.time })
                axisLeft.axisMinimum = 0f
                axisRight.isEnabled = false

                // Set the marker
                marker = CustomMarkerView(context, R.layout.marker_view).apply {
                    chartView = this@chart // Correctly reference the LineChart
                }
            }
        },
        update = { chart ->
            (chart.xAxis.valueFormatter as? DateAxisFormatter)?.dates = chartData.map { it.date.time }

            val entriesBurned = chartData.mapIndexed { index, data ->
                Entry(index.toFloat(), data.caloriesBurned.toFloat())
            }
            val entriesConsumed = chartData.mapIndexed { index, data ->
                Entry(index.toFloat(), data.caloriesConsumed.toFloat())
            }

            val burnedDataSet = LineDataSet(entriesBurned, "Verbraucht").apply {
                color = Color.BLUE
                setCircleColor(Color.BLUE)
                lineWidth = 2f
                setDrawValues(false) // Disable drawing values on the line
            }
            val consumedDataSet = LineDataSet(entriesConsumed, "Eingenommen").apply {
                color = Color.RED
                setCircleColor(Color.RED)
                lineWidth = 2f
                setDrawValues(false) // Disable drawing values on the line
            }

            chart.data = LineData(burnedDataSet, consumedDataSet)
            chart.invalidate()
        }
    )
}

private class DateAxisFormatter(var dates: List<Long>) : ValueFormatter() {
    private val formatter = SimpleDateFormat("d", Locale.GERMAN)
    override fun getFormattedValue(value: Float): String {
        return if (value.toInt() >= 0 && value.toInt() < dates.size) {
            formatter.format(dates[value.toInt()])
        } else ""
    }
}
