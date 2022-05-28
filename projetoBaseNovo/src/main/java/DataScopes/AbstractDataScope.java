package DataScopes;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.LockModeType;
import jakarta.persistence.Persistence;
import model.JPAEntity;

import java.util.List;


@SuppressWarnings("rawtypes")
public class AbstractDataScope<T extends JPAEntity<K>, K> implements AutoCloseable {

    boolean isMine = true;
    boolean voted = false;

    ThreadLocal<Session> threadLocal = SessionThreadLocal.threadLocal;

    EntityManager em;
    Class<T> javaClass;
    String entityName;

    public AbstractDataScope(Class<T> javaClass) {
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
    public void close() {
        // TODO Auto-generated method stub
        if (isMine) {
            // Main transaction (first to be opened AND last to be called on CLOSE)
            if (
                threadLocal.get().getOk() // No Sub-Transaction has NOT voted (meaning everything should abort)
                &&
                voted                // Out transaction (MAIN) has voted (meaning it voted for commit)
            )  {
                threadLocal.get().getEm().getTransaction().commit();
            }
            else {
                threadLocal.get().getEm().getTransaction().rollback();
            }
            // Release Resources
            threadLocal.get().getEm().close();
            threadLocal.get().getEf().close();
            // Set ThreadLocal value to null
            threadLocal.remove();
        }
        else {
            System.out.println("Closing Sub-Transaction. Voted: " + voted);
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

    public T getSingle(K id) {
        T item = em.find(javaClass, id);
        // NULL if not found
        return item;
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
