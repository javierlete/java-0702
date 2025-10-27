package com.ipartek.formacion.bibliotecas.accesodatos;

import java.util.function.Function;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class DaoJpa {
	public final EntityManagerFactory emf;

	public DaoJpa(String unidadPersistencia) {
		emf = Persistence.createEntityManagerFactory(unidadPersistencia);
	}

	public <R> R ejecutarJpa(Function<EntityManager, R> codigo) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction t = em.getTransaction();

		t.begin();

		R resultado = codigo.apply(em);

		t.commit();

		em.close();

		return resultado;
	}

}
