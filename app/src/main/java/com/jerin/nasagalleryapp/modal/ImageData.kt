package com.jerin.nasagalleryapp.modal

import org.json.JSONObject

data class ImageData(val json: JSONObject) {
    val title: String
        get() {
            return json.getString("name")
        }

    val explanation: String
        get() {
            return json.getString("explanation")
        }

    val url: String
        get() {
            return json.getString("url")
        }

    val hdUrl: String
        get() {
            return json.getString("hdurl")
        }

    val copyright: String?
        get() {
            return try {
                json.getString("copyright")
            } catch (e: Exception) {
                null
            }
        }

    val dateString: String
        get() {
            return json.getString("date")
        }

//    DateTime get date => DateTime.parse(_dateString);
//
//    String get formattedDate => DateFormat("dd MMMM yyyy").format(date);
}
