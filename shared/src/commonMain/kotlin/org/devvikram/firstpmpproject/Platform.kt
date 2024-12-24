package org.devvikram.firstpmpproject

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform