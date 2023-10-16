package studentwithjspm2.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.border.EtchedBorder;

import studentwithjspm2.dto.Student;

public class StudentDao {

	public EntityManager getEntityManager() {
		return Persistence.createEntityManagerFactory("vinod").createEntityManager();
	}
	
	public List<Student> getAllStudents(){
		EntityManager entityManager=getEntityManager();
		Query query=entityManager.createQuery("Select u from Student u");
		return query.getResultList();
	}
	
	public Student signUpStudent(Student student) {
		EntityManager  entityManager=getEntityManager();
		EntityTransaction entityTransaction=entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.persist(student);
		entityTransaction.commit();
		return student;
	}

	public void deleteStudent(int id) {
	EntityManager entityManager=getEntityManager();
	Student student=entityManager.find(Student.class, id);
	EntityTransaction entityTransaction=entityManager.getTransaction();
	entityTransaction.begin();
	entityManager.remove(student);
	entityTransaction.commit();
	}

	public Student findStudentById(int id) {
		EntityManager entityManager=getEntityManager();
		Student student=entityManager.find(Student.class, id);
		return student;
	}

	public Student updateStudent(Student student) {
		EntityManager entityManager=getEntityManager();
		EntityTransaction entityTransaction=entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.merge(student);
		entityTransaction.commit();
		return student;
	}
	
	
	
	
	
}
