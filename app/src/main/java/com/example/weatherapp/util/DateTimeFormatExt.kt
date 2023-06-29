package com.example.weatherapp.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
fun String.toLocalDateTime(): LocalDate =
    LocalDate.parse(this)