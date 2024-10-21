package com.project.bonggong.model

data class Message(
    val text: String,
    val profileImageRes: Int? = null,// 이미지 리소스 ID
    val isUser: Boolean = true
)