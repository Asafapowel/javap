package easyFrame.dao.hirbernate;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;






import easyFrame.dao.MenuDao;

import easyFrame.model.Menu;

@Repository("menuDao")
public class TreeDaoHirbernate extends GernericDaoHirbernate<Menu, Long>implements MenuDao {

	public TreeDaoHirbernate() {
		super(Menu.class);
	}}
