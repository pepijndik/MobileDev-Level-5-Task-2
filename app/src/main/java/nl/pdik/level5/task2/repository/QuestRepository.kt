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

    private val _quests: MutableLiveData<List<Quest>> = MutableLiveData()

    val quests: LiveData<List<Quest>>
        get() = _quests

    // The CreateProfileFragment can use this to see if creation succeeded
    private val _createSuccess: MutableLiveData<Boolean> = MutableLiveData()

    val createSuccess: LiveData<Boolean>
        get() = _createSuccess

    suspend fun getQuest() {
        try {
            withTimeout(5_000) {
                val list = mutableStateListOf<Quest>();
                questDocument.get().addOnSuccessListener { documents ->
                    for (document in documents) {
                        list.add(document.toObject<Quest>(Quest::class.java));
                    }
                }
                _quests.value = list;
            }
        } catch (e: Exception) {
            throw QuestsRetrievalError("Retrieval-firebase-task was unsuccessful")
        }
    }

    fun reset() {
        _createSuccess.value = false
    }

    class QuestsRetrievalError(message: String) : Exception(message)
}
