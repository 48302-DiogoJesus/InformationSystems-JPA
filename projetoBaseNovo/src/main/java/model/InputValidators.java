package model;

public class InputValidators {
    public static InputState NIF(String nif) {
        if (nif.length() != 9) {
            return new InputState(false, "NIF should be 9 characters long.");
        }
        return new InputState(true, null);
    }
    public static InputState Morada(String morada) {
        if (morada.length() <= 10) {
            return new InputState(false, "Morada should have more than 10 characters.");
        }
        return new InputState(true, null);
    }
    public static InputState Telefone(String telefone) {
        if (telefone.length() != 9) {
            return new InputState(false, "Telefone should be 9 characters long.");
        }
        return new InputState(true, null);
    }
    public static InputState CC(String cc) {
        if (cc.length() != 12) {
            return new InputState(false, "CC should be 12 characters long.");
        }
        return new InputState(true, null);
    }
    public static InputState CC(String cc) {
        if (cc.length() != 12) {
            return new InputState(false, "CC should be 12 characters long.");
        }
        return new InputState(true, null);
    }
}
