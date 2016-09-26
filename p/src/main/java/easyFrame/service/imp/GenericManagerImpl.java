package easyFrame.service.imp;



import easyFrame.dao.GenericDao;

import easyFrame.service.GenericManager;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

/**
 * This class serves as the Base class for all other Managers - namely to hold
 * common CRUD methods that they might all use. You should only need to extend
 * this class when your require custom CRUD logic.
 * <p/>
 * <p>To register this class in your Spring context file, use the following XML.
 * <pre>
 *     &lt;bean id="userManager" class="com.myapp.service.impl.GenericManagerImpl"&gt;
 *         &lt;constructor-arg&gt;
 *             &lt;bean class="com.myapp.dao.hibernate.GenericDaoHibernate"&gt;
 *                 &lt;constructor-arg value="com.myapp.model.User"/&gt;
 *                 &lt;property name="sessionFactory" ref="sessionFactory"/&gt;
 *             &lt;/bean&gt;
 *         &lt;/constructor-arg&gt;
 *     &lt;/bean&gt;
 * </pre>
 * <p/>
 * <p>If you're using iBATIS instead of Hibernate, use:
 * <pre>
 *     &lt;bean id="userManager" class="com.myapp.service.impl.GenericManagerImpl"&gt;
 *         &lt;constructor-arg&gt;
 *             &lt;bean class="com.myapp.dao.ibatis.GenericDaoiBatis"&gt;
 *                 &lt;constructor-arg value="com.myapp.model.User"/&gt;
 *                 &lt;property name="dataSource" ref="dataSource"/&gt;
 *                 &lt;property name="sqlMapClient" ref="sqlMapClient"/&gt;
 *             &lt;/bean&gt;
 *         &lt;/constructor-arg&gt;
 *     &lt;/bean&gt;
 * </pre>
 *
 * @param <T>  a type variable
 * @param <PK> the primary key for that type
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 *  Updated by jgarcia: added full text search + reindexing
 */
public class GenericManagerImpl<T, PK extends Serializable> implements GenericManager<T, PK> {

@Autowired
protected GenericDao<T, PK> dao;
	
	
	public List<T> getAll() {
		
		return dao.getAll();
	
		
	}

	public List<T> getByInstitution(String institutionCode) {
		// TODO Auto-generated method stub
		return null;
	}

	public T get(PK id) {
		// TODO Auto-generated method stub
		return null;
	}

	public T get(PK id, String institutionCode) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean exists(PK id) {
		// TODO Auto-generated method stub
		return false;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public T save(T object) {
		dao.save(object);
		return 	dao.save(object);
		
	}

	public void remove(T object) {
		// TODO Auto-generated method stub
		
	}

	public void remove(PK id) {
		// TODO Auto-generated method stub
		
	}

	public List<T> search(String searchTerm, Class clazz) {
		// TODO Auto-generated method stub
		return null;
	}

	public void reindex() {
		// TODO Auto-generated method stub
		
	}

	public void reindexAll(boolean async) {
		// TODO Auto-generated method stub
		
	}
	
	 public GenericManagerImpl() {
	    }

	    public GenericManagerImpl(GenericDao<T, PK> genericDao) {
	        this.dao = genericDao;
	    }
}
