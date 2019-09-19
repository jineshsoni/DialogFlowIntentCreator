package dao

import java.io.Serializable

data class AllJson(
    val auto: Boolean = true,
    val conditionalResponses: List<Any> = ArrayList<Any>(),
    val contexts: List<Any> = ArrayList<Any>(),
    val events: List<Any> = ArrayList<Any>(),
    val fallbackIntent: Boolean = false,
//    val id: String,
    var name: String = "",
    val priority: Int = 500000,
    var responses: List<Response> = ArrayList<Response>(),
    val webhookForSlotFilling: Boolean = false,
    val webhookUsed: Boolean = false
): Serializable

data class Response(
    val affectedContexts: List<Any> = ArrayList(),
//    val defaultResponsePlatforms: DefaultResponsePlatforms,
    var messages: List<Message> = ArrayList<Message>(),
    var parameters: List<Parameter> = ArrayList<Parameter>(),
    val resetContexts: Boolean = false,
    val speech: List<Any> = ArrayList<Any>()
): Serializable


data class Parameter(
//    val id: String,
    var name: String = "",
    var dataType: String = if (name == "") "" else "@$name",
    var value: String = name,
    val isList: Boolean = false,
    val required: Boolean = false,
    val noInputPromptMessages: List<Any> = ArrayList<Any>(),
    val noMatchPromptMessages: List<Any> = ArrayList<Any>(),
    val outputDialogContexts: List<Any> = ArrayList<Any>(),
    val promptMessages: List<Any> = ArrayList<Any>()
): Serializable

data class Message(
    val condition: String = "",
    val lang: String = "en",
    var speech: String = "",
    val type: Int = 0
): Serializable

class DefaultResponsePlatforms(
): Serializable