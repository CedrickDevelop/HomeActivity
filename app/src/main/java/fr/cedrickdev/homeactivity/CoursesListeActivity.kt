package fr.cedrickdev.homeactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_courses_liste.*
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.liste_elements_course.*

class CoursesListeActivity : AppCompatActivity(), MAJSupprCourses {

    lateinit var database: DatabaseReference
    lateinit var adapter: CoursesAdapter
    private var listViewItem : ListView? = null
    private var ToDOList: MutableList<CoursesClass>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_courses_liste)

        database =
            FirebaseDatabase.getInstance("https://homeactivity-26613-default-rtdb.europe-west1.firebasedatabase.app").reference

        btnAjoutElementTouched()
    }

    private fun btnAjoutElementTouched() {

        btn_AjoutElementsListe.setOnClickListener {
            val textEditText = text_ElementAjout.editText?.text.toString()
            val todoItemData = CoursesClass.createList()
            todoItemData.itemDataText = textEditText
            todoItemData.done = false
            val email = FirebaseAuth.getInstance().currentUser?.email.toString()
            val newItemData = database.child("ListeCourse").push()
            todoItemData.UID = newItemData.key

            newItemData.setValue(todoItemData)

            Toast.makeText(this@CoursesListeActivity, "$textEditText ajouté", Toast.LENGTH_LONG)
                .show()


        }

        ToDOList = mutableListOf<CoursesClass>()
        adapter = CoursesAdapter(this, ToDOList!!)
        listViewItem?.adapter = adapter

        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                ToDOList!!.clear()
                addItemToList(snapshot)
            }
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(applicationContext, "Rien n'est ajouté", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun addItemToList(snapshot: DataSnapshot) {
                val items = snapshot.children.iterator()

                if(items.hasNext()){
                    val toDoIndexedValue = items.next()
                    val itemsIterator = toDoIndexedValue.children.iterator()

                    while(itemsIterator.hasNext()){
                        val currentItem = itemsIterator.next()
                        val toDoItemData = CoursesClass.createList()
                        val map = currentItem.getValue() as HashMap<String, Any>

                        toDoItemData.UID = currentItem.key
                        toDoItemData.done=map.get("done") as Boolean?
                        toDoItemData.itemDataText = map.get("itemDataText") as String?
                        ToDOList!!.add(toDoItemData)

                    }
                }

                adapter.notifyDataSetChanged()

            }

    override fun modifyItem(itemUID: String, isDone: Boolean) {
        val email = FirebaseAuth.getInstance().currentUser?.email.toString()
        val itemReference = database.child("$email").child("ListeCourse").child(itemUID)
        itemReference.child("done").setValue(isDone)
    }

    override fun onItemDelete(itemUID: String) {
        val email = FirebaseAuth.getInstance().currentUser?.email.toString()
        val itemReference = database.child("ListeCourse").child(itemUID)
        itemReference.removeValue()
        adapter.notifyDataSetChanged()
    }
}