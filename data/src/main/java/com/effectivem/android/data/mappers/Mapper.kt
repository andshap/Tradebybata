package com.effectivem.android.data.mappers

interface Mapper<I, O> {

    fun map(input: I): O
}