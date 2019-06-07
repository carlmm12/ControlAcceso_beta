package co.com.samtel.ControlAccesos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import co.com.samtel.ControlAccesos.entities.CodigoUsuario;

public interface ICodigoUsuario extends JpaRepository<CodigoUsuario, Integer> {
	
	@Query (value="SELECT * FROM tblcodigo_usuarios WHERE codigo = :codigo", nativeQuery=true)
	@Transactional
	CodigoUsuario findByCode(@Param("codigo") Integer codigo);
	
	@Modifying
	@Query (value="INSERT INTO controlacceso.tblcodigo_usuarios (codigo, cedula) VALUES(:cod, 999999999)", nativeQuery=true)
	@Transactional
	void insertCodigo(@Param("cod") Integer codigo);
	
	@Query ("FROM CodigoUsuario c inner join fetch c.tblusuario WHERE c.codigo = :codigo")
	@Transactional
	CodigoUsuario findByCod(@Param("codigo") Integer codigo);
	
	

}
