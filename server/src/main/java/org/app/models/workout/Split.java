package org.app.models.workout;

public class Split {

    // Fields
    private int splitId;
    private String splitName;

    // Constructor
    public Split (int splitId, String splitName) {

        this.splitId = splitId;
        this.splitName = splitName;
    }

    // Getters & Setters
    public int getSplitId() {
        return splitId;
    }

    public void setSplitId(int splitId) {
        this.splitId = splitId;
    }

    public String getSplitName() {
        return splitName;
    }

    public void setSplitName(String splitName) {
        this.splitName = splitName;
    }
}
