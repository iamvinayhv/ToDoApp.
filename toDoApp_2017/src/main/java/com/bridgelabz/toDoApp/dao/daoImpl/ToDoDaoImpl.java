package com.bridgelabz.toDoApp.dao.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
	public boolean addNote(ToDo toDo) {
		
		try {
			Session session = sessionFactory.getCurrentSession();
			//Transaction transaction = session.beginTransaction();
			
			session.save(toDo);
			//transaction.commit();
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}


	
	@Override
	public List<ToDo> getNotes( User user ) {
	
			Session session = sessionFactory.getCurrentSession();
			
			String hql = "from ToDo where user_id=:userId";
			Query query = session.createQuery(hql);
			query.setParameter("userId", user.getId());
			List<ToDo> notes = query.list();
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
	public boolean updateNote(ToDo toDo) {
		
		try {
			Session session = sessionFactory.getCurrentSession();
			session.update(toDo);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		
	}



	@Override
	public boolean copyToDo(ToDo copy) {
		
		try{
			Session session = sessionFactory.getCurrentSession();
			session.save(copy);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}


	@Override
	public void update(ToDo toDo) {
		
		try{
			Session session = sessionFactory.getCurrentSession();
			
			session.update(toDo);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}



	@Override
	public boolean collaborator(Collaborator collaborator) {
		
		try{
			Session session = sessionFactory.getCurrentSession();
			
			session.save(collaborator);
			return true;
		}
		catch (Exception e) {
			return false;
		}
		
	}



	@Override
	public List<ToDo> getSharedNotes(User user) {
			
		
		try{
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
		catch (Exception e) {
			
			e.printStackTrace();
			return null;
		}
	}

}
