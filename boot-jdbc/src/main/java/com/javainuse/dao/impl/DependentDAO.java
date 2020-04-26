package com.javainuse.dao.impl;

import java.util.List;

import com.javainuse.model.Dependent;

/**
 * Dependent DAO Interface
 * 
 * @author Kanav Singla
 *
 */
public interface DependentDAO {

	void assignDependent(Dependent d);

	List<Dependent> listDependents();

	List<Dependent> getDependentBySsn(String ssn);

}
