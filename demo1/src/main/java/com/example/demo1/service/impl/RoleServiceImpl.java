//package com.example.demo1.service.impl;
//
//import com.example.demo1.entity.Role;
//import com.example.demo1.service.RoleService;
//import jakarta.persistence.TypedQuery;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//import jakarta.persistence.EntityManager;
//
//
//@Repository
//public class RoleServiceImpl implements RoleService {
//    @Autowired
//    private EntityManager entityManager;
//
//    public RoleServiceImpl(EntityManager theEntityManager){
//        entityManager = theEntityManager;
//    }
//
//    @Override
//    public Role findRoleByName(String theRoleName) {
//
//        // retrieve/read from database using name
//        TypedQuery<Role> theQuery = entityManager.createQuery("from Role where name=:roleName", Role.class);
//        theQuery.setParameter("roleName", theRoleName);
//
//        Role theRole = null;
//
//        try {
//            theRole = theQuery.getSingleResult();
//        } catch (Exception e) {
//            theRole = null;
//        }
//
//        return theRole;
//    }
//}
