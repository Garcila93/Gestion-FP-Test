package com.salesianostriana.edu.proyecto;

import com.salesianostriana.edu.proyecto.modelo.Alumno;
import com.salesianostriana.edu.proyecto.modelo.Usuario;
import com.salesianostriana.edu.proyecto.repositorio.UsuarioRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class UsuarioRepositoryTestAdri {

    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    private TestEntityManager entityManager;

    static Usuario a1;

    @BeforeAll
    public static void inicializarUsuario(){
        a1 = new Alumno("prueba", "prueba", "prueba", null);
    }


    /*
    * Probamos a guardar un alumno y comprobamos que si lo devuelve
    * es porque lo ha guardado con éxito
    * */
    @Test
    @DisplayName("Probar guardar alumno")
    public void probarGuardaAlumno(){
        Usuario u1 = new Alumno("email", "nombre", "apellidos", null);
        entityManager.persist(u1);
        assertNotEquals(Optional.empty() ,usuarioRepository.save(u1));
    }

    /*
     * Probamos a eliminar un alumno y comprobamos buscandolo
     * en la lista porque lo ha borrado con éxito
     * */
    @Test
    @DisplayName("Probar eliminar alumno")
    public void eliminarAlumno(){
        usuarioRepository.save(a1);
        usuarioRepository.delete(a1);
        assertEquals(Optional.empty() ,usuarioRepository.findFirstByEmail("prueba"));
    }

    /*
     * Probamos a encontrar todos los alumnos
     **/
    @Test
    @DisplayName("Probar encontrar todos los alumnos")
    public void encontrarTodosLosAlumnos(){
        usuarioRepository.save(a1);
        assertEquals(List.of(a1) ,usuarioRepository.findAll());
    }

    /*
     * Probamos a encontrar un alumno por email
     **/
    @Test
    @DisplayName("Probar encontrar un alumno por email")
    public void encontrarAlumnoPorEmail(){
        usuarioRepository.save(a1);
        assertEquals(a1 ,usuarioRepository.findFirstByEmail("prueba"));
    }

    /*
     * Probamos a encontrar el alumno por email (estando
     * el repositorio vacio)
     **/
    @Test
    @DisplayName("Probamos a encontrar el alumno por email de un repositorio vacio")
    public void encontrarAlumnoListaVaciaPorEmail(){
        assertEquals(Optional.empty(), usuarioRepository.findFirstByEmail("prueba"));
    }

    /*
     * Probamos a encontrar el alumno de un email que es un string vacio
     **/
    @DisplayName("Probamos a encontrar el alumno de un email que es un string vacio")
    @Test
    public void encontrarAlumnoPorEmailVacio(){
        assertEquals(Optional.empty() ,usuarioRepository.findFirstByEmail(""));
    }

    /*
     * Probamos a encontrar el alumno por su id
     **/
    @DisplayName("Probamos a encontrar el alumno por su id")
    @Test
    public void encontrarAlumnoPorId(){
        a1.setId(Long.parseLong("1"));
        usuarioRepository.save(a1);
        assertEquals(Optional.of(a1)  , usuarioRepository.findById(a1.getId()));
    }

    /*
     * Probamos a eliminar todos de la lista
     **/
    @DisplayName("Probamos a eliminar todos de la lista")
    @Test
    public void eliminarTodos(){
        usuarioRepository.save(a1);
        usuarioRepository.deleteAll();
        assertEquals(List.of(), usuarioRepository.findAll());
    }



}
