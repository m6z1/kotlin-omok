package woowacourse.omok.data

import android.provider.BaseColumns

object OmokContract {
    const val TABLE_NAME = "omok"

    const val COLUMN_NAME_POSITION = "position"
    const val COLUMN_NAME_STONE = "stone"
    private const val COLUMN_NAME_ID = BaseColumns._ID

    const val SQL_CREATE_ENTRIES =
        "CREATE TABLE $TABLE_NAME (" +
            "$COLUMN_NAME_ID INTEGER PRIMARY KEY," +
            "$COLUMN_NAME_POSITION TEXT," +
            "$COLUMN_NAME_STONE TEXT)"

    const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS $TABLE_NAME"
}
