package Interfaces;

import java.util.List;

import POJO.*;

public interface UserManager {
	public void register(User user);

	public void createRole(Role role);

	public Role getRole(String name);

	public List<Role> getRoles();

	public void assignRole(User user, Role role);

	public User login(String name, String password);

	public void deleteUser(String email);

	public void changeEmail(String emailNew, String emailOld);

}
