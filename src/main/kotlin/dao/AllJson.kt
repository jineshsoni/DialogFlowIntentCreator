package dao

data class AllJson(
    val auto: Boolean = true,
    val conditionalResponses: List<Any> = ArrayList<Any>(),
    val contexts: List<Any> = ArrayList<Any>(),
    val events: List<Any> = ArrayList<Any>(),
    val fallbackIntent: Boolean = false,
//    val id: String,
    var name: String = "",
    val priority: Int = 500000,
    val responses: List<Response> = ArrayList<Response>(),
    val webhookForSlotFilling: Boolean = false,
    val webhookUsed: Boolean = false
)

data class Response(
    val affectedContexts: List<Any>,
//    val defaultResponsePlatforms: DefaultResponsePlatforms,
    val messages: List<Message> = ArrayList<Message>(),
    val parameters: List<Parameter> = ArrayList<Parameter>(),
    val resetContexts: Boolean = false,
    val speech: List<Any> = ArrayList<Any>()
)


data class Parameter(
//    val id: String,
    var name: String = "",
    val dataType: String = if (name == "") "" else "@$name",
    val value: String = name,
    val isList: Boolean = false,
    val required: Boolean = false,
    val noInputPromptMessages: List<Any> = ArrayList<Any>(),
    val noMatchPromptMessages: List<Any> = ArrayList<Any>(),
    val outputDialogContexts: List<Any> = ArrayList<Any>(),
    val promptMessages: List<Any> = ArrayList<Any>()
)

data class Message(
    val condition: String = "",
    val lang: String = "en",
    var speech: String = "",
    val type: Int = 0
)

class DefaultResponsePlatforms(
)