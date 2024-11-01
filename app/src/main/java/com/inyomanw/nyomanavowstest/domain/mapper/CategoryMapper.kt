package com.inyomanw.nyomanavowstest.domain.mapper

import com.inyomanw.nyomanavowstest.data.room.table.CategoryTable

fun String.toCategoryTable() : CategoryTable {
    return CategoryTable(
        id = 0,
        category = this
    )
}