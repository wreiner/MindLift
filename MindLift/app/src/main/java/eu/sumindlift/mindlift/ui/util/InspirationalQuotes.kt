package eu.sumindlift.mindlift.ui.util

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import eu.sumindlift.mindlift.R
import eu.sumindlift.mindlift.ui.viewmodel.InspirationalQuotesViewModel

@Composable
fun InspirationalQuotes(
    modifier: Modifier = Modifier,
    navController: NavController,
    inspirationalQuotesViewModel: InspirationalQuotesViewModel = hiltViewModel()
) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        LaunchedEffect(Unit) {
            inspirationalQuotesViewModel.loadQuotes()
        }
        val quote by inspirationalQuotesViewModel.quote.collectAsState()
        Text(
            fontWeight = FontWeight.Bold,
            text = stringResource(R.string.inspirational_quote),
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            contentAlignment = Alignment.Center
        ) {
            Column{
                if (quote.text.isNotBlank()) {
                    Text(
                        text = "\"${quote.text}\"",
                        fontStyle = FontStyle.Italic,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                if (quote.text.isNotBlank()) {
                    Text(
                        text = "- ${quote.author.replace(", type.fit", "")}",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}