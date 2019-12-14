package com.roomapp.toolsloan.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.roomapp.toolsloan.R
import com.roomapp.toolsloan.dao.FriendDao
import com.roomapp.toolsloan.dao.LoanDao
import com.roomapp.toolsloan.dao.ToolDao
import com.roomapp.toolsloan.entity.Friend
import com.roomapp.toolsloan.entity.Loan
import com.roomapp.toolsloan.entity.Tool

@Database (entities = [Tool::class, Friend::class, Loan::class],version = 1)
abstract class LoanAppDatabase : RoomDatabase() {

    abstract fun toolDao(): ToolDao
    abstract fun friendDao(): FriendDao
    abstract fun loanDao(): LoanDao

    companion object{
        @Volatile private var INSTANCE: LoanAppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = INSTANCE ?: synchronized(LOCK){
            INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
        }

        fun getInstance(context: Context): LoanAppDatabase =
                INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, LoanAppDatabase::class.java, "loan.db")
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
//                        // moving to a new thread
//                        ioThread {
//                            getInstance(context).toolDao()
//                                .insertTools(PREPOPULATE_DATA)
//                        }
                        Thread(Runnable { prepopulateDb(context, getInstance(context)) }).start()
                    }
                })
                .build()

        val PREPOPULATE_DATA = listOf(
            Tool("Wrench", 6, R.drawable.ic_wrench),
            Tool("Cutter", 15, R.drawable.ic_cutter),
            Tool("Pliers", 12, R.drawable.ic_pliers),
            Tool("Screwdriver", 13, R.drawable.ic_screw),
            Tool("Welding machine", 3, R.drawable.ic_welding_tool),
            Tool("Welding glasses", 7, R.drawable.ic_welding_glass),
            Tool("Hammer", 4, R.drawable.ic_hammer),
            Tool("Measuring Tape", 9, R.drawable.ic_measuring_tape),
            Tool("Alan key set", 4, R.drawable.ic_allen),
            Tool("Air compressor", 2, R.drawable.ic_air_compressor))

        val PREPOPULATE_FRIEND = listOf(
            Friend("Brian"),
            Friend( "Luke"),
            Friend( "Letty"),
            Friend( "Shaw"),
            Friend( "Parker"))

        private fun prepopulateDb(context: Context, db: LoanAppDatabase) {
            db.toolDao().insertTools(PREPOPULATE_DATA)
            db.friendDao().insertFriend(PREPOPULATE_FRIEND)
        }
    }
}