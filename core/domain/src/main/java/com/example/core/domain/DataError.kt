package com.example.core.domain

/**
 * Represents different types of errors that can occur during
 * data operations allowing for exhaustive error handling.
 */
sealed interface DataError {

    /**
     * Represents a timeout error during a network request.
     */
    data object RequestTimeout : DataError

    /**
     * Represents an error when too many requests have been made to the server.
     */
    data object TooManyRequests : DataError

    /**
     * Represents an error when there is no internet connection.
     */
    data object NoInternet : DataError

    /**
     * Represents an error when the server returns an error response (5xx status code).
     */
    data object ServerError : DataError

    /**
     * Represents an error during the serialization or deserialization of data.
     */
    data object Serialization : DataError

    /**
     * Represents a general error that occurs during local data operations (e.g., database access).
     */
    data object LocalError : DataError

    /**
     * Represents an error when data retrieval fails.
     */
    data object FailedToRetrieveData : DataError

    /**
     * Represents an error when data update fails.
     */
    data object FailedToUpdateData : DataError

    /**
     * Represents an unknown or unexpected error.
     */
    data object GeneralError : DataError
}
