package br.com.fiap.revisaoapi.service;

import br.com.fiap.revisaoapi.dto.FuncionarioDTO;
import br.com.fiap.revisaoapi.model.Funcionario;
import br.com.fiap.revisaoapi.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FuncionarioService {
    private final FuncionarioRepository funcionarioRepository;
    private static final Pageable customPageable = PageRequest.of(0, 4, Sort.by("ds_cpf").ascending());
    @Autowired
    public FuncionarioService(FuncionarioRepository funcionarioRepository) {this.funcionarioRepository = funcionarioRepository;}

    public Page<FuncionarioDTO> findAll() {
        return funcionarioRepository.findAll(customPageable).map(this::toDTO);
    }


    public FuncionarioDTO findById(Long id) {
        Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
        return funcionario.map(this::toDTO).orElse(null);
    }


    public Funcionario save(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }


    public Funcionario update(Long id, Funcionario funcionario) {
        Optional<Funcionario> funcionarioOptional = funcionarioRepository.findById(id);
        if (funcionarioOptional.isPresent()) {
            Funcionario funcionarioUpdate = funcionarioOptional.get();

            funcionarioUpdate.setNome(funcionario.getNome());
            funcionarioUpdate.setEmail(funcionario.getEmail());
            funcionarioUpdate.setCpf(funcionario.getCpf());
            funcionarioUpdate.setDataAdmissao(funcionario.getDataAdmissao());

            funcionario = funcionarioRepository.save(funcionarioUpdate);
            return funcionario;
        }
        return null;
    }


    public void delete(Long id) {
        Optional<Funcionario> funcionarioOptional = funcionarioRepository.findById(id);
        funcionarioOptional.ifPresent(funcionarioRepository::delete);
    }


    private FuncionarioDTO toDTO(Funcionario funcionario) {
        FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
        funcionarioDTO.setId(funcionario.getId());
        funcionarioDTO.setNome(funcionario.getNome());
        funcionarioDTO.setEmail(funcionario.getEmail());
        funcionarioDTO.setDataAdmissao(funcionario.getDataAdmissao());
        return funcionarioDTO;
    }
}
