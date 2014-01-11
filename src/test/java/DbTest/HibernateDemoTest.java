package DbTest;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import DbTest.models.SimpleObject;
import junit.framework.Assert;

import org.junit.*;


/**
 * @author <a href="mailto:lincolnbaxter@gmail.com">Lincoln Baxter, III</a>
 */
public class HibernateDemoTest
{
    private EntityManager em;

    @Before
    public void beforeEach()
    {
        em = EntityManagerUtil.getEntityManagerFactory().createEntityManager();
    }

    @After
    public void afterEach()
    {
        em.close();
    }

    @Test
    public void testAutoIncrement()
    {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        SimpleObject object0 = new SimpleObject();
        SimpleObject object1 = new SimpleObject();

        // IDs start as null
        Assert.assertEquals((Long) null, object0.getId());
        Assert.assertEquals((Long) null, object1.getId());

        em.persist(object0);
        em.persist(object1);

        transaction.commit();

        System.out.println("Object 0");
        System.out.println("Generated ID is: " + object0.getId());
        System.out.println("Generated Version is: " + object0.getVersion());

        System.out.println("Object 1");
        System.out.println("Generated ID is: " + object1.getId());
        System.out.println("Generated Version is: " + object1.getVersion());

        Assert.assertEquals((Long) 1l, object0.getId());
        Assert.assertEquals((Long) 2l, object1.getId());
    }
}
