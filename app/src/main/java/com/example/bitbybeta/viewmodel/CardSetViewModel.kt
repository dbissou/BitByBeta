import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bitbybeta.entity.QuestionEntity

class CardSetViewModel : ViewModel() {

    // MutableLiveData to hold the list of questions
    private val _questionsLiveData = MutableLiveData<List<QuestionEntity>>()

    // Expose LiveData to observe the list of questions
    val questionsLiveData: LiveData<List<QuestionEntity>>
        get() = _questionsLiveData

    // MutableList to hold the list of questions
    private val cards = mutableListOf<QuestionEntity>()

    // Function to add a question to the list
    fun addQuestion(question: QuestionEntity) {
        cards.add(question)
        // Notify observers that the list has been updated
        _questionsLiveData.value = cards.toList()
    }

    // Function to remove a question from the list
    fun removeQuestion(question: QuestionEntity) {
        cards.remove(question)
        // Notify observers that the list has been updated
        _questionsLiveData.value = cards.toList()
    }

    // Function to get the total count of questions
    fun getTotalQuestionCount(): Int {
        return cards.size
    }

    // Function to get a question at a specific index
    fun getQuestion(index: Int): QuestionEntity? {
        return if (index >= 0 && index < cards.size) {
            cards[index]
        } else {
            null
        }
    }

    // Function to set the list of questions
    fun setQuestions(questions: List<QuestionEntity>) {
        cards.clear()
        cards.addAll(questions)
        // Notify observers that the list has been updated
        _questionsLiveData.value = cards.toList()
    }

    // Function to clear the list of questions
    fun clearQuestions() {
        cards.clear()
        // Notify observers that the list has been updated
        _questionsLiveData.value = emptyList()
    }
}
