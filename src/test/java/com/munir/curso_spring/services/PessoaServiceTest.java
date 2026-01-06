/*
 * package com.munir.curso_spring.services;
 * 
 * import static org.junit.jupiter.api.Assertions.assertEquals; import static
 * org.junit.jupiter.api.Assertions.assertNotNull; import static
 * org.mockito.Mockito.when;
 * 
 * import java.util.Optional;
 * 
 * import org.junit.jupiter.api.BeforeEach; import org.junit.jupiter.api.Test;
 * import org.junit.jupiter.api.TestInstance; import
 * org.junit.jupiter.api.extension.ExtendWith; import org.mockito.InjectMocks;
 * import org.mockito.Mock; import org.mockito.MockitoAnnotations; import
 * org.mockito.junit.jupiter.MockitoExtension;
 * 
 * import com.munir.crud_pessoa.entidades.Pessoa; import
 * com.munir.crud_pessoa.repositories.PessoaRepository; import
 * com.munir.crud_pessoa.services.PessoaService;
 * 
 * @TestInstance(TestInstance.Lifecycle.PER_CLASS)
 * 
 * @ExtendWith(MockitoExtension.class) public class PessoaServiceTest {
 * 
 * Pessoa input;
 * 
 * @Mock PessoaRepository repository;
 * 
 * @InjectMocks private PessoaService pessoaService;
 * 
 * @BeforeEach void setUp() {
 * 
 * input = new Pessoa(); input.setId(1L); input.setNome("Munir");
 * input.setEndereco("Rua A");
 * 
 * MockitoAnnotations.openMocks(this); }
 * 
 * @Test void findById() {
 * 
 * when(repository.findById(1L)).thenReturn(Optional.of(input));
 * 
 * var result = pessoaService.findById(1L);
 * 
 * assertNotNull(result); assertNotNull(result.getId());
 * assertNotNull(result.getLinks()); assertEquals("Munir", result.getNome());
 * assertEquals("Rua A", result.getEndereco());
 * 
 * assertNotNull(result.getLinks() .stream() .anyMatch(link ->
 * link.getRel().value().equals("self") && link.getHref().endsWith("/pessoa/1")
 * && link.getType().equals("GET")));
 * 
 * assertNotNull(result.getLinks() .stream() .anyMatch(link ->
 * link.getRel().value().equals("findAll") && link.getHref().endsWith("/pessoa")
 * && link.getType().equals("GET")));
 * 
 * assertNotNull(result.getLinks() .stream() .anyMatch(link ->
 * link.getRel().value().equals("create") && link.getHref().endsWith("/pessoa")
 * && link.getType().equals("POST")));
 * 
 * assertNotNull(result.getLinks() .stream() .anyMatch(link ->
 * link.getRel().value().equals("update") && link.getHref().endsWith("/pessoa")
 * && link.getType().equals("PUT")));
 * 
 * assertNotNull(result.getLinks() .stream() .anyMatch(link ->
 * link.getRel().value().equals("delete") &&
 * link.getHref().endsWith("/pessoa/1") && link.getType().equals("DELETE"))); }
 * 
 * @Test void save() {
 * 
 * }
 * 
 * @Test void alteraPessoa() {
 * 
 * }
 * 
 * @Test void delete() {
 * 
 * }
 * 
 * @Test void find() {
 * 
 * } }
 */