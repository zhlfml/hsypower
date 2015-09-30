package com.hsypower.epct.dao;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.hsypower.epct.entity.QualificationFileCategory;

public interface IQualificationFileCategoryDAO extends CrudRepository<QualificationFileCategory, Long> {
	
	@Query("select o from QualificationFileCategory o")
	List<QualificationFileCategory> findAll(Sort sort);
}
