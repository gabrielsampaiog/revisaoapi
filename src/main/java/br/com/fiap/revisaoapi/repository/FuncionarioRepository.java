package br.com.fiap.revisaoapi.repository;
import br.com.fiap.revisaoapi.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
}
