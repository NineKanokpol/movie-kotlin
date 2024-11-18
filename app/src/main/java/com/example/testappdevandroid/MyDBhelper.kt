package com.example.testappdevandroid

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import androidx.fragment.app.FragmentActivity
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.jvm.Throws

class MyDBhelper(context: Context) : SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        // this method is to check if table already exists
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }
    @Throws(SQLiteConstraintException::class)
    fun insertMovies(movies : Results): Boolean{
        val db = writableDatabase
        val values = ContentValues()
        values.put(DBContract.RecentMovies.COLUMN_ID_MOVIE, movies.id)
        values.put(DBContract.RecentMovies.COLUMN_MOVIE_NAME, movies.title)
        values.put(DBContract.RecentMovies.COLUMN_IMG, movies.posterPath)
        values.put(DBContract.RecentMovies.COLUMN_TIME_STAMP, movies.releaseDate)

        val newRowId = db.insert(DBContract.RecentMovies.TABLE_NAME,null,values)
        return true
    }
//    fun updateMovies(movies: Results){
//        val db = writableDatabase
//        val values = ContentValues()
//        values.put(DBContract.RecentMovies.COLUMN_ID_MOVIE, movies.id)
//        values.put(DBContract.RecentMovies.COLUMN_MOVIE_NAME, movies.title)
//        values.put(DBContract.RecentMovies.COLUMN_IMG, movies.posterPath)
//        values.put(DBContract.RecentMovies.COLUMN_TIME_STAMP, movies.releaseDate)
//        var whereClause = "movieId=?"
//        var whereArgs = {movies.id.toString()}
//        db.update(DBContract.RecentMovies.TABLE_NAME,values,whereClause,whereArgs)
//    }
    @SuppressLint("Range")
    fun readMovie(): ArrayList<Results>{
        val movies = ArrayList<Results>()
        val db = writableDatabase
        var cursor:Cursor? = null
        try{
            cursor = db.rawQuery("SELECT * FROM " + DBContract.RecentMovies.TABLE_NAME + " ORDER BY " + DBContract.RecentMovies.COLUMN_TIME_STAMP + " DESC LIMIT 6 "  ,null )
        } catch (e : SQLiteException){
            db.execSQL(SQL_CREATE_ENTRIES)
            return ArrayList()
        }
        var movieid : Int? = null
        var name : String
        var image: String
        if(cursor!!.moveToFirst()){
            while (cursor.isAfterLast == false){
                movieid = cursor.getInt(cursor.getColumnIndex(DBContract.RecentMovies.COLUMN_ID_MOVIE))
                name = cursor.getString(cursor.getColumnIndex(DBContract.RecentMovies.COLUMN_MOVIE_NAME))
                image = cursor.getString(cursor.getColumnIndex(DBContract.RecentMovies.COLUMN_IMG))

                movies.add(Results(id = movieid, title = name , posterPath = image))
                cursor.moveToNext()
            }
        }
        return movies
    }
    companion object{
        val DATABASE_VERSION = 1
        val DATABASE_NAME = "MoviesDatabaseTime.db"

        private val SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DBContract.RecentMovies.TABLE_NAME + " (" +
                    DBContract.RecentMovies.COLUMN_ID_MOVIE + " TEXT PRIMARY KEY," +
                    DBContract.RecentMovies.COLUMN_MOVIE_NAME + " TEXT," +
                    DBContract.RecentMovies.COLUMN_IMG + " TEXT, " +
                    DBContract.RecentMovies.COLUMN_TIME_STAMP + " TEXT)"

        private val SQL_DELETE_ENTRIES = " DROP TABLE IF EXISTS " + DBContract.RecentMovies.TABLE_NAME
    }
}