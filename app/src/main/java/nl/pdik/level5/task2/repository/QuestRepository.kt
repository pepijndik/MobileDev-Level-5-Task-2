package nl.pdik.level5.task2.repository

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withTimeout
import nl.pdik.level5.task2.model.Quest

class QuestRepository {
    private var firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    private var questDocument = firestore.collection("questions");
    private val questList = MutableLiveData<List<Quest>>()

    val quests: MutableLiveData<List<Quest>>
        get() = questList

    suspend fun getQuest() {
        try {
            withTimeout(5_000) {
                questDocument.addSnapshotListener { querySnapshot, _ ->
                    if (querySnapshot != null) {
                        val quests = querySnapshot.documents.map { documentSnapshot ->
                            Quest(
                                documentSnapshot["choices"] as ArrayList<String>,
                                documentSnapshot["correctAnswer"] as String,
                                documentSnapshot["id"] as Long,
                                documentSnapshot["question"] as String
                            )
                        }
                        questList.value = quests
                    }
                }
            }
        } catch (e: Exception) {
            throw QuestsRetrievalError("Retrieval-firebase-task was unsuccessful")
        }
    }

    fun reset() {

    }

    class QuestsRetrievalError(message: String) : Exception(message)
}
