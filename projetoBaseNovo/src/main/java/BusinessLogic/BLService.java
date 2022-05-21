package BusinessLogic;

import java.util.List;
import jakarta.persistence.*;

import model.*;

public class BLService
{
    @SuppressWarnings("unchecked")
	public void test() throws Exception
    {
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("projetoBase");
        EntityManager em = emf.createEntityManager();

        try
        {
        	//Criar um aluno
            System.out.println("Ler um aluno");
            em.getTransaction().begin();

        	String sql = "SELECT a FROM Aluno a";
        	Query query = em.createQuery(sql);
            List<Gps> la = query.getResultList();

            for (Gps ax : la)
            {
                System.out.printf("%d ", ax.getNumal());
                System.out.printf("%s \n", ax.getNomeal());
            }
        } 
        catch(Exception e)
        {
        	System.out.println(e.getMessage());
        	throw e;
        }
        finally 
        {
        	em.close();
            emf.close();
        }
    }
}
