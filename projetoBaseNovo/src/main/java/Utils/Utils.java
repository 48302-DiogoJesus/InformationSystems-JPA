package Utils;

import jakarta.persistence.*;

import java.util.List;

public class Utils {
    public enum ProcedureType {
        FUNCTION,
        STORED_PROCEDURE
    }

    public static List<Object[]> CallProcedure(String sp_name, String[] arguments, ProcedureType type) {
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
            for (int i = 1; i <= arguments.length; i++) {
                queryString.append("?").append(i);
                if (i != arguments.length) {
                    queryString.append(", ");
                }
            }
            queryString.append(")");

            Query q = em.createNativeQuery(queryString.toString());
            // Set parameters
            for (int i = 1; i <= arguments.length; i++) {
                q.setParameter(i, arguments[i - 1]);
            }

            List<Object[]> results = q.getResultList();

            em.getTransaction().commit();
            return results;
        }
    }
}
