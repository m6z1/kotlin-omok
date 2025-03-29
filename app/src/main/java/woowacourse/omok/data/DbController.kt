package woowacourse.omok.data

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import woowacourse.omok.data.OmokContract.COLUMN_NAME_POSITION
import woowacourse.omok.data.OmokContract.COLUMN_NAME_STONE

class DbController(
    dbHelper: DbHelper,
) {
    private val writableDb: SQLiteDatabase = dbHelper.writableDatabase
    private val readableDb: SQLiteDatabase = dbHelper.readableDatabase

    fun updateBoardCell(
        position: String,
        stone: String,
    ) {
        val values = ContentValues()
        values.put(COLUMN_NAME_POSITION, position)
        values.put(COLUMN_NAME_STONE, stone)

        writableDb.insert(OmokContract.TABLE_NAME, null, values)
    }

    fun getAllBoardCells(): List<Pair<String, String>> {
        val columns = arrayOf(COLUMN_NAME_POSITION, COLUMN_NAME_STONE)
        val cursor =
            readableDb.query(
                OmokContract.TABLE_NAME,
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
            readableDb.query(
                OmokContract.TABLE_NAME,
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
}
