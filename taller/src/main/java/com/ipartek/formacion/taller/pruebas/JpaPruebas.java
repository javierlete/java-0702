package com.ipartek.formacion.taller.pruebas;

import java.util.List;

import com.ipartek.formacion.taller.modelos.Usuario;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JpaPruebas {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.ipartek.formacion.taller.modelos");
		EntityManager em = emf.createEntityManager();
		EntityTransaction t = em.getTransaction();
		
		t.begin();
		
		em.persist(Usuario.builder().nombre("Javier").email("javier@email.net").password("javier").build());
		em.persist(Usuario.builder().nombre("Pepe").email("pepe@email.net").password("pepe").build());
		
		List<Usuario> listado = em.createQuery("from Usuario", Usuario.class).getResultList();
		
		listado.forEach(System.out::println);
		
		em.merge(Usuario.builder().id(1L).nombre("Javierito").email("javierito@email.net").password("javierito").build());
		
		System.out.println(em.find(Usuario.class, 1L));
		
		em.remove(em.find(Usuario.class, 2L));
		
		listado = em.createQuery("from Usuario", Usuario.class).getResultList();
		
		listado.forEach(System.out::println);
		
		t.commit();
		
		em.close();
		emf.close();
	}
}
