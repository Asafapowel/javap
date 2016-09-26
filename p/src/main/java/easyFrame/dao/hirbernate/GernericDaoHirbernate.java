package easyFrame.dao.hirbernate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.Table;
import javax.sql.DataSource;




import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import easyFrame.dao.GenericDao;


/**
 * This class serves as the Base class for all other DAOs - namely to hold
 * common CRUD methods that they might all use. You should only need to extend
 * this class when your require custom CRUD logic.
 * <p/>
 * <p>To register this class in your Spring context file, use the following XML.
 * <pre>
 *      &lt;bean id="fooDao" class="com.myapp.dao.hibernate.GenericDaoHibernate"&gt;
 *          &lt;constructor-arg value="com.myapp.model.Foo"/&gt;
 *      &lt;/bean&gt;
 * </pre>
 *
 * @author <a href="mailto:bwnoll@gmail.com">Bryan Noll</a>
 *      Updated by jgarcia: update hibernate3 to hibernate4
 * @author jgarcia (update: added full text search + reindexing)
 * @param <T> a type variable
 * @param <PK> the primary key for that type
 */
public class GernericDaoHirbernate<T, PK extends Serializable> implements GenericDao<T, PK> {
    /**
     * Log variable for all child classes. Uses LogFactory.getLog(getClass()) from Commons Logging
     */
    protected final Log log = LogFactory.getLog(getClass());
    private Class<T> persistentClass;
    @Resource
    private SessionFactory sessionFactory;
 

    
    
    
    
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }
    
    public NamedParameterJdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
    
    public Long queryForCount(String where) {
    	String sql = " select count(id) as cnt from " + getTableName(); 
    	if (where != null && where.length() > 0) {
			sql += " where " + where; 
		};
		Map<String, String> paramMap = new HashMap<String, String>();
		SqlParameterSource params = new MapSqlParameterSource(paramMap);
		List<Map<String, Object>> result = jdbcTemplate.queryForList(sql , params);
		if (result.size() > 0) {
			return (Long) result.get(0).get("cnt");
		}
		return null;
    }
    
    
//    public Page queryForPage(String where, Integer pageNo) {
//    	if (pageNo != null) {
//	    	long count = queryForCount(where);
//	    	if (Page.getStartOfPage(pageNo) > count) {	//³¬³ö·¶Î§ÁË
//				return new Page(Page.getStartOfPage(pageNo), count, Page.DEFAULT_PAGE_SIZE, new ArrayList<>());
//			}
//	    	
//	    	NamedParameterJdbcTemplate jdbcTemplate = getJdbcTemplate();
//	    	String sql = " select * from " + getTableName();
//	    	if (where != null && where.length() > 0) {
//				sql += " where " + where; 
//			};
//			sql += " limit " + Page.getStartOfPage(pageNo) + ", " + Page.DEFAULT_PAGE_SIZE;
//			Map<String, String> paramMap = new HashMap<String, String>();
//			SqlParameterSource params = new MapSqlParameterSource(paramMap);
//			List<Map<String, Object>> result = jdbcTemplate.queryForList(sql, params);
//			List<Schedule> schedules = new ArrayList<Schedule>();
//			for(Map<String, Object> map : result){
//				Schedule schedule = new Schedule();
//			    schedule.setUserId((Long)map.get( "uid"));
//			    schedule.setRealName((String)map.get("urn"));
//			    schedule.setTitle((String)map.get("ut"));
//			    schedules.add(schedule);
//			}
//			return schedules;
//    	}
//    	return null;
//    }
    
    
    private Table table;
    
    public String getTableName(){
    	return table.name();
    }
    
    
    
    
    
    /**
     * Constructor that takes in a class to see which type of entity to persist.
     * Use this constructor when subclassing.
     *
     * @param persistentClass the class type you'd like to persist
     */
    public GernericDaoHirbernate(final Class<T> persistentClass) {
        this.persistentClass = persistentClass;
      
        
        table = AnnotationUtils.findAnnotation(persistentClass, Table.class);
    }

    /**
     * Constructor that takes in a class and sessionFactory for easy creation of DAO.
     *
     * @param persistentClass the class type you'd like to persist
     * @param sessionFactory  the pre-configured Hibernate SessionFactory
     */
    public GernericDaoHirbernate(final Class<T> persistentClass, SessionFactory sessionFactory) {
        this.persistentClass = persistentClass;
        this.sessionFactory = sessionFactory;
        
        
        table = AnnotationUtils.findAnnotation(persistentClass, Table.class);
    }

    
    
    
    
    
    
    public SessionFactory getSessionFactory() {
        return this.sessionFactory;
    }

    public Session getSession() throws HibernateException {
        Session sess = getSessionFactory().getCurrentSession();
        if (sess == null) {
            sess = getSessionFactory().openSession();
        }
        return sess;
    }

    
    
    
    
    
    @Autowired
    @Required
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<T> getAll() {
        Session sess = getSession();
        return sess.createCriteria(persistentClass).list();
    }
    
    
    

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<T> getAllDistinct() {
        Collection<T> result = new LinkedHashSet<T>(getAll());
        return new ArrayList<T>(result);
    }

    /**
     * {@inheritDoc}
     */
  
    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public T get(PK id) {
		return null;
    }
    
   
   

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public T save(T object) {
        Session sess = getSession();
        return (T) sess.merge(object);
    }

    /**
     * {@inheritDoc}
     */
    public void remove(T object) {
        Session sess = getSession();
        sess.delete(object);
    }

   

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<T> findByNamedQuery(String queryName, Map<String, Object> queryParams) {
        Session sess = getSession();
        Query namedQuery = sess.getNamedQuery(queryName);

        for (String s : queryParams.keySet()) {
            namedQuery.setParameter(s, queryParams.get(s));
        }

        return namedQuery.list();
    }

	public List<T> getByInstitution(String institutionCode) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<T> search(String searchTerm) {
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

	public void reindex() {
		// TODO Auto-generated method stub
		
	}

	public void reindexAll(boolean async) {
		// TODO Auto-generated method stub
		
	}

	public void remove(PK id) {
		// TODO Auto-generated method stub
		
	}

    /**
     * {@inheritDoc}
     */
   


   
	
}
