package woowacourse.omok.data

import android.content.ContentValues
import android.content.Context

class OmokStorageController(
    context: Context,
) {
    private val omokDbHelper = OmokDbHelper(context)

    fun insertBoardCell(
        position: String,
        stone: String,
    ) {
        val values = ContentValues()
        values.put(COLUMN_NAME_POSITION, position)
        values.put(COLUMN_NAME_STONE, stone)

        omokDbHelper.writableDatabase.insert(TABLE_NAME, null, values)
    }

    fun getAllBoardCells(): List<Pair<String, String>> {
        val columns = arrayOf(COLUMN_NAME_POSITION, COLUMN_NAME_STONE)
        val cursor =
            omokDbHelper.readableDatabase.query(
                TABLE_NAME,
                columns,
                null,
                null,
                null,
                null,
                null,
            )

        val boardCells = mutableListOf<Pair<String, String>>()
        while (cursor.moveToNext()) {
            val title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_POSITION))
            val stone = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_STONE))
            boardCells.add(title to stone)
        }
        cursor.close()
        return boardCells
    }

    fun getLastStone(): String? {
        val columns = arrayOf(COLUMN_NAME_STONE)
        val cursor =
            omokDbHelper.readableDatabase.query(
                TABLE_NAME,
                columns,
                null,
                null,
                null,
                null,
                "rowid DESC",
                "1",
            )

        return if (cursor.moveToFirst()) {
            cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_STONE))
        } else {
            null
        }.also { cursor.close() }
    }

    fun close() {
        omokDbHelper.close()
    }

    companion object {
        const val TABLE_NAME = "omok"
        const val COLUMN_NAME_POSITION = "position"
        const val COLUMN_NAME_STONE = "stone"
    }
}
