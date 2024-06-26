package eu.sumindlift.mindlift.ui.util

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import co.yml.charts.axis.AxisData
import co.yml.charts.common.model.Point
import co.yml.charts.ui.linechart.LineChart
import co.yml.charts.ui.linechart.model.GridLines
import co.yml.charts.ui.linechart.model.IntersectionPoint
import co.yml.charts.ui.linechart.model.Line
import co.yml.charts.ui.linechart.model.LineChartData
import co.yml.charts.ui.linechart.model.LinePlotData
import co.yml.charts.ui.linechart.model.LineStyle
import co.yml.charts.ui.linechart.model.LineType
import co.yml.charts.ui.linechart.model.SelectionHighlightPoint
import co.yml.charts.ui.linechart.model.SelectionHighlightPopUp
import co.yml.charts.ui.linechart.model.ShadowUnderLine
import eu.sumindlift.mindlift.R
import eu.sumindlift.mindlift.data.entity.EnergyLevelRecord
import eu.sumindlift.mindlift.ui.theme.backgroundLight
import eu.sumindlift.mindlift.ui.viewmodel.EnergyLevelProgressViewModel

@Composable
fun EnergyLevelProgress(
    modifier: Modifier,
    viewModel: EnergyLevelProgressViewModel = hiltViewModel(),
    navController: NavController
) {
    Column(
        modifier = modifier.fillMaxHeight()
    ) {
        LaunchedEffect(Unit) {
            viewModel.loadLatestEnergyLevelRecords(10)
        }
        val energyLevelRecords = viewModel.energyLevelRecords.collectAsState()

        Text(
            fontWeight = FontWeight.Bold,
            text = stringResource(R.string.energy_level_record_progress),
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
        )

        LineChart(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp),
            lineChartData = getEnergyLevelLineChartData(energyLevelRecords.value)
        )
    }
}

@Composable
fun getEnergyLevelLineChartData(energyLevelRecords: List<EnergyLevelRecord>): LineChartData {
    val steps = 2
    val pointsData = transformEnergyLevelRecordsToPoints(energyLevelRecords)
    val xAxisData = AxisData.Builder()
        .axisStepSize(90.dp)
        .backgroundColor(backgroundLight)
        .steps(pointsData.size - 1)
        .labelData { i ->
            if (energyLevelRecords.isEmpty()) {
                ""
            } else {
                energyLevelRecords[i].timestamp?.substring(5) ?: ""
            }
        }
        .labelAndAxisLinePadding(15.dp)
        .axisLineColor(MaterialTheme.colorScheme.tertiary)
        .axisLabelColor(MaterialTheme.colorScheme.tertiary)
        .build()
    val yAxisData = AxisData.Builder()
        .steps(steps)
        .backgroundColor(backgroundLight)
        .labelAndAxisLinePadding(20.dp)
        .labelData { i ->
            val yScale = 100 / steps
            (i * yScale).toString()
        }
        .axisLineColor(MaterialTheme.colorScheme.tertiary)
        .axisLabelColor(MaterialTheme.colorScheme.tertiary)
        .build()
    return LineChartData(
        linePlotData = LinePlotData(
            lines = listOf(
                Line(
                    dataPoints = pointsData,
                    LineStyle(
                        lineType = LineType.Straight()
                    ),
                    IntersectionPoint(),
                    SelectionHighlightPoint(),
                    ShadowUnderLine(),
                    SelectionHighlightPopUp()
                )
            ),
        ),
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        gridLines = GridLines(),
        backgroundColor = backgroundLight
    )
}

fun transformEnergyLevelRecordsToPoints(energyLevelRecords: List<EnergyLevelRecord>): List<Point> {
    if (energyLevelRecords.isEmpty()) {
        return listOf(Point(0f, 0f))
    }
    return energyLevelRecords.mapIndexed { i, energyLevelRecord ->
        Point(i.toFloat(), energyLevelRecord.level?.toFloat() ?: 0f)
    }
}

@Preview
@Composable
fun EnergyLevelProgressPreview() {
    EnergyLevelProgress(modifier = Modifier, navController = rememberNavController())
}