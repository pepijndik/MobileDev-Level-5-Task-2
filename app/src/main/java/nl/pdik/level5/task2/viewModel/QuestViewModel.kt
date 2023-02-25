package nl.pdik.level5.task2.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import nl.pdik.level5.task2.model.Quest
import nl.pdik.level5.task2.repository.QuestRepository

class QuestViewModel(application: Application) : AndroidViewModel(application) {
    private val TAG = "FIRESTORE"
    private val questRepository: QuestRepository = QuestRepository()
    val quests: LiveData<List<Quest>> = questRepository.quests

    private val _errorText: MutableLiveData<String> = MutableLiveData()
    val errorText: LiveData<String>
        get() = _errorText

    fun getProfile() {
        viewModelScope.launch {
            try {
                questRepository.getQuest()
            } catch (ex: QuestRepository.QuestsRetrievalError) {
                val errorMsg = "Something went wrong while retrieving the Quests"
                Log.e(TAG, ex.message ?: errorMsg)
                _errorText.value = errorMsg
            }
        }
    }


    fun reset() {
        _errorText.value = ""
        questRepository.reset()
    }
}

