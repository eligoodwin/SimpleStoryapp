package com.example.eligoodwin.simplestoryapp.model;

/**
 * Created by eligoodwin on 3/31/18.
 */

public class Page {
    //data objects
    private int imageId;
    private int textId;
    private boolean isFinalPage = false;

    //choices --
    private Choice choice1;
    private Choice choice2;

    public Page(int imageId, int textId, Choice choice1, Choice choice2) {
        this.imageId = imageId;
        this.textId = textId;
        this.choice1 = choice1;
        this.choice2 = choice2;
    }

    public Page(int imageId, int textId){
        this.imageId = imageId;
        this.textId = textId;
        this.isFinalPage = true;
    }

    //getters & setters

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getTextId() {
        return textId;
    }

    public void setTextId(int textId) {
        this.textId = textId;
    }

    public Choice getChoice1() {
        return choice1;
    }

    public void setChoice1(Choice choice1) {
        this.choice1 = choice1;
    }

    public Choice getChoice2() {
        return choice2;
    }

    public void setChoice2(Choice choice2) {
        this.choice2 = choice2;
    }

    public boolean isFinalPage() {
        return isFinalPage;
    }

    public void setFinalPage(boolean finalPage) {
        isFinalPage = finalPage;
    }
}
