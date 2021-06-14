package com.example.telegram.ui.message_recycler_view.view_holders

import com.example.telegram.ui.message_recycler_view.views.MessageView

interface MessageHolder {
    fun drawMessage(view: MessageView)
    fun omAttach(view: MessageView)
    fun onDetach()
}
