package kr.co.kmarket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.kmarket.admin.vo.ProductVo;
import kr.co.kmarket.dao.ProductDao;

@Service
public class ProductService {
	
	@Autowired
	private ProductDao dao;
	
	public List<ProductVo> selectProducts(ProductVo vo){
		return dao.selectProducts(vo);
	}

}