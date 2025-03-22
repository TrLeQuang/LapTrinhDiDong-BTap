package com.example.navigationapp.model

// Model dữ liệu cho mỗi item trong danh sách
data class ItemModel(
    val id: Int,
    val title: String,
    val content: String,
    val url: String = "https://www.example.com/item/$id"
)

// Tạo dữ liệu mẫu
object DataProvider {
    // Hàm tạo 1.000.000 items với cùng nội dung
    fun generateItems(count: Int = 1000000): List<ItemModel> {
        return List(count) { index ->
            ItemModel(
                id = index,
                title = "Item $index",
                content = "The only way to do great work is to love what you do.",
                url = "http://quotes.thegrandapblogs.com/"
            )
        }
    }
}