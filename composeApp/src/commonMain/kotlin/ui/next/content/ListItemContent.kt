package ui.next.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import data.model.SalesReportModel

@Composable
fun ListItemContent(
    modifier: Modifier = Modifier,
    key: String,
    item: SalesReportModel
) {
    Row(
        modifier = modifier.fillMaxWidth().height(48.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = key,
            modifier = Modifier
        )
        Text(text = item.name)

        repeat(item.unitPrice.size) {
            Text(text = item.unitPrice[it].unitPrice.toString())
        }
    }
}