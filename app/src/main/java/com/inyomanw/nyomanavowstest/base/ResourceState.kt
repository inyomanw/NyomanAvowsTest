package com.inyomanw.nyomanavowstest.base

sealed class ResourceState<T>(
    val data: T? = null,
    val errorMsg: String? = null
) {
    class Success<T>(data: T?) : ResourceState<T>(data = data)

    class Error<T>(msg: String) : ResourceState<T>(errorMsg = msg)

}