package com.roomapp.toolsloan.ui.friends

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.roomapp.toolsloan.R
import com.roomapp.toolsloan.base.ScopeFragment
import com.roomapp.toolsloan.entity.Friend
import com.roomapp.toolsloan.ui.friend_details.FriendDetailsActivity
import kotlinx.android.synthetic.main.fragment_friends.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

const val USERNAME = "USER_NAME"

class FriendsFragment : ScopeFragment(), KodeinAware, OnItemClickListener {

    override fun onItemClicked(friend: Friend) {
        gotoFriendDetails(friend)
    }

    private fun gotoFriendDetails(friend: Friend) {
        val intent = Intent(requireContext(),FriendDetailsActivity::class.java)
        intent.putExtra(USERNAME,friend.friendName)
        startActivity(intent)
    }

    override val kodein by closestKodein()

    private val viewModelFactory: FriendViewModelFactory by instance()

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: FriendItemAdapter
    private var friendList: List<Friend> = ArrayList()

    companion object {
        fun newInstance() = FriendsFragment()
    }

    private lateinit var viewModel: FriendsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_friends, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this,viewModelFactory).get(FriendsViewModel::class.java)

        linearLayoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL ,false)
        friendsRecycler.layoutManager = linearLayoutManager
        adapter = FriendItemAdapter(this)
        friendsRecycler.adapter = adapter

        bindUI()

    }

    private fun bindUI() = launch {
        val toolList = viewModel.friendList.await()

        toolList.observe(this@FriendsFragment, Observer {
            if(it == null) return@Observer

            adapter.swapData(it)
        })
    }

}
