package Utils;

import jakarta.persistence.*;

import java.util.List;
import model.Parameters.Parameter;

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
