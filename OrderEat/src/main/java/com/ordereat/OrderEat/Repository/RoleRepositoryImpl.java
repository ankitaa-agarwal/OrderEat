package com.ordereat.OrderEat.Repository;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ordereat.OrderEat.Entity.Role;

@Repository
public class RoleRepositoryImpl implements RoleRepository{

	@Autowired
	EntityManager em;
	
	@Override
	public Role getRoleById(Long id) {
		String query = "Select r from Role r where r.id = :id";
		TypedQuery<Role> role = em.createQuery(query, Role.class);
		role.setParameter("id", id);
		Role roleRes = role.getSingleResult();
		return roleRes;
	}

}
