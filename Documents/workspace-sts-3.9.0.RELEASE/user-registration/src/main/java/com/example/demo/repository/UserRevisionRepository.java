package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.User;
@Repository
@Transactional
public class UserRevisionRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> listUserRevisions(Long userId) {
		AuditReader auditReader=AuditReaderFactory.get(entityManager);
		User userObject=userRepository.findOne(userId);
	List<Number> revisions=auditReader.getRevisions(User.class,userId);
	List<User> userRevision=new ArrayList<>();
	for(Number revision:revisions) {
		userRevision.add(auditReader.find(User.class,userId, revision));
		
		
	}
	return userRevision;
	}

}
