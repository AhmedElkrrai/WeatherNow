package com.example.core.presentation

import android.content.Context
import com.example.core.R
import com.example.core.domain.DataError

/**
 * Extension function for [DataError] to get a user-friendly error message.
 *
 * This function maps a [DataError] to a corresponding string.
 *
 * @param context The [Context] used to retrieve the localized string resource.
 * @return A user-friendly error message string.
 */
fun DataError.getMessage(context: Context): String {
    val resourceId = when (this) {
        is DataError.NoInternet -> R.string.error_no_internet
        is DataError.RequestTimeout -> R.string.error_request_timeout
        is DataError.Serialization -> R.string.error_serialization
        is DataError.ServerError -> R.string.error_unknown
        is DataError.TooManyRequests -> R.string.error_too_many_requests
        is DataError.LocalError -> R.string.local_data_error
        is DataError.GeneralError -> R.string.error_unknown
        is DataError.FailedToRetrieveData -> R.string.error_failed_to_retrieve_data
        is DataError.FailedToUpdateData -> R.string.error_failed_to_update_data
    }
    return context.getString(resourceId)
}
