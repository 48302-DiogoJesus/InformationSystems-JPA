package DataScopes;

import jakarta.persistence.*;
import org.postgresql.util.PGobject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("rawtypes")
public class DataScope<T extends JPAEntity<K>, K> implements AutoCloseable {

    boolean isMine;
    boolean voted = false;

    ThreadLocal<Session> threadLocal = SessionThreadLocal.threadLocal;

    EntityManager em;
    Class<T> javaClass;
    String entityName;

    public DataScope(Class<T> javaClass) {
        this.javaClass = javaClass;
        this.entityName = javaClass.getName();

        if (threadLocal.get() == null) {
            // We are the first/main transaction
            EntityManagerFactory ef = Persistence.createEntityManagerFactory("SI");
            EntityManager em = ef.createEntityManager();
            Session s = new Session();
            s.setEf(ef);
            s.setEm(em);
            threadLocal.set(s);
            em.getTransaction().begin();
            // We are the main transaction
            isMine = true;
        }
        else {
            // We are a sub-transaction
            isMine = false;
        }
        // Set Entity Manager for the current instance
        em = threadLocal.get().getEm();
    }

    @Override
    public void close() throws Exception {
        // TODO Auto-generated method stub
        if (isMine) {
            try {
                // Main transaction (first to be opened AND last to be called on CLOSE)
                if (
                        threadLocal.get().getOk() // No Sub-Transaction has NOT voted (meaning everything should abort)
                        &&
                        voted                     // Out transaction (MAIN) has voted (meaning it voted for commit)
                )  {
                    threadLocal.get().getEm().getTransaction().commit();
                }
                else {
                    threadLocal.get().getEm().getTransaction().rollback();
                }
            } catch (PersistenceException e) {
                String expected = "org.postgresql.util.PSQLException: ERROR: ";
                String message = e.getMessage();
                String PSQLMessage = message.substring(message.indexOf(expected) + expected.length()).split("\\r?\\n")[0];
                throw new Exception("[DB ERROR] ON COMMIT/ROLLBACK: " + PSQLMessage);
            }
            // If the commit/rollback fails we still want to close the threadLocal
            finally {
                // Release Resources
                threadLocal.get().getEm().close();
                threadLocal.get().getEf().close();
                // Set ThreadLocal value to null
                threadLocal.remove();
            }
        }
        else {
            // Sub-transaction
            if (!voted) {
                cancelWork();
            }
        }
    }

    /*
    /** Set flag saying we agree with committing. No need for rollback (for this transaction) */
    public void validateWork() {
        voted = true;
    }

    /** Set flag saying we want to make the whole transaction + Sub-Transactions to rollback */
    public void cancelWork() {
        threadLocal.get().setOk(false);
        voted = true;
    }

    public List<T> getAll() {
        List<T> items = em.createQuery("select a from " + entityName + " a", javaClass).getResultList();
        return items;
    }

    public List<T> getAll(LockModeType lockModeType) {
        Query q = em.createQuery("select a from " + entityName + " a", javaClass);
        q.setLockMode(lockModeType);
        return (List<T>) q.getResultList();
    }

    public List<T> getAll(int limit) {
        return em.createQuery("select a from " + entityName + " a", javaClass).setMaxResults(limit).getResultList();
    }

    public List<T> getAll(int limit, LockModeType lockModeType) {
        Query q = em.createQuery("select a from " + entityName + " a", javaClass).setMaxResults(limit);
        q.setLockMode(lockModeType);
        return (List<T>) q.getResultList();
    }

    public T getSingle(K id) {
        return em.find(javaClass, id);
    }

    public T getSingle(K id, LockModeType lockModeType) {
        return em.find(javaClass, id, lockModeType);
    }

