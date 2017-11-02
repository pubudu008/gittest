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

import com.example.demo.model.Company;
import com.example.demo.model.User;

@Transactional
@Repository
public class CompanyRevisionRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private CompanyRepository companyRepository;
	
	public List<Company> listRepository(Long id) {
	
		AuditReader auditReader=AuditReaderFactory.get(entityManager);
		Company comObject=companyRepository.findOne(id);
		System.out.println(comObject.getId());
		List<Number> revisions=auditReader.getRevisions(Company.class, id);
		List<Company> comapnyRevision=new ArrayList<>();
		//List<User> userRevision=new ArrayList<>();
		for(Number revision:revisions) {
			comapnyRevision.add(auditReader.find(Company.class, id, revision));
			
		}
		return comapnyRevision;
	}
	

}
