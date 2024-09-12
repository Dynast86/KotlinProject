package data.model

import kotlinx.serialization.Serializable

@Serializable
data class UnitPrice(
    val currencyCode: String,
    val unitPrice: Double
)

@Serializable
data class SalesReportModel(
    val name: String,
    val unitPrice: List<UnitPrice>
)