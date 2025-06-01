package ph.theorangeplatform.camtime.ui.common

import com.example.composepokedex.data.handlers.LogHandler
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.Locale
import java.util.TimeZone

object DateAndTime {
    
    const val PATTERN_YEAR = "yyyy"
    const val PATTERN_MONTH = "MMM"
    const val PATTERN_MONTH_FULL = "MMMM"
    const val PATTERN_DAY_OF_MONTH = "dd"
    const val PATTERN_DAY_OF_WEEK = "EEEE"
    const val PATTERN_DATE = "dd/M/yyyy"
    const val PATTERN_DATE_TIME = "dd/M/yyyy hh:mm"
    const val PATTERN_TIME_24H = "HH:mm"
    const val PATTERN_SERVER_DATE = "yyyy-MM-dd"
    const val PATTERN_SERVER_DATE_TIME = "yyyy-MM-dd HH:mm:ss"
    const val PATTERN_START_WITH_MONTH = "MMM dd, yyyy"
    const val PATTERN_START_WITH_MONTH_NO_YEAR = "MMMM dd"
    const val PATTERN_START_WITH_DATE_NO_YEAR = "dd MMMM"
    const val PATTERN_START_WITH_MONTH_SHORT_NO_YEAR = "MMM dd"
    const val PATTERN_START_WITH_MONTH_WITH_TIME = "MMM dd, yyyy HH:mm:ss"
    const val PATTERN_START_WITH_MONTH_SMALL_NO_YEAR = "MMM dd"
    const val PATTERN_START_WITH_DAY_THEN_DATE = "MMM dd, yyyy"
    
    fun formatCurrentDate(pattern: String): String {
        val localDateTime = LocalDateTime.now()
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern))
    }
    
    fun formatDate(
        localDateTime: LocalDateTime,
        pattern: String
    ): String = localDateTime.format(DateTimeFormatter.ofPattern(pattern))
    
    fun formatDate(
        timeInMills: Long?,
        pattern: String
    ): String = LocalDateTime.ofInstant(Instant.ofEpochMilli(timeInMills ?: 0), ZoneId.of("UTC"))
        .format(DateTimeFormatter.ofPattern(pattern))
    
    fun getDayOfWeek(timestamp: Long): String {
        return SimpleDateFormat("EEEE", Locale.ENGLISH).format(timestamp * 1000) }
    
    fun getEpochFromDateAndTime(timeString: String, dateString: String): Long {
        val formatter = DateTimeFormatter.ofPattern(PATTERN_DATE)
        val date = LocalDate.parse(dateString, formatter)
        val time = LocalTime.parse(timeString)
        val dateTime = LocalDateTime.of(date, time)
        return dateTime.atZone(ZoneId.of("UTC")).toEpochSecond() * 1000
    }
    
    fun currentDateAndTimeToEpochMilli() = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli()
    
    
}

fun formatDateTime(time: Long): String {
    return try {
        val sdf = SimpleDateFormat("EEE, MMM dd, yyyy hh:mm a", Locale.getDefault())
        sdf.timeZone = TimeZone.getTimeZone("UTC")
        sdf.format(time)
    } catch (ex: Exception) {
        LogHandler.e(ex)
        ""
    }
}

object MeridiansEnum {
    const val AM = "AM"
    const val PM = "PM"
}