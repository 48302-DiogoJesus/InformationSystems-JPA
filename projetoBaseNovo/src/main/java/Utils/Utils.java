package Utils;

import jakarta.persistence.*;

import java.util.*;

import static java.util.Collections.emptyList;

public class Utils {
    public enum ProcedureType {
        FUNCTION,
        STORED_PROCEDURE
    }
    public enum ReturnType {
        TABLE,
        VOID
    }

    public static class InputState {
        public Boolean valid;
        public String errorMessage;

        public InputState(Boolean valid, String errorMessage) {
            this.valid = valid;
            this.errorMessage = errorMessage;
        }
    }

    public interface InputValidator {
        InputState validate(String value);
    }

    public static class Parameter<T> {
        // Param Name
        public String name;
        public Boolean optional = false;
        // Param Value: Any (when setting it is converted to String or Integer ONLY, currently)
        public Object value;
        // Param Class
        public Class<T> valueClass;
        // Function that takes a string and validates it. Each parameter could have its own validator
        public InputValidator validator;

        // Map Keys   = possible values
        // Map Values = Extra information
        // Example: Key=23-AS-12 | Value=Rodrigo, 2003, Chevrolet
        public HashMap<String, String> options = new HashMap<>();

        // 4 Constructors
        public Parameter(String name, Boolean optional, InputValidator inputValidator, Class<T> valueClass, HashMap<String, String> validOptions) {
            this.name = name;
            this.optional = optional;
            this.validator = inputValidator;
            this.valueClass = valueClass;
            this.options = validOptions;
        }
        public Parameter(String name, Boolean optional, InputValidator inputValidator, Class<T> valueClass) {
            this.name = name;
            this.optional = optional;
            this.validator = inputValidator;
            this.valueClass = valueClass;
        }
        public Parameter(String name, InputValidator inputValidator, Class<T> valueClass, HashMap<String, String> validOptions) {
            this.name = name;
            this.validator = inputValidator;
            this.valueClass = valueClass;
            this.options = validOptions;
        }
        public Parameter(String name, InputValidator inputValidator, Class<T> valueClass) {
            this.name = name;
            this.validator = inputValidator;
            this.valueClass = valueClass;
        }

        public void printValidOptions() {
            for (Map.Entry<String, String> option : options.entrySet()) {
                System.out.print(option.getKey() + ": ");
                System.out.println(option.getValue());
            }
        }

        // Value Setter
        public InputState setValue(String value) {
            // If optional AND no value was passed
            if (Objects.equals(value, "") && optional)
                return new InputState(true, null);

            if (!options.isEmpty() && !options.containsKey(value)) {
                return new InputState(false, "Invalid value for '" + name + "'");
            }

            // Validate String input
            InputState state = validator.validate(value);
            if (!state.valid)
                return state;

            // Set value with the correct conversion
            if (valueClass == Integer.class) {
                this.value = Integer.parseInt(value);
            }
            else {
                this.value = value;
            }
            return state;
        }
    }

    public static List<Object[]> CallProcedure(String sp_name, Parameter[] params, ProcedureType type, ReturnType returnType) {
        try (
                EntityManagerFactory ef = Persistence.createEntityManagerFactory("SI");
                EntityManager em = ef.createEntityManager()
        ) {
            em.getTransaction().begin();

            String startQuery = switch (type) {
                case FUNCTION -> "select * from " + sp_name;
                case STORED_PROCEDURE -> "call " + sp_name;
            };

            // Build query string
            StringBuilder queryString = new StringBuilder(startQuery);
            queryString.append("(");
            for (int i = 1; i <= params.length; i++) {
                queryString.append("?").append(i);
                if (i != params.length) {
                    queryString.append(", ");
                }
            }
            queryString.append(")");

            Query q = em.createNativeQuery(queryString.toString());
            // Set parameters
            for (int i = 1; i <= params.length; i++) {
                Parameter param = params[i - 1];
                // If optional and empty set NULL
                if (param.valueClass == String.class && param.value == "" && param.optional) {
                    q.setParameter(i, null);
                }
                q.setParameter(i, param.value);
            }

            List<Object[]> results = emptyList();
            if (returnType == ReturnType.TABLE) {
                results = q.getResultList();
            } else {
                q.executeUpdate();
            }

            em.getTransaction().commit();
            return results;
        }
    }
}
