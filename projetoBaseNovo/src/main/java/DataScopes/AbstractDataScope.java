package DataScopes;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class AbstractDataScope implements AutoCloseable {

    protected class Session {
        private EntityManagerFactory ef;
        private EntityManager em;
        private boolean ok = true;
    }

    boolean isMine = true;
    boolean voted = false;

    private static final ThreadLocal<Session> threadLocal = ThreadLocal.withInitial(() -> null);

    public AbstractDataScope() {
        if (threadLocal.get() == null) {
            // We are the first/main transaction
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SI");
            EntityManager em = emf.createEntityManager();
            Session s = new Session();
            s.ef = emf;
            s.ok = true;
            s.em = em;
            threadLocal.set(s);
            em.getTransaction().begin();
            isMine = true;
        }
        else {
            // We are a sub-transaction
            isMine = false;
        }
    }

    public EntityManager getEntityManager() {
        return threadLocal.get().em;
    }

    @Override
    public void close() throws Exception {
        // TODO Auto-generated method stub
        if (isMine) {
            // Main transaction (first to be opened AND last to be called on CLOSE)
            if (
                threadLocal.get().ok // No Sub-Transaction has NOT voted (meaning everything should abort)
                &&
                voted                // Out transaction (MAIN) has voted (meaning it voted for commit)
            )  {
                threadLocal.get().em.getTransaction().commit();
            }
            else {
                threadLocal.get().em.getTransaction().rollback();
            }
            // Release Resources
            threadLocal.get().em.close();
            threadLocal.get().ef.close();
            // Set ThreadLocal value to null
            threadLocal.remove();
        }
        else {
            // Sub-transaction
            if (!voted) {
                cancelWork();
            }
        }
    }

    /** Set flag saying we agree with committing. No need for rollback (for this transaction) */
    public void validateWork() {
        voted = true;
    }

    /** Set flag saying we want to make the whole transaction + Sub-Transactions to rollback */
    public void cancelWork() {
        threadLocal.get().ok = false;
        voted = true;
    }
}
