package ru.netology.firstapp.dto

data class Post(
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    var likes: Int = 0,
    var share: Int = 0,
    val view: Int = 0,
    var likedByMe: Boolean = false,
    var postByShare: Boolean = false,

)