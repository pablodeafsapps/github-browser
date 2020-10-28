package org.deafsapps.android.githubbrowser.presentationlayer.utils

import java.text.SimpleDateFormat
import java.util.*

fun getCurrentTimestamp() =
    SimpleDateFormat("dd/M/yyyy hh:mm:ss", Locale.UK).format(Date())