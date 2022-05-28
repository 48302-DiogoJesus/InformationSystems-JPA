package Utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class Utils {
    public static void CallStoredProcedure(String sp_name, Object[] arguments) {
        try (
                EntityManagerFactory ef = Persistence.createEntityManagerFactory("SI");
                EntityManager em = ef.createEntityManager()
        ) {
            em.getTransaction().begin();

            // Build query string
            StringBuilder queryString = new StringBuilder("call " + sp_name );
            queryString.append("(");
            for (int i = 1; i <= arguments.length; i++) {
                queryString.append("?" + i);
                if (i != arguments.length) {
                    queryString.append(", ");
                }
            }
            queryString.append(")");

            Query q = em.createNativeQuery(queryString.toString());

            // Set parameters
            for (int i = 1; i <= arguments.length; i++) {
                q.setParameter(i, arguments[i-1]);
            }

            // Execute Procedure
            q.executeUpdate();
            em.getTransaction().commit();
        }
    }

}
