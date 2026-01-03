package com.plcoding.goodreads

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform