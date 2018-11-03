package vn.team.freechat_kotlin.data

import com.tvd12.ezyfoxserver.client.constant.EzyConstant
import com.tvd12.ezyfoxserver.client.entity.EzyObject
import vn.team.freechat_kotlin.constant.MessageType
import java.util.*

/**
 * Created by tavandung12 on 10/7/18.
 */

class MessageReceived : Message {

    var from: String? = null

    companion object {

        fun create(data: EzyObject) : MessageReceived {
            val answer = MessageReceived()
            answer.deserialize(data)
            return answer
        }

    }

    constructor() : super("") {
    }

    override fun deserialize(data: EzyObject) {
        super.deserialize(data)
        this.from = data.get<String>("from")
        this.sentTime = Date()
    }

    override fun getType(): EzyConstant {
        return MessageType.RECEIVED
    }
}
