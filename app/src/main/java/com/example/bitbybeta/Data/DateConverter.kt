import androidx.room.TypeConverter
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter


class Converters {
    private val formatter = DateTimeFormatter.ISO_ZONED_DATE_TIME

    @TypeConverter
    fun fromZonedDateTime(date: ZonedDateTime?): String? {
        return date?.format(formatter)
    }

    @TypeConverter
    fun toZonedDateTime(value: String?): ZonedDateTime? {
        return value?.let {
            return@let ZonedDateTime.parse(it, formatter)
        }
    }
}