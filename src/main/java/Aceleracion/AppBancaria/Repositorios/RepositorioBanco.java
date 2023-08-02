package Aceleracion.AppBancaria.Repositorios;

import Aceleracion.AppBancaria.Entidades.Banco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioBanco extends JpaRepository<Banco, Long> {
}
