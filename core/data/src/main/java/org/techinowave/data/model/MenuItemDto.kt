package org.techinowave.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MenuItemDto(

    @SerialName("id")
    val id: Int,

    @SerialName("created_at")
    val createdAt: String,

    @SerialName("item_name")
    val itemName: String,

    @SerialName("rating")
    val rating: Float,

    @SerialName("image_url")
    val imgUrl: String,

    @SerialName("price")
    val price: Int,

)