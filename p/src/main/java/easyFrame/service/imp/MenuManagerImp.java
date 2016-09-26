package easyFrame.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;








import easyFrame.dao.MenuDao;

import easyFrame.model.Menu;

import easyFrame.service.MenuManager;

@Service("menuManager")
public class MenuManagerImp extends GenericManagerImpl<Menu, Long> implements MenuManager{

	@Autowired
	MenuDao menuDao;

	public MenuManagerImp() {
	}

	@Autowired
	public MenuManagerImp(
			MenuDao menuDao) {
		super(menuDao);
		this.menuDao =menuDao;
	}
	
	
	
	
	public boolean makeEasyUItreeByRoleId(Long id) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
