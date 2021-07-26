package com.ulvijabbarli.pronote.data.error

import java.io.IOException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

abstract class HandledException(
    cause: Throwable? = null,
    message: String? = null
) : IOException(message, cause)

class NetworkError(cause: Throwable?) : HandledException(cause)

class RemoteErrorMapper : ErrorMapper {

    override fun mapError(e: Throwable): Throwable = when (e) {
        is SocketException,
        is SocketTimeoutException,
        is UnknownHostException -> NetworkError(e)
        else -> throw Exception(e)
    }

}