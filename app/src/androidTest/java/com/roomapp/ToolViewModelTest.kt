package com.roomapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.roomapp.toolsloan.R
import com.roomapp.toolsloan.entity.Tool
import com.roomapp.toolsloan.repository.ToolsRepository
import com.roomapp.toolsloan.ui.tools.ToolsViewModel
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ToolViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var toolViewModel: ToolsViewModel
    lateinit var toolRepository: ToolsRepository
    val tool = Tool("Pipe", 5, R.drawable.ic_hammer)

    @Before
    fun setUp() {
        //toolRepository = ToolsRepository()
       // toolViewModel = ToolsViewModel()
        insertAuction() //in order to test the query, we directly insert it
    }

    private fun insertAuction() {
        toolRepository.insertTool(tool)
    }

    private fun removeAuction() {
        val auctionsToRemove = mutableListOf<Long>()
        auctionsToRemove.add(854L)
        //toolRepository.deleteAuctions(auctionsToRemove, false)
    }

//    @Test
//    fun queryAuctionById_WithAuctionValue() {
//        toolViewModel.queryAuctionById(auction.id)
//        toolViewModel.getAuction().observeOnce {
//            assertEquals(auction.id, it.id)
//        }
//    }
//
//    @Test
//    fun queryAuctionById_WithNoAuctionValue() {
//        removeAuction()
//        toolViewModel.queryAuctionById(auction.id)
//        toolViewModel.getAuction().observeOnce {
//            assertEquals(it, null)
//        }
//    }

    @After
    fun afterTest() {
        removeAuction()
    }

}