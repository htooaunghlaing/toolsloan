package com.roomapp.toolsloan.ui.friend_details

import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.roomapp.toolsloan.R
import com.roomapp.toolsloan.entity.Friend
import com.roomapp.toolsloan.entity.LoanWithFriendAndTool
import com.roomapp.toolsloan.ui.friends.USERNAME
import com.google.gson.Gson
import com.hah.forecastmvvm.ui.base.ScopeActivity
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance
import timber.log.Timber

class FriendDetailsActivity : ScopeActivity(), KodeinAware,OnItemClickListener {

    override val kodein by closestKodein()

    private val friendDetailsViewModelFactory: FriendDetailsViewModelFactory by instance()

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: ToolsByFriendAdapter
    private var friendList: List<Friend> = ArrayList()

    public lateinit var friendName: String


    private lateinit var friendDetailsViewModel: FriendDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friend_details)
        setSupportActionBar(toolbar)

         friendName = intent.getStringExtra(USERNAME)


        val actionbar = supportActionBar

        actionbar!!.title = "$friendName loaned tools"
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)



        //init recycler
        linearLayoutManager = LinearLayoutManager(this@FriendDetailsActivity,LinearLayoutManager.VERTICAL ,false)
        toolsByFriendRecycler.layoutManager = linearLayoutManager
        adapter = ToolsByFriendAdapter(this)
        toolsByFriendRecycler.adapter = adapter

        friendDetailsViewModel = ViewModelProviders.of(this, friendDetailsViewModelFactory)
            .get(FriendDetailsViewModel::class.java)

        friendDetailsViewModel.name = friendName

        bindUI()

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun bindUI() = launch {
        val toolListByFriendName = friendDetailsViewModel.toolListbyFriendName.await()

        toolListByFriendName.observe(this@FriendDetailsActivity, Observer {
            if(it == null) return@Observer

            Timber.d("Loan Tools : %s", Gson().toJson(it))
            //Toast.makeText(this@FriendDetailsActivity, it.size.toString(), Toast.LENGTH_LONG).show()
            adapter.swapData(it)
        })
    }

    override fun onItemClicked(loanwithFriendAndTool: LoanWithFriendAndTool) {
        showToolReturnDialog(loanwithFriendAndTool)
    }

    private fun showToolReturnDialog(loanwithFriendAndTool: LoanWithFriendAndTool) {
// build alert dialog
        val dialogBuilder = AlertDialog.Builder(this)


        dialogBuilder.setMessage("Do you want to return ${loanwithFriendAndTool.toolName} ?")

            .setCancelable(false)

            .setPositiveButton(getString(R.string.str_ok)){dialog, which ->
                removeToolFromLoan(loanwithFriendAndTool.id)
                updateToolsAvailableCount(loanwithFriendAndTool.tool_id)

                dialog.dismiss()
            }

            .setNegativeButton(getString(R.string.str_cancel), DialogInterface.OnClickListener {
                    dialog, id -> dialog.cancel()
            })


        val alert = dialogBuilder.create()

        alert.setTitle("Return ${loanwithFriendAndTool.toolName}")

        alert.show()
    }

    private fun updateToolsAvailableCount(toolId: Int) {
        friendDetailsViewModel.updateAvailableCount(toolId)

    }

    private fun removeToolFromLoan(loanID: String) {
        friendDetailsViewModel.removeToolFromLoan(loanID)
    }

}
