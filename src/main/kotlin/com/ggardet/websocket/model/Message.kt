package com.ggardet.websocket.model

import com.ggardet.websocket.utils.DateUtils

data class Message(
    var from: String?,
    var text: String?,
    var recipient: String?,
    var time: String? = DateUtils.getTime()
)