    public List<T> get(HashMap<String, Object> queryMap) {
        StringBuilder queryString= new StringBuilder("select a from " + entityName + " a where ");
        int i = 1;
        for (String key: queryMap.keySet()) {
            String appendText = "a." + key + " = :column" + i;

            if (i != queryMap.size())
                appendText += " and ";

            queryString.append(appendText);
            i++;
        }
        TypedQuery query = em.createQuery(queryString.toString(), javaClass);
        i = 1;
        for (Object value: queryMap.values()) {
            query.setParameter("column" + i, value);
            i++;
        }
        return (List<T>) query.getResultList();
    }

    public List<T> get(HashMap<String, Object> queryMap, LockModeType lockModeType) {
        StringBuilder queryString= new StringBuilder("select a from " + entityName + " a where ");
        int i = 1;
        for (String key: queryMap.keySet()) {
            String appendText = "a." + key + " = :column" + i;

            if (i != queryMap.size())
                appendText += " and ";

            queryString.append(appendText);
            i++;
        }
        TypedQuery query = em.createQuery(queryString.toString(), javaClass);
        query.setLockMode(lockModeType);
        i = 1;
        for (Object value: queryMap.values()) {
            query.setParameter("column" + i, value);
            i++;
        }
        return (List<T>) query.getResultList();
    }

    public List<PGobject> getNative(HashMap<String, Object> queryMap) {
        StringBuilder queryString= new StringBuilder("select a from " + javaClass.getSimpleName().toLowerCase() + " a where ");
        int i = 1;
        for (Map.Entry<String, Object> entry: queryMap.entrySet()) {
            String appendText = "a." + entry.getKey() + " = '" + entry.getValue() + "'";

            if (i != queryMap.size())
                appendText += " and ";

            queryString.append(appendText);
            i++;
        }
        Query query = em.createNativeQuery(queryString.toString());
        return query.getResultList();
    }

    public List<PGobject> getNative(HashMap<String, Object> queryMap, LockModeType lockModeType) {
        StringBuilder queryString = new StringBuilder("select a from " + javaClass.getSimpleName().toLowerCase() + " a where ");
        int i = 1;
        for (Map.Entry<String, Object> entry : queryMap.entrySet()) {
            String appendText = "a." + entry.getKey() + " = '" + entry.getValue() + "'";

            if (i != queryMap.size())
                appendText += " and ";

            queryString.append(appendText);
            i++;
        }
        Query query = em.createNativeQuery(queryString.toString());
        query.setLockMode(lockModeType);
        return query.getResultList();
    }

    public void delete(T item) throws Exception {
        // Para garantir que caso esta transação/sub-transações tenham criado este recurso ele seja visível
        em.flush();
        K id = item.getPK();
        deleteById(id);
    }

    public void deleteById(K id) throws Exception {
        em.flush();
        T item = em.find(javaClass, id);
        if (item == null)
            throw new java.lang.IllegalAccessException("O item que tentou remover não existe");
        em.remove(item);
    }

    public void deleteById(K id, LockModeType lockModeType) throws Exception {
        em.flush();
        T item = em.find(javaClass, id, lockModeType);
        if (item == null)
            throw new java.lang.IllegalAccessException("O item que tentou remover não existe");
        em.remove(item);
    }

    public void update(T c) throws Exception {
        /*
         Garantir que neste momento a entidade ainda existe no sistema. Caso não exista será lançada exceção levando a
         não votar implicando ROLLBACK on CLOSE (.close())
         */
        em.flush();
        K id = c.getPK();
        T item = em.find(javaClass, id, LockModeType.PESSIMISTIC_WRITE);
        if (item == null)
            throw new java.lang.IllegalAccessException("O item que tentou remover não existe");
        /*
        > CHANGE TRACKING <
        Ao chamar este função assume-se que os campos já estão atualizados (Ex: al.setId_registo(2))
        Desta forma, quando a transação fizer commit (invocando .close()) a entidade será atualizada na base de dados
         */
    }

    public void create(T item) {
        em.flush();
        // Create
        em.persist(item);
    }
}
