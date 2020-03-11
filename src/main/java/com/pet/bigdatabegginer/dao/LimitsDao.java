package com.pet.bigdatabegginer.dao;

import com.pet.bigdatabegginer.domain.Limits;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;

@Component
public class LimitsDao {

    private final SessionFactory sessionFactory;

    public LimitsDao(EntityManagerFactory factory) {
        this.sessionFactory = factory.unwrap(SessionFactory.class);
    }

    public Limits getMin() {
        return sessionFactory
                .openSession()
                .createQuery("select l from Limits l where l.name='min' order by l.date desc", Limits.class)
                .list()
                .get(0);
    }

    public Limits getMax() {
        return sessionFactory
                .openSession()
                .createQuery("select l from Limits l where l.name='max' order by l.date desc", Limits.class)
                .list()
                .get(0);
    }

}
