package com.example.telegram.ui.screens.groups

import androidx.recyclerview.widget.RecyclerView
import com.example.telegram.R
import com.example.telegram.database.*
import com.example.telegram.models.CommonModel
import com.example.telegram.ui.screens.base.BaseFragment
import com.example.telegram.utilits.*
import kotlinx.android.synthetic.main.fragment_add_contacts.*
import kotlinx.android.synthetic.main.fragment_create_group.*

class CreateGroupFragment(private var listContacts:List<CommonModel>):BaseFragment(R.layout.fragment_create_group) {
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: AddContactsAdapter

    override fun onResume() {
        super.onResume()
        APP_ACTIVITY.title = "Создать группу"
        hideKeyboard()
        initRecyclerView()
        create_group_input_name.requestFocus()
       create_group_btn_complete.setOnClickListener {
           //TODO
       }
        create_group_counts.text = getPlurals(listContacts.size)
    }

    private fun initRecyclerView() {
        mRecyclerView = create_group_recycle_view
        mAdapter = AddContactsAdapter()
        mRecyclerView.adapter = mAdapter
        listContacts.forEach{
            mAdapter.updateListItems(it)
        }
    }
}