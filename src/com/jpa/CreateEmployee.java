package com.jpa;

import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class CreateEmployee {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		Employee emp = new Employee();
		
		System.out.println("Enter your Choice: ");
		System.out.println("1. Insert");
		System.out.println("2. Delete");
		System.out.println("3. Update");
		System.out.println("4. Display");
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("FirstJPA");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		switch(sc.nextInt()){
		
		case 1: System.out.println("Enter name and age of employee: ");
		emp.setEmpName(sc.next());
		emp.setAge(sc.nextInt());
		em.persist(emp);
		em.getTransaction().commit();
		em.close();
		 break;
			
		case 2: System.out.println("Enter employee id to delete: ");
		emp = em.find(Employee.class, sc.nextInt());
		em.remove(emp.getEmpId());
		em.getTransaction().commit();
		em.close();
		break;
		
		case 3: System.out.println("Enter employee id to update: ");
		emp = em.find(Employee.class, sc.nextInt());
		emp.setEmpName(sc.nextLine());
		em.getTransaction().commit();
		em.close();
		break;
		
		case 4: System.out.println("Available employees: ");
		Query query = em.createQuery("select e from Employee e");
		System.out.println(query.getResultList().toString());
		em.close();
		break;
		
		default: System.out.println("Invalid Choice");
		}
		
		sc.close();
	}

}
