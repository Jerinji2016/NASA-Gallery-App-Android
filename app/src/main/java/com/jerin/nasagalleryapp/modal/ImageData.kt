package com.jerin.nasagalleryapp.modal

import org.json.JSONObject
import java.time.LocalDate

data class ImageData(val json: JSONObject) {
    val title: String
        get() {
            return json.getString("title")
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

    private val dateString: String
        get() {
            return json.getString("date")
        }

    val date: LocalDate
        get() {
            return LocalDate.parse(dateString)
        }

    override fun hashCode(): Int {
        return json.toString().hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ImageData

        if (json != other.json) return false

        return true
    }
}
