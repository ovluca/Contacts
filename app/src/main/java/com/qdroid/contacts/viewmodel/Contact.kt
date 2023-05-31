package com.qdroid.contacts.viewmodel

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import java.net.URLDecoder
import java.net.URLEncoder

@JsonClass(generateAdapter = true)
data class Contact(
    val id: Int,
    val name: String,
    val imageUrl: String?,
    val nameInitials: String
)
/*The following code is a hack for sending data using compose navigation
* sending parcelable in arguments is an anti-pattern in compose, but I serialized the data class into string in order to be sent to the details screen.
* https://developer.android.com/guide/navigation/use-graph/pass-data#supported_argument_types
* */

val moshi: Moshi by lazy {
    Moshi.Builder().build()
}

val contactAdapter: JsonAdapter<Contact> by lazy {
    moshi.adapter(Contact::class.java)
}

fun Contact.toJson(): String {
    return URLEncoder.encode(contactAdapter.toJson(this), "UTF-8")
}

fun String.toContact(): Contact? {
    val decodedJson: String = URLDecoder.decode(this, "UTF-8")
    return contactAdapter.fromJson(decodedJson)
}
