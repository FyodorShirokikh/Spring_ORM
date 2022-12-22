package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public List<User> listUsersCar(String model, int series) {
      Query<User> query = sessionFactory.getCurrentSession().createQuery("from User user where user.car.model=:carModel and user.car.series=:carSeries");
      Map<String, Object> properties = new HashMap<>();
      properties.put("carModel", model);
      properties.put("carSeries", series);
      query.setProperties(properties);
      return query.getResultList();
   }

}
