package logic.model;

import logic.bean.LoginBean;

public class LoginController {

	private static LoginController instance;

	private boolean logged = false;

	private String username;

	private String password;

	private LoginController() {
	}

	public static synchronized LoginController getInstance() {

		if (LoginController.instance == null)

			LoginController.instance = new LoginController();

		return LoginController.instance;

	}

	public boolean theUserExist(LoginBean loginBean) {

		User user = UserDao.findUserMock(loginBean.getUsername(), loginBean.getPassword());

		if (user != null) {
			this.username = loginBean.getUsername();
			this.password = loginBean.getPassword();
			logged = true;
			return true;
		}

		else
			return false;

	}

	public boolean theOwnerExist(LoginBean loginBean) {

		User user = UserDao.findOwnerMock(loginBean.getUsername(), loginBean.getPassword());

		if (user != null) {
			this.username = loginBean.getUsername();
			this.password = loginBean.getPassword();
			logged = true;
			return true;
		}

		else
			return false;

	}

	public boolean isLogged() {
		return logged;
	}

	public void setLogged(boolean logged) {
		this.logged = logged;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
