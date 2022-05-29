package model;

public class InputState {
    public Boolean valid;
    public String errorMessage;

    public InputState(Boolean valid, String errorMessage) {
        this.valid = valid;
        this.errorMessage = errorMessage;
    }
}
