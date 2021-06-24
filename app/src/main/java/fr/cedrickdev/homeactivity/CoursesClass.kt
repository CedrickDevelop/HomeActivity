package fr.cedrickdev.homeactivity

import android.bluetooth.le.BluetoothLeAdvertiser

class CoursesClass {

    companion object Factory {
        fun createList(): CoursesClass = CoursesClass()
    }

    // Variables des éléments de la liste de course
    // identifiant, l'élément, coché
    var UID: String? = null
    var itemDataText: String? = null
    var done: Boolean? = false
}