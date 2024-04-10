package com.example.bitbybeta.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "questions")
data class QuestionEntity(
    @PrimaryKey(autoGenerate = true) private var id: Long = 0,
    private var cardSetId: Long,
    private var questionText: String,
    private var answerOption1: String,
    private var answerOption2: String,
    private var answerOption3: String,
    private var answerOption4: String,
    private var correctAnswerIndex: Int,
    private var appearances: Int,
    private var correctlyAnswered: Int
) {
    fun getId(): Long {
        return id
    }

    fun setId(id: Long) {
        this.id = id
    }

    fun getCardSetId(): Long {
        return cardSetId
    }

    fun setCardSetId(cardSetId: Long) {
        this.cardSetId = cardSetId
    }

    fun getQuestionText(): String {
        return questionText
    }

    fun setQuestionText(questionText: String) {
        this.questionText = questionText
    }

    fun getAnswerOption1(): String {
        return answerOption1
    }

    fun setAnswerOption1(answerOption1: String) {
        this.answerOption1 = answerOption1
    }

    fun getAnswerOption2(): String {
        return answerOption2
    }

    fun setAnswerOption2(answerOption2: String) {
        this.answerOption2 = answerOption2
    }

    fun getAnswerOption3(): String {
        return answerOption3
    }

    fun setAnswerOption3(answerOption3: String) {
        this.answerOption3 = answerOption3
    }

    fun getAnswerOption4(): String {
        return answerOption4
    }

    fun setAnswerOption4(answerOption4: String) {
        this.answerOption4 = answerOption4
    }

    fun getCorrectAnswerIndex(): Int {
        return correctAnswerIndex
    }

    fun setCorrectAnswerIndex(correctAnswerIndex: Int) {
        this.correctAnswerIndex = correctAnswerIndex
    }

    fun getAppearances(): Int {
        return appearances
    }

    fun setAppearances(appearances: Int) {
        this.appearances = appearances
    }

    fun getCorrectlyAnswered(): Int {
        return correctlyAnswered
    }

    fun setCorrectlyAnswered(correctlyAnswered: Int) {
        this.correctlyAnswered = correctlyAnswered
    }
}
