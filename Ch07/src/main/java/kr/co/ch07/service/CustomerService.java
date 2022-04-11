package kr.co.ch07.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ch07.persistence.CustomerRepo;
import kr.co.ch07.vo.CustomerVo;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepo repo;
	
	public void insertCustomer(CustomerVo vo) {
		repo.save(vo);
	}
	
	public CustomerVo selectCustomer(int custid) {
		return repo.findById(custid).get();
	}
	public List<CustomerVo> selectCustomers() {
		return repo.findAll();
		
	}
	public void updateCustomer(CustomerVo cv) {
		repo.save(cv);
	}
	
	public void deleteCustomer(int custid) {
		repo.deleteById(custid);
	}

}
