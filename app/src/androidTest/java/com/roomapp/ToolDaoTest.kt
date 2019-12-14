package com.roomapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.roomapp.toolsloan.R
import com.roomapp.toolsloan.dao.ToolDao
import com.roomapp.toolsloan.db.LoanAppDatabase

import com.roomapp.toolsloan.entity.Tool
import org.junit.*
import org.junit.rules.TestRule
import org.mockito.MockitoAnnotations
import org.mockito.Mock
import org.junit.runner.RunWith
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import org.junit.Rule


@RunWith(AndroidJUnit4::class)
class ToolDaoTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private var database: LoanAppDatabase? = null
    private var dao: ToolDao? = null

    @Mock
    private val observer: Observer<List<Tool>>? = null

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        val context = InstrumentationRegistry.getTargetContext()
        database = Room.inMemoryDatabaseBuilder(context, LoanAppDatabase::class.java!!)
            .allowMainThreadQueries().build()
        dao = database!!.toolDao()
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        database!!.close()
    }

    @Test
    @Throws(Exception::class)
    fun should_Insert_Food_Item() {
        val tool = Tool("Pipe", 5, R.drawable.ic_hammer)
        tool.toolId = 20
        dao?.insertTools(tool)
        val toolTest = getValue(dao?.getTool(tool.toolId)!!)
        Assert.assertEquals(tool.toolName, toolTest.toolName)
    }

//    @Test
//    @Throws(Exception::class)
//    fun insert() {
//        // given
//        val todo = Tool("Pipe", 5, R.drawable.ic_hammer)
//        dao!!.getAllTools().observeForever(observer)
//        // when
//        dao!!.insertTools(todo)
//        // then
//        verify(observer)?.onChanged(Collections.singletonList(todo))
//    }

    //from stackoverflow
    @Throws(InterruptedException::class)
    fun <T> getValue(liveData: LiveData<T>): T {
        val data = arrayOfNulls<Any>(1)
        val latch = CountDownLatch(1)
        val observer = object : Observer<T> {
            override fun onChanged(t: T?) {
                data[0] = t
                latch.countDown()
                liveData.removeObserver(this)//To change body of created functions use File | Settings | File Templates.
            }

        }
        liveData.observeForever(observer)
        latch.await(2, TimeUnit.SECONDS)

        return data[0] as T
    }
}