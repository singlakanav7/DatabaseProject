package com.example.demo.dao;

import java.util.List;

import com.example.demo.Model.User;

/**
 * @author Kanav Singla
 *
 */
public class DAOImpl implements UserDAO {

	List<User> list;

	
	public DAOImpl() {

		User obj = new User(1, "Kanav");
		User obj1 = new User(2, "Deep");
		list.add(obj);
		list.add(obj1);

	}

	@Override
	public void save() {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int id) {
		list.remove(id);
		System.out.println(list.get(id));
	}

	@Override
	public User get(int id) {
		return list.get(id);
	}

	@Override
	public List<User> getAll() {
		return list;
	}

}
