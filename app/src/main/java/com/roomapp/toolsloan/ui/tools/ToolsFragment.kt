package com.roomapp.toolsloan.ui.tools

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.roomapp.toolsloan.entity.Tool
import com.roomapp.toolsloan.R
import com.roomapp.toolsloan.base.ScopeFragment
import com.roomapp.toolsloan.entity.Loan
import com.roomapp.toolsloan.ui.friends.FriendViewModelFactory
import com.roomapp.toolsloan.ui.friends.FriendsViewModel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.dialog_loan.view.*
import kotlinx.android.synthetic.main.fragment_tools.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance
import timber.log.Timber

class ToolsFragment : ScopeFragment(), KodeinAware ,OnItemClickListener{

    override val kodein by closestKodein()

    private val toolsViewModelFactory: ToolsViewModelFactory by instance()
    private val friendViewModelFactory: FriendViewModelFactory by instance()


    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: ToolItemAdapter
    private var forecastList: List<Tool> = ArrayList()

    companion object {
        fun newInstance() = ToolsFragment()
    }

    private lateinit var toolViewModel: ToolsViewModel
    private lateinit var friendViewModel: FriendsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tools, container, false)
    }

    override fun onItemClicked(tool: Tool) {
        //Toast.makeText(requireContext(),"Tool name ${tool.toolName} \n Count:${tool.totalCount}",Toast.LENGTH_LONG).show()
        showLoanDialog(tool)
    }

    private fun showLoanDialog(tool: Tool) = launch {
        Timber.d("Tool : %s", Gson().toJson(tool))
        val mDialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_loan, null)
        //AlertDialogBuilder
        val mBuilder = AlertDialog.Builder(requireContext())
            .setView(mDialogView)
            .setTitle("Loan Form")


        var friId:Int = 0

        val frinedList = friendViewModel.friendList.await()
        var friendAdapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_item)

        frinedList.observe(this@ToolsFragment, Observer { mFriendList ->
            mFriendList?.forEach {
                friendAdapter.add(it.friendName)
            }
            mDialogView.spnFriend.adapter = friendAdapter

            mDialogView.spnFriend.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adapterView: AdapterView<*>, view: View, i: Int, l: Long) {
                    friId = mFriendList[i].friendID
                    //Toast.makeText(requireContext(), mFriendList[i].friendName, Toast.LENGTH_SHORT).show()
                }
                override fun onNothingSelected(adapterView: AdapterView<*>) {
                }
            }
        })

        val  mAlertDialog = mBuilder.show()

        mDialogView.dialogLoginBtn.setOnClickListener {
           insertLoan(tool.toolId, friId)
           updateToolsCount(tool)

           mAlertDialog.dismiss()
        }

        mDialogView.dialogCancelBtn.setOnClickListener {
            mAlertDialog.dismiss()
        }

    }

    private fun updateToolsCount(tool: Tool) {
        Timber.e("Tool count %d Total Count %d", tool.count,tool.totalCount)
        if(tool.count < tool.totalCount){
            toolViewModel.updateToolsCount(tool)
        }else if(tool.count == tool.totalCount){
            Toast.makeText(requireContext(), tool.toolName + " has not left in inventory", Toast.LENGTH_LONG).show()
        }


    }

    private fun insertLoan(toolId: Int, friId: Int){
        toolViewModel.insertLoan(Loan(toolId.toString(),friId.toString()))

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        linearLayoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL ,false)
        toolsRecycler.layoutManager = linearLayoutManager
        toolsRecycler.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        adapter = ToolItemAdapter(this)
        toolsRecycler.adapter = adapter

        toolViewModel = ViewModelProviders.of(this, toolsViewModelFactory)
            .get(ToolsViewModel::class.java)

        friendViewModel = ViewModelProviders.of(this, friendViewModelFactory)
            .get(FriendsViewModel::class.java)


        bindUI()


    }

    private fun bindUI() = launch {
        val toolList = toolViewModel.toolList.await()

        toolList.observe(this@ToolsFragment, Observer {
            if(it == null) return@Observer

            Timber.i("Tool List : %s", Gson().toJson(it))
            //Toast.makeText(requireContext(), "Some changes in DB : ", Toast.LENGTH_LONG).show()


            adapter.swapData(it)
        })
    }

}
