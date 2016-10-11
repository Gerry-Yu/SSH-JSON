package com.test;

import com.demo.dao.UserDao;
import com.demo.model.User;
import com.demo.service.UserService;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pinggang Yu on 2016/10/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = false)
@ContextConfiguration({"classpath:conf/spring-config.xml","classpath:struts.xml"})
@Component
public class UserTest {

    HibernateTemplate hibernateTemplate;
    @Resource
    void setSessionFactory(SessionFactory sessionFactory) {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }

    @Test
    @Transactional
    public void selectUser(){
        List<User> userList = hibernateTemplate
                .execute(new HibernateCallback<List<User>>() {
                    public List<User> doInHibernate(Session session) throws HibernateException {
                        Query query = session.createQuery("from User");
                        List<User> result = query.setFirstResult(2).setMaxResults(3).list();
                        return result;
                    }
                });
        for (User userIndex:userList) {
            System.out.println(userIndex.toString());
        }
    }
}
