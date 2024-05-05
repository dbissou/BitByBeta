import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bitbybeta.entity.FlashCardEntity
import java.util.Date

class CardSetViewModel : ViewModel() {

    // MutableLiveData to hold the list of flashcards
    private val _flashcardsLiveData = MutableLiveData<List<FlashCardEntity>>()

    // Expose LiveData to observe the list of flashcards
    val flashcardsLiveData: LiveData<List<FlashCardEntity>>
        get() = _flashcardsLiveData

    // MutableList to hold the list of flashcards
    private val flashcards = mutableListOf<FlashCardEntity>()

    // ViewModel properties to mimic the data structure of CardSetEntity
    private var cardSetTitle: String? = null
    private var cardSetStartDate: Date? = null
    private var cardSetEndDate: Date? = null
    private var currentQuestion: String? = ""
    private var currentAnswer: String? = ""

    //variable to hold number of questions for study session; defaults to max
    private var studyQuestionCount = flashcards.size


    // Function to get cardSetTitle
    fun getCardSetTitle(): String? {
        return cardSetTitle
    }

    // Function to set cardSetTitle
    fun setCardSetTitle(title: String) {
        cardSetTitle = title
    }

    // Function to get cardSetStartDate
    fun getCardSetStartDate(): Date? {
        return cardSetStartDate
    }

    // Function to set cardSetStartDate
    fun setCardSetStartDate(startDate: Date?) {
        cardSetStartDate = startDate
    }

    // Function to get cardSetEndDate
    fun getCardSetEndDate(): Date? {
        return cardSetEndDate
    }

    // Function to set cardSetEndDate
    fun setCardSetEndDate(endDate: Date?) {
        cardSetEndDate = endDate
    }

    // Method to set the current question
    fun setQuestion(question: String) {
        currentQuestion = question
    }

    // Method to set the current answer
    fun setAnswer(answer: String) {
        currentAnswer = answer
    }

    // Method to get the current question
    fun getQuestion(): String? {
        return currentQuestion
    }

    // Method to get the current answer
    fun getAnswer(): String? {
        return currentAnswer
    }

    // Function to add a flashcard to the list
    fun addFlashCard(flashcard: FlashCardEntity) {
        flashcards.add(flashcard)
        // Notify observers that the list has been updated
        _flashcardsLiveData.value = flashcards.toList()
    }

    // Function to remove a flashcard from the list
    fun removeFlashCard(flashcard: FlashCardEntity) {
        flashcards.remove(flashcard)
        // Notify observers that the list has been updated
        _flashcardsLiveData.value = flashcards.toList()
    }

    // Function to get the total count of flashcards
    fun getTotalFlashCardCount(): Int {
        return flashcards.size
    }

    // Function to get a flashcard at a specific index
    fun getFlashCard(index: Int): FlashCardEntity? {
        return if (index >= 0 && index < flashcards.size) {
            flashcards[index]
        } else {
            null
        }
    }

    // Function to set the list of flashcards
    fun setFlashCards(flashcardsList: List<FlashCardEntity>) {
        flashcards.clear()
        flashcards.addAll(flashcardsList)
        // Notify observers that the list has been updated
        _flashcardsLiveData.value = flashcards.toList()
    }

    // Function to update a flashcard at a specific index
    fun updateFlashCardAtIndex(index: Int, updatedFlashCard: FlashCardEntity) {
        if (index >= 0 && index < flashcards.size) {
            flashcards[index] = updatedFlashCard
            // Notify observers that the list has been updated
            _flashcardsLiveData.value = flashcards.toList()
        } else {
            // Handle index out of bounds or invalid index
            throw IndexOutOfBoundsException("Index $index is out of bounds for size ${flashcards.size}")
        }
    }

    // Function to clear the list of flashcards
    fun clearFlashCards() {
        flashcards.clear()
        // Notify observers that the list has been updated
        _flashcardsLiveData.value = emptyList()
    }


    //get and set question count for specific study session
    fun setStudyQuestionCount(n: Int) {
        studyQuestionCount = n
    }

    fun getStudyQuestionCount(): Int {
        return studyQuestionCount
    }

}
