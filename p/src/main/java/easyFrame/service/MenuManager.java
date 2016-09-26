package easyFrame.service;

import easyFrame.model.Menu;




public interface MenuManager extends GenericManager<Menu, Long>  {

	public boolean	makeEasyUItreeByRoleId(Long id);         

}
