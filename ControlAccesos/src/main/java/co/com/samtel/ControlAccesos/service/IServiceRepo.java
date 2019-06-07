package co.com.samtel.ControlAccesos.service;

import java.util.List;
import java.util.Optional;



public interface IServiceRepo<T,PK> {
	
	
	
	void save(T entity);
	Boolean saveAll(List<T> list);
	Optional<T> findById(Integer id);
	boolean existsById(PK id);
	List<T> findAll();
	long count();
	public void deleteById(PK id); 
	void delete(T entity);
	
}
