package org.techinowave.domain.model


data class FoodMenuItem(

    val id: Int,

    val createdAt: String,

    val itemName: String,

    val rating: Float,

    val imgUrl: String,

    val price: Int

)
