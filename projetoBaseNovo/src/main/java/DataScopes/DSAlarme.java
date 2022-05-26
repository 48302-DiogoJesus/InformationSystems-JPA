package DataScopes;

import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import model.Alarme;

import java.util.List;


/*
// Usage Example
public class Main {
    public static void main(String[] args) throws Exception {
        try (DSAlarme a = new DSAlarme()) {
            System.out.println(a.getAll());
            // Vote
            a.validateWork();
        }
    }
}
*/
public class DSAlarme extends AbstractDataScope {
    public DSAlarme() {
        super();
    }

    public List<Alarme> getAll() throws Exception {
        EntityManager em = super.getEntityManager();
        List<Alarme> alarmes = em.createQuery("select a from Alarme a",Alarme.class).getResultList();
        return alarmes;
    }

    public Alarme getSingle(int id) throws Exception  {
        EntityManager em = super.getEntityManager();
        Alarme alarme = em.find(Alarme.class, id);
        // NULL if not found
        return alarme;
    }

    public void delete(Alarme a) throws Exception {
        EntityManager em = super.getEntityManager();
        // Para garantir que caso esta transação/sub-transações tenham criado este recurso ele seja visível
        em.flush();
        int alarme_id = a.getId();
        deleteSingle(alarme_id);
    }

    public void deleteSingle(int id) throws Exception {
        EntityManager em = super.getEntityManager();
        em.flush();
        Alarme alarme = em.find(Alarme.class, id);
        if (alarme == null)
            throw new java.lang.IllegalAccessException("Alarme não existente: " + id);
        em.remove(alarme);
    }

    public void update(Alarme a) throws Exception {
        EntityManager em = super.getEntityManager();
        /*
         Garantir que neste momento a entidade ainda existe no sistema. Caso não exista será lançada exceção levando a
         não votar implicando ROLLBACK on CLOSE (.close())
         */
        em.flush();
        int alarme_id = a.getId();
        Alarme alarme = em.find(Alarme.class, alarme_id, LockModeType.PESSIMISTIC_WRITE);
        if (alarme == null)
            throw new java.lang.IllegalAccessException("Alarme não existente: " + alarme_id);
        /*
         Ao chamar este função assume-se que os campos já estão atualizados (Ex: al.setId_registo(2))
         Desta forma, quando a transação fizer commit (invocando .close()) a entidade será atualizada na base de dados
         */
    }

    public void create(Alarme a) throws Exception {
        EntityManager em = super.getEntityManager();
        em.flush();
        // Create
        em.persist(a);
    }
}
