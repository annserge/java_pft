package ru.stqa.pft.mantis.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.pft.mantis.model.BtUserData;
import ru.stqa.pft.mantis.model.BtUsers;

import java.util.List;

/**
 * Created by Anna on 20.05.2016.
 */
public class DbHelper {

  private final SessionFactory sessionFactory;

  public DbHelper() {
    // A SessionFactory is set up once for an application!
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();

    sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
  }

  public BtUsers btUsers() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<BtUserData> result = session.createQuery( "from BtUserData" ).list();
    session.getTransaction().commit();
    session.close();
    return new BtUsers(result);
  }
}
