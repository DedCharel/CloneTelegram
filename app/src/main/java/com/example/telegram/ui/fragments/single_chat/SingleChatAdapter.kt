package com.example.telegram.ui.fragments.single_chat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.telegram.R
import com.example.telegram.models.CommonModel
import com.example.telegram.utilits.CURRENT_UID
import com.example.telegram.utilits.DiffUtilCalback
import com.example.telegram.utilits.asTime
import kotlinx.android.synthetic.main.message_item.view.*
import java.text.SimpleDateFormat
import java.util.*

class SingleChatAdapter:RecyclerView.Adapter<SingleChatAdapter.SingleChatHolder>() {

    private var mListMessageCash = emptyList<CommonModel>()
    private lateinit var mDiffResult:DiffUtil.DiffResult

    class SingleChatHolder(view: View):RecyclerView.ViewHolder(view){
        val blocUserMessage: ConstraintLayout = view.bloc_user_message
        val chatUserMessage:TextView = view.chat_user_message
        val chatUserMessageTime:TextView =view.chat_user_message_time

        val blocReceivedMessage: ConstraintLayout = view.bloc_received_message
        val chatReceivedMessage:TextView = view.chat_received_message
        val chatReceivedMessageTime: TextView = view.chat_received_message_time
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SingleChatHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.message_item,parent,false)
        return SingleChatHolder(view)
    }

    override fun onBindViewHolder(holder: SingleChatHolder, position: Int) {
        if (mListMessageCash[position].from == CURRENT_UID){
            holder.blocUserMessage.visibility = View.VISIBLE
            holder.blocReceivedMessage.visibility = View.GONE
            holder.chatUserMessage.text = mListMessageCash[position].text
            holder.chatUserMessageTime.text =
                mListMessageCash[position].timeStamp.toString().asTime()
        } else{
            holder.blocUserMessage.visibility = View.GONE
            holder.blocReceivedMessage.visibility = View.VISIBLE
            holder.chatReceivedMessage.text = mListMessageCash[position].text
            holder.chatReceivedMessageTime.text =
                mListMessageCash[position].timeStamp.toString().asTime()
        }
    }

    override fun getItemCount(): Int = mListMessageCash.size

    fun setList(list: List<CommonModel>){


        //notifyDataSetChanged()
    }

    fun addItem(item:CommonModel){
        val newList = mutableListOf<CommonModel>()
        newList.addAll(mListMessageCash)
        newList.add(item)
        mDiffResult = DiffUtil.calculateDiff(DiffUtilCalback(mListMessageCash,newList))
        mDiffResult.dispatchUpdatesTo(this)
        mListMessageCash = newList
    }
}


