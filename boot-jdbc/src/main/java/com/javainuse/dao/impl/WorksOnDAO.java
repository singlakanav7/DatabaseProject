package com.javainuse.dao.impl;

import java.util.List;

import com.javainuse.model.WorksOn;

public interface WorksOnDAO {

	int assignProject(WorksOn w);

	List<WorksOn> listProjects();

	List<WorksOn> getDependentBySsn(String ssn);
	
	int checkProject(WorksOn w);
	
	void deleteProject(String id);
}
