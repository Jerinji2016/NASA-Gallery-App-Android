package com.jerin.nasagalleryapp.providers

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class DataProviderTest {

    @Test
    fun validateData() {
        val dataProvider = DataProvider.getInstance()
        dataProvider.loadImagesTest()
        val images = dataProvider.images

        //  check if data is not empty
        assert(dataProvider.images.isNotEmpty()) {
            "No Image Items found"
        }

        var isSortedInDescending = true

        val iterator = images.iterator()
        val previous = iterator.next()


        while(iterator.hasNext()) {
            val current = iterator.next()

            if(previous.date.isAfter(current.date)) {
                continue
            } else {
                isSortedInDescending = false
                break
            }
        }

        //  check if latest image first
        assert(isSortedInDescending) {
            "Following data was not in descending order"
        }
    }
}