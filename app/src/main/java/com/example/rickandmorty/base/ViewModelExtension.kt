package com.example.rickandmorty.base


import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

fun <CLS> Class<*>.findGenericWithType(targetClass: Class<*>): Class<out CLS>? {
    var currentType: Type? = this

    while (true) {
        val answerClass =
            (currentType as? ParameterizedType)?.actualTypeArguments
                ?.mapNotNull { it as? Class<*> }
                ?.findLast { targetClass.isAssignableFrom(it) }

        if (answerClass != null) {
            @Suppress("UNCHECKED_CAST")
            return answerClass as Class<out CLS>?
        }

        currentType = when (currentType) {
            is Class<*> -> currentType.genericSuperclass
            is ParameterizedType -> currentType.rawType
            else -> return null
        }
    }
}
