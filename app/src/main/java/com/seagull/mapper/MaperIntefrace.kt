package com.seagull.mapper

interface MapperInterface<A, B> {
    fun transform(type: A): B
    fun transformToDB(type: B): A
}