package com.snb.hbm.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(BaseHibernateDAO.class);
	private SessionFactory sessionFactory;
	protected void initDao() {
		// do nothing
	}
	 public Session getSession() {
        return sessionFactory.getCurrentSession();
    }
	public int executeByHQL(String strHQL){
		Query query = null;
		Session session = sessionFactory.getCurrentSession();
		query =session.createQuery(strHQL);
		return query.executeUpdate();
	}
	
	public int exexuteBySQL(String strSQL) {
		SQLQuery query = null;
		Session session = sessionFactory.getCurrentSession();
		query =session.createSQLQuery(strSQL);
		return query.executeUpdate();
	}
	
	/**
	 * @param transientInstance
	 * @return 闁跨喐鏋婚幏鐑芥晸閺傘倖瀚归柨鐔告灮閹风兘鏁撶紒鐐村敾閹风兘鏁撶紓瀵割暜閹风兘鏁撻弬銈嗗闁跨噦鎷�

	 */
	public Serializable save(Object obj) {
		log.debug("saving "+obj.getClass().getName()+" instance");
		try {
			Serializable serializable=sessionFactory.getCurrentSession().save(obj);
			log.debug("save successful");
			return serializable;
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Object obj) {
		log.debug("deleting "+obj.getClass().getName()+" instance");
		try {
			sessionFactory.getCurrentSession().delete(obj);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}


	public void update(Object obj){
		log.debug("attaching dirty "+obj.getClass().getName()+" instance");
		try {
			sessionFactory.getCurrentSession().update(obj);
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}
	
	public void saveOrUpdate(Object obj) {
		log.debug("save or update "+obj.getClass().getName()+" obj");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(obj);
			log.debug("save or update successful");
		} catch (RuntimeException re) {
			log.error("save or update failed", re);
			throw re;
		}
	}
	
	
	
	public int update(String hql,Object ... values){
		log.debug("update hql :"+hql);
		try {
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					query.setParameter(i, values[i]);
				}
			}
			int num=query.executeUpdate();
			log.debug("update hql successful");
			return num;
		} catch (RuntimeException re) {
			log.error("update hql failed", re);
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	
	public Object getById(Class clazz,Serializable id) {
		log.debug("getting "+clazz.getName()+" obj with id: " + id);
		try {
			return  sessionFactory.getCurrentSession().get(clazz.getName(), id);
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	/**
	 * hql闁跨喐鏋婚幏鐑芥晸閺傘倖瀚归柨鐔虹叓閳藉懏瀚�闁跨喐鏋婚幏宄板窗娴ｅ秹鏁撻弬銈嗗婵棝鏁撶紒鐐存灮閹风兘鏁撶粣鏍垫嫹

	 */
	@SuppressWarnings("unchecked")
	
	public List query(String hql,Object ...values){
		return query(hql,values,-1,-1);
	}
	/**
	 * hql闁跨喐鏋婚幏鐑芥晸閺傘倖瀚归柨鐔虹叓閳藉懏瀚�闁跨喐鏋婚幏宄板窗娴ｅ秹鏁撻弬銈嗗婵棝鏁撶紒鐐存灮閹风兘鏁撶粣鏍垫嫹

	 */
	@SuppressWarnings("unchecked")
	
	public List query(String hql, Object values[],int firstResult, int maxResults){
		log.debug("query hql :"+hql);
		try {
			Query query=sessionFactory.getCurrentSession().createQuery(hql);
			if (firstResult >= 0) {
				query.setFirstResult(firstResult);
			}
			if (maxResults > 0) {
				query.setMaxResults(maxResults);
			}
			if(values!=null){
				for(int i=0,length=values.length;i<length;i++){
					query.setParameter(i, values[i]);
				}
			}
			return query.list();
		} catch (RuntimeException re) {
			log.error("query hql failed", re);
			throw re;
		}
	}
	/**
	 * 娴ｅ潡鏁撻惌顐ヮ嚋閹风兘鏁撻弬銈嗗閿犳煼姘舵晸缂佺偞鏋婚幏鐑芥晸缁愭牭鎷�

	 */
	@SuppressWarnings("unchecked")
	
	public List query(String hql,final String[] paramNames, final Object[] values){
		return query(hql,paramNames,values,-1,-1);
	}
	/**
	 * 娴ｅ潡鏁撻惌顐ヮ嚋閹风兘鏁撻弬銈嗗閿犳煼姘舵晸缂佺偞鏋婚幏鐑芥晸缁愭牭鎷�

	 */
	@SuppressWarnings("unchecked")
	
	public List query(String hql,final String[] paramNames, final Object[] values,int firstResult, int maxResults){
		log.debug("query hql :"+hql);
		if (paramNames.length != values.length) {
			throw new IllegalArgumentException("Length of paramNames array must match length of values array");
		}
		try {
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			if (firstResult >= 0) {
				query.setFirstResult(firstResult);
			}
			if (maxResults > 0) {
				query.setMaxResults(maxResults);
			}
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					applyNamedParameterToQuery(query, paramNames[i], values[i]);
				}
			}
			return query.list();
		} catch (RuntimeException re) {
			log.error("query hql failed", re);
			throw re;
		}
	}
	public List querySQL(String sql,Object value){
		return querySQL(sql,value,-1,-1);
	}

	/**
	 * sql闁跨喐鏋婚幏鐑芥晸閺傘倖瀚归柨鐔虹叓閳藉懏瀚�闁跨喐鏋婚幏宄板窗娴ｅ秹鏁撻弬銈嗗婵棝鏁撶紒鐐存灮閹风兘鏁撶粣鏍垫嫹

	 */
	@SuppressWarnings("unchecked")
	
	public List querySQL(String sql,Object[] values){
		return querySQL(sql,values,-1,-1);
	}
	
	public List querySQL(String sql, Object values,int firstResult, int maxResults){
		log.debug("query sql :"+sql);
		try {
			SQLQuery sqlQuery=sessionFactory.getCurrentSession().createSQLQuery(sql);
			if (firstResult >= 0) {
				sqlQuery.setFirstResult(firstResult);
			}
			if (maxResults > 0) {
				sqlQuery.setMaxResults(maxResults);
			}
			if(values!=null){
				sqlQuery.setParameter(0, values);
			}
			return sqlQuery.list();
		} catch (RuntimeException re) {
			log.error("query sql failed", re);
			throw re;
		}
	}
	/**
	 * sql闁跨喐鏋婚幏鐑芥晸閺傘倖瀚归柨鐔虹叓閳藉懏瀚�闁跨喐鏋婚幏宄板窗娴ｅ秹鏁撻弬銈嗗婵棝鏁撶紒鐐存灮閹风兘鏁撶粣鏍垫嫹

	 */
	@SuppressWarnings("unchecked")
	
	public List querySQL(String sql, Object values[],int firstResult, int maxResults){
		log.debug("query sql :"+sql);
		try {
			SQLQuery sqlQuery=sessionFactory.getCurrentSession().createSQLQuery(sql);
			if (firstResult >= 0) {
				sqlQuery.setFirstResult(firstResult);
			}
			if (maxResults > 0) {
				sqlQuery.setMaxResults(maxResults);
			}
			if(values!=null){
				for(int i=0,length=values.length;i<length;i++){
					sqlQuery.setParameter(i, values[i]);
				}
			}
			return sqlQuery.list();
		} catch (RuntimeException re) {
			log.error("query sql failed", re);
			throw re;
		}
	}
	/**
	 * 娴ｅ潡鏁撻惌顐ヮ嚋閹风兘鏁撻弬銈嗗閿犳煼姘舵晸缂佺偞鏋婚幏鐑芥晸缁愭牭鎷�

	 */
	@SuppressWarnings("unchecked")
	
	public List querySQL(String sql,final String[] paramNames, final Object[] values){
		return querySQL(sql,paramNames,values,-1,-1);
	}
	/**
	 * 娴ｅ潡鏁撻惌顐ヮ嚋閹风兘鏁撻弬銈嗗閿犳煼姘舵晸缂佺偞鏋婚幏鐑芥晸缁愭牭鎷�

	 */
	@SuppressWarnings("unchecked")
	
	public List querySQL(String sql,final String[] paramNames, final Object[] values,int firstResult, int maxResults){
		log.debug("query sql :"+sql);
		if (paramNames.length != values.length) {
			throw new IllegalArgumentException("Length of paramNames array must match length of values array");
		}
		try {
			SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery(sql);
			if (firstResult >= 0) {
				sqlQuery.setFirstResult(firstResult);
			}
			if (maxResults > 0) {
				sqlQuery.setMaxResults(maxResults);
			}
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					applyNamedParameterToQuery(sqlQuery, paramNames[i], values[i]);
				}
			}
			return sqlQuery.list();
		} catch (RuntimeException re) {
			log.error("query sql failed", re);
			throw re;
		}
	}
	/**
	 * 闁跨喐鏋婚幏宄板絿闁跨喐鏋婚幏鐑芥晸閺傘倖瀚归柨鐔告灮閹风bm.xml闁跨喎褰ㄧ喊澶嬪HQL/SQL闁跨喐鏋婚幏鐑芥晸閿燂拷

	 * @param queryName
	 * @param values
	 * @return
	 */
	@SuppressWarnings("unchecked")
	
	public List queryByName(String name,Object ... values ){
		return queryByName(name,values,-1,-1);
	}
	/**
	 * 闁跨喐鏋婚幏宄板絿闁跨喐鏋婚幏鐑芥晸閺傘倖瀚归柨鐔告灮閹风bm.xml闁跨喎褰ㄧ喊澶嬪HQL/SQL闁跨喐鏋婚幏鐑芥晸閿燂拷

	 * @param name
	 * @param values
	 * @return
	 */
	@SuppressWarnings("unchecked")
	
	public List queryByName(String name, Object values[],int firstResult, int maxResults){
		log.debug("query by name :"+name);
		try {
			Query query=sessionFactory.getCurrentSession().getNamedQuery(name);
			if (firstResult >= 0) {
				query.setFirstResult(firstResult);
			}
			if (maxResults > 0) {
				query.setMaxResults(maxResults);
			}
			if(values!=null){
				for(int i=0,length=values.length;i<length;i++){
					query.setParameter(i, values[i]);
				}
			}
			return query.list();
		} catch (RuntimeException re) {
			log.error("query by name failed", re);
			throw re;
		}
	}
	/**
	 * 闁跨喐鏋婚幏宄板絿闁跨喐鏋婚幏鐑芥晸閺傘倖瀚归柨鐔告灮閹风bm.xml闁跨喎褰ㄧ喊澶嬪HQL/SQL闁跨喐鏋婚幏鐑芥晸閿燂拷

	 * @param name
	 * @param values
	 * @return
	 */
	@SuppressWarnings("unchecked")
	
	public List queryByName(String name,final String[] paramNames, final Object[] values){
		return queryByName(name,paramNames,values,-1,-1);
	}
	/**
	 * 闁跨喐鏋婚幏宄板絿闁跨喐鏋婚幏鐑芥晸閺傘倖瀚归柨鐔告灮閹风bm.xml闁跨喎褰ㄧ喊澶嬪HQL/SQL闁跨喐鏋婚幏鐑芥晸閿燂拷

	 * @param name
	 * @param values
	 * @return
	 */
	@SuppressWarnings("unchecked")
	
	public List queryByName(String name,final String[] paramNames, final Object[] values,int firstResult, int maxResults){
		log.debug("query by name :"+name);
		if (paramNames.length != values.length) {
			throw new IllegalArgumentException("Length of paramNames array must match length of values array");
		}
		try {
			Query query = sessionFactory.getCurrentSession().getNamedQuery(name);
			if (firstResult >= 0) {
				query.setFirstResult(firstResult);
			}
			if (maxResults > 0) {
				query.setMaxResults(maxResults);
			}
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					applyNamedParameterToQuery(query, paramNames[i], values[i]);
				}
			}
			return query.list();
		} catch (RuntimeException re) {
			log.error("query by failed", re);
			throw re;
		}
	}
	@SuppressWarnings("unchecked")
	protected void applyNamedParameterToQuery(Query queryObject, String paramName, Object value)throws HibernateException {

		if (value instanceof Collection) {
			queryObject.setParameterList(paramName, (Collection) value);
		}else if (value instanceof Object[]) {
			queryObject.setParameterList(paramName, (Object[]) value);
		}else {
			queryObject.setParameter(paramName, value);
		}
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
