package com.demo.dao.impl;

import com.demo.dao.UserDao;
import com.demo.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pinggang Yu on 2016/10/9.
 */

@Repository("userDao")
public class UserDaoImpl implements UserDao {
    private HibernateTemplate hibernateTemplate;
    @Resource
    public void setSessionFactory (SessionFactory sessionFactory) {
        this.hibernateTemplate =  new HibernateTemplate(sessionFactory);
    }

    public int saveUser(User user) {
        Serializable serializable = this.hibernateTemplate.save(user);
        System.out.println(serializable);
        return (Integer)serializable;
    }

    public List<User> userList(final int first, final int offset) {
        List<User> userList = hibernateTemplate
                .execute(new HibernateCallback<List<User>>() {
                    public List<User> doInHibernate(Session session) throws HibernateException {
                        Query query = session.createQuery("from User");
                        List<User> result = query.setFirstResult(first - 1).setMaxResults(offset).list();
                        return result;
                    }
                });
        return userList;
    }
}
