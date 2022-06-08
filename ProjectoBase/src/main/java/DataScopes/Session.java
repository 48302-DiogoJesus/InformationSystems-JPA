package DataScopes;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class Session {
    private EntityManagerFactory ef;
    private EntityManager em;
    private boolean ok = true;

    public EntityManagerFactory getEf() {
        return ef;
    }

    public void setEf(EntityManagerFactory ef) {
        this.ef = ef;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public boolean getOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }
}
