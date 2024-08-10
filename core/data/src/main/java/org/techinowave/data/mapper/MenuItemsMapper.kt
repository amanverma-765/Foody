package org.techinowave.data.mapper

import org.techinowave.data.model.MenuItemDto
import org.techinowave.domain.model.FoodMenuItem

object MenuItemsMapper {

    fun MenuItemDto.toMenuItem(): FoodMenuItem {
        return FoodMenuItem(
            id = id,
            createdAt = createdAt,
            imgUrl = imgUrl,
            itemName = itemName,
            price = price,
            rating = rating
        )
    }
}