package com.example.demo.listener;

import java.util.List;

import javax.persistence.MappedSuperclass;
import javax.persistence.PreUpdate;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.Company;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
@MappedSuperclass
public class AuditListener {
	
	@Autowired
	public UserRepository userRepository;

	// @LastModifiedBy
	// @OneToOne(fetch = FetchType.LAZY)
	// @JoinColumn(name = "company_id", insertable = false, updatable = true)
	// @NotNull
	// private Company modifiedBy;
	@PreUpdate
	public void userPreUpdate(Company ob) {
		System.out.println("Listening User Pre Update : " + ob.getId() + " " + ob.getName());

//		User u=userRepository.findByCompany(ob);
//		userRepository.save(u);
		}
		// //userRepository.findByCompanyId(ob.getId());
		// System.out.println("Listening User Pre Update : "
		// +userRepository.findByCompanyId(ob.getId()));
		//
		// userRepository.save(userRepository.findByCompanyId(ob.getId()));
	
}