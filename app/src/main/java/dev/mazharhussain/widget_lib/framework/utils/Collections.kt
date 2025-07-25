package dev.mazharhussain.widget_lib.framework.utils

fun <K, V> Map<K, V>.asMap() = object : Map<K, V> by this {}
