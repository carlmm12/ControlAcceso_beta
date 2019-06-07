package co.com.samtel.ControlAccesos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.com.samtel.ControlAccesos.entities.Usuario;



@Repository
public interface IUsuarioDao extends JpaRepository<Usuario, String> {

	 
	@Query("SELECT u FROM Usuario u  where u.cedula = :id")
	Usuario findAllByID(@Param("id") String id);
}
