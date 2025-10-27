package com.ipartek.formacion.bibliotecas.accesodatos;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Function;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class DaoJpa<T> implements Dao<T> {
	public final EntityManagerFactory emf;
	public final Class<T> clase;
	
	public DaoJpa(String unidadPersistencia, Class<T> clase) {
		emf = Persistence.createEntityManagerFactory(unidadPersistencia);
		this.clase = clase;
	}
	
	@Override
	public Collection<T> obtenerTodos() {
		return ejecutarJpa(em -> em.createQuery("from " + clase.getName(), clase).getResultList());
	}

	@Override
	public Optional<T> obtenerPorId(Long id) {
		return ejecutarJpa(em -> Optional.ofNullable(em.find(clase, id)));
	}

	@Override
	public T insertar(T objeto) {
		return ejecutarJpa(em -> {
			em.persist(objeto);
			return objeto;
		});
	}

	@Override
	public T modificar(T objeto) {
		return ejecutarJpa(em -> {
			em.merge(objeto);
			return objeto;
		});
	}

	@Override
	public void borrar(Long id) {
		ejecutarJpa(em -> {
			em.remove(em.find(clase, id));
			return null;
		});
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
