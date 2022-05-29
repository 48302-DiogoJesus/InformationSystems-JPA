package model.Parameters;

import model.InputState;
import model.InputValidator;

import java.util.Objects;

public class Parameter<T> {
    public String name;
    public Boolean optional = false;
    public Object value;
    public Class<T> valueClass;
    public InputValidator validator;
    public Parameter(String name, Boolean optional, InputValidator inputValidator, Class<T> valueClass) {
        this.name = name;
        this.optional = optional;
        this.validator = inputValidator;
        this.valueClass = valueClass;
    }
    public Parameter(String name, InputValidator inputValidator, Class<T> valueClass) {
        this.name = name;
        this.validator = inputValidator;
        this.valueClass = valueClass;
    }

    public InputState setValue(String value) {
        // Se for opcional e nada for passado não tentar validar
        if (Objects.equals(value, "") && optional)
            return new InputState(true, null);

        InputState state = validator.validate(value);
        if (!state.valid)
            return state;

        if (valueClass == Integer.class) {
            this.value = Integer.parseInt(value);
        }
        else {
            this.value = value;
        }
        return state;
    }
}
