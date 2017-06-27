package com.bridgelabz.toDoApp.dao.daoImpl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bridgelabz.toDoApp.dao.doaInterface.ToDoDao;
import com.bridgelabz.toDoApp.model.Collaborator;
import com.bridgelabz.toDoApp.model.ToDo;
import com.bridgelabz.toDoApp.model.User;

@Repository
public class ToDoDaoImpl implements ToDoDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void addNote(ToDo toDo) {
		
		
		Session session = sessionFactory.getCurrentSession();
		//Transaction transaction = session.beginTransaction();
		
		session.save(toDo);
		//transaction.commit();
		
	}


	
	@Override
	public List<ToDo> getNotes( User user ) {
	
			Session session = sessionFactory.getCurrentSession();
			
			List<ToDo> notes = (List<ToDo>) session.createCriteria(ToDo.class)
							  .createAlias("user", "u")
							  .add( Restrictions.eq("u.id", user.getId()) )
							  .list();
							 
			return notes;
		
	}



	@Override
	public int deleteNote(int id) {
		
		Session session = sessionFactory.getCurrentSession();
		
		String hql = "delete from ToDo where id=:id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		int result = query.executeUpdate();
		return result;
		
	}



	@Override
	public void updateNote(ToDo toDo) {
		
		Session session = sessionFactory.getCurrentSession();
		session.update(toDo);
	
		
	}



	@Override
	public void copyToDo(ToDo copy) {
		
		Session session = sessionFactory.getCurrentSession();
		session.save(copy);
	}


	@Override
	public void update(ToDo toDo) {
		
		Session session = sessionFactory.getCurrentSession();
		session.update(toDo);
	
	}



	@Override
	public void collaborator(Collaborator collaborator) {
	
		Session session = sessionFactory.getCurrentSession();
		session.save(collaborator);
		
	}



	@Override
	public List<ToDo> getSharedNotes(User user) {
			
		Session session = sessionFactory.getCurrentSession();
		
		List<ToDo> sharedNotes = null;
		
		
		Criteria criteria = session.createCriteria(Collaborator.class);
		
		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.property("toDo"));
		criteria.setProjection(projectionList);
		
		criteria.add(Restrictions.eq("user", user));
		
		sharedNotes = criteria.list();
		
		return sharedNotes;
		
	}



	@Override
	public void setIndex(List<Map<String,Integer>> indexOb) {
		
		String hql = "update ToDo set indexValue=:index where id=:todoId";
		Session session = sessionFactory.getCurrentSession();
		Iterator<Map<String,Integer>> iterator = indexOb.iterator();
		
		while(iterator.hasNext()){
			HashMap<String, Integer> map = (HashMap<String, Integer>) iterator.next();
			
			Query query =session.createQuery(hql);
			query.setParameter("index", map.get("index"));
			query.setParameter("todoId", map.get("id"));
			query.executeUpdate();
		}
		
	}

}
