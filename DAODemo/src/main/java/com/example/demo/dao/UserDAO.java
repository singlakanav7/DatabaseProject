package com.example.demo.dao;

import java.util.List;

import com.example.demo.Model.User;

/**DAO Layer Interface
 * @author Kanav Singla
 *
 */
public interface UserDAO {

	void save();

	void delete(int id);

	User get(int id);

	List<User> getAll();

}
