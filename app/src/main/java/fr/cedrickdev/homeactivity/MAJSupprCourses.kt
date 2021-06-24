package fr.cedrickdev.homeactivity

interface MAJSupprCourses {

    fun modifyItem(itemUID: String, isDone: Boolean)
    fun onItemDelete(itemUID: String)

    
}