package com.example.bitbybeta;

import java.util.Date;

public class CardSetModel {
    public CardSetModel(String cardSetTitle, Integer cardSetQuestionCount, Date cardSetStartDate, Date cardSetEndDate) {
        this.cardSetTitle = cardSetTitle;
        this.cardSetQuestionCount = cardSetQuestionCount;
        this.cardSetStartDate = cardSetStartDate;
        this.cardSetEndDate = cardSetEndDate;
    }
    String cardSetTitle;
    Integer cardSetQuestionCount;
    Date cardSetStartDate;
    Date cardSetEndDate;

    public String getCardSetTitle() {
        return cardSetTitle;
    }

    public Integer getCardSetQuestionCount() {
        return cardSetQuestionCount;
    }

    public Date getCardSetStartDate() {
        return cardSetStartDate;
    }

    public Date getCardSetEndDate() {
        return cardSetEndDate;
    }

    public void setCardSetTitle(String cardSetTitle) {
        this.cardSetTitle = cardSetTitle;
    }

    public void setCardSetQuestionCount(Integer cardSetQuestionCount) {
        this.cardSetQuestionCount = cardSetQuestionCount;
    }

    public void setCardSetStartDate(Date cardSetStartDate) {
        this.cardSetStartDate = cardSetStartDate;
    }

    public void setCardSetEndDate(Date cardSetEndDate) {
        this.cardSetEndDate = cardSetEndDate;
    }
}
