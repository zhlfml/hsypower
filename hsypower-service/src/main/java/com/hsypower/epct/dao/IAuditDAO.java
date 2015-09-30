package com.hsypower.epct.dao;

import org.springframework.data.repository.CrudRepository;

import com.hsypower.epct.entity.Audit;

public interface IAuditDAO extends CrudRepository<Audit, Long> {

}
