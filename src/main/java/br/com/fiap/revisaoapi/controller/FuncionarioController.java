package br.com.fiap.revisaoapi.controller;

import br.com.fiap.revisaoapi.dto.FuncionarioDTO;
import br.com.fiap.revisaoapi.model.Funcionario;
import br.com.fiap.revisaoapi.service.FuncionarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/funcionarios", produces = {"application/json"})
@Tag(name = "api-funcionario")
public class FuncionarioController {
    private final FuncionarioService funcionarioService;

    @Autowired
    public FuncionarioController(FuncionarioService funcionarioService) {this.funcionarioService = funcionarioService;}


    @Operation(summary = "Retorna todos os funcionários em páginas de 4")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum funcionário encontrado", content = {
                    @Content(schema = @Schema())
            })
    })
    @GetMapping
    public ResponseEntity<Page<FuncionarioDTO>> findAll() {
        Page<FuncionarioDTO> funcionariosDTO = funcionarioService.findAll();
        if (funcionariosDTO.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            for (FuncionarioDTO funcionarioDTO : funcionariosDTO){
                Long id = funcionarioDTO.getId();
                funcionarioDTO.add(linkTo(methodOn(FuncionarioController.class)
                        .findById(id)).withSelfRel());
            }
        }
        return ResponseEntity.ok(funcionariosDTO);
    }


    @Operation(summary = "Retorna um funcionário específico por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum funcionário encontrado para o id informado", content = {
                    @Content(schema = @Schema())
            })
    })
    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioDTO> findById(@PathVariable Long id) {
        FuncionarioDTO funcionarioDTO = funcionarioService.findById(id);
        if (funcionarioDTO == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            funcionarioDTO.add(linkTo(methodOn(FuncionarioController.class)
                    .findAll()).withRel("Lista de Funcionarios"));
        }
        return ResponseEntity.ok(funcionarioDTO);
    }


    @Operation(summary = "Grava um funcionario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Funcionario gravado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação dos dados", content = {
                    @Content(schema = @Schema())
            })
    })
    @PostMapping
    public ResponseEntity<Funcionario> save(@Valid @RequestBody Funcionario funcionario) {
        Funcionario funcionarioSalvo = funcionarioService.save(funcionario);
        return new ResponseEntity<>(funcionarioSalvo, HttpStatus.CREATED);
    }


    @Operation(summary = "Atualiza um funcionario com base no id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Funcionario atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação dos dados", content = {
                    @Content(schema = @Schema())
            })
    })
    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> update(@PathVariable Long id, @Valid @RequestBody Funcionario funcionario) {
        Funcionario funcionarioSalvo = funcionarioService.update(id, funcionario);
        return new ResponseEntity<>(funcionarioSalvo, HttpStatus.CREATED);
    }


    @Operation(summary = "Exclui um funcionario com base no id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Funcionario excluído com sucesso", content = {
                    @Content(schema = @Schema())
            })
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        funcionarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
