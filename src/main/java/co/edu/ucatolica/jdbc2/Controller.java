package co.edu.ucatolica.jdbc2;

import co.edu.ucatolica.jdbc2.dao.UserJDBC;
import co.edu.ucatolica.jdbc2.domain.User;
import co.edu.ucatolica.jdbc2.util.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api")
public class Controller {
    private UserJDBC jdbc;

    @GetMapping("/list")
    public ResponseEntity<Response> getUsuarios() {
        List<User> users = jdbc.listar();

        return  new ResponseEntity<Response>(
                Response.builder()
                        .timeStampo(LocalDateTime.now())
                        .message("Data retrieved succesful.")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .data(Map.of("usuarios",users))
                        .build()
                , HttpStatus.OK
        );
    }

    @GetMapping("get/{id}")
    public ResponseEntity<Response> getUsuarioById(@PathVariable Integer id) {

            User usuario = jdbc.encontrar(new User(id));

            if (usuario != null){
                return  new ResponseEntity<Response>(
                        Response.builder()
                                .timeStampo(LocalDateTime.now())
                                .message("Data retrieved succesful.")
                                .status(HttpStatus.OK)
                                .statusCode(HttpStatus.OK.value())
                                .data(Map.of("usuario",usuario))
                                .build()
                        , HttpStatus.OK
                );
            }

            return  new ResponseEntity<Response>(
                    Response.builder()
                            .timeStampo(LocalDateTime.now())
                            .message("User id not found.")
                            .status(HttpStatus.NOT_FOUND)
                            .statusCode(HttpStatus.NOT_FOUND.value())
                            .build()
                    , HttpStatus.NOT_FOUND
            );

    }

    @PostMapping("/")
    public ResponseEntity<Response> createUsuario(@RequestBody User usuario) {
        int persisted = jdbc.insertar(usuario);

        if (persisted == 1) {
            return  new ResponseEntity<Response>(
                    Response.builder()
                            .timeStampo(LocalDateTime.now())
                            .message("Data persited succesful.")
                            .status(HttpStatus.CREATED)
                            .statusCode(HttpStatus.CREATED.value())
                            .build()
                    , HttpStatus.CREATED
            );
        } else {
            return  new ResponseEntity<Response>(
                    Response.builder()
                            .timeStampo(LocalDateTime.now())
                            .message("Data incorrect format.")
                            .status(HttpStatus.BAD_REQUEST)
                            .statusCode(HttpStatus.BAD_REQUEST.value())
                            .build()
                    , HttpStatus.BAD_REQUEST
            );
        }
    }

    @PutMapping("/")
    public ResponseEntity<Response> updateUsuario(@RequestBody User usuario) {
        int result = jdbc.actualizar(usuario);
        if (result == 1) {
            return  new ResponseEntity<Response>(
                    Response.builder()
                            .timeStampo(LocalDateTime.now())
                            .message("Data persited succesful.")
                            .status(HttpStatus.ACCEPTED)
                            .statusCode(HttpStatus.ACCEPTED.value())
                            .build()
                    , HttpStatus.ACCEPTED
            );
        } else {
            return  new ResponseEntity<Response>(
                    Response.builder()
                            .timeStampo(LocalDateTime.now())
                            .message("Data incorrect format.")
                            .status(HttpStatus.BAD_REQUEST)
                            .statusCode(HttpStatus.BAD_REQUEST.value())
                            .build()
                    , HttpStatus.BAD_REQUEST
            );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteUsuario(@PathVariable Integer id) {
        int result = jdbc.eliminar(new User(id));
        if (result == 1) {
            return  new ResponseEntity<Response>(
                    Response.builder()
                            .timeStampo(LocalDateTime.now())
                            .message("Data persited succesful.")
                            .status(HttpStatus.ACCEPTED)
                            .statusCode(HttpStatus.ACCEPTED.value())
                            .build()
                    , HttpStatus.ACCEPTED
            );
        } else {
            return  new ResponseEntity<Response>(
                    Response.builder()
                            .timeStampo(LocalDateTime.now())
                            .message("Data incorrect format.")
                            .status(HttpStatus.BAD_REQUEST)
                            .statusCode(HttpStatus.BAD_REQUEST.value())
                            .build()
                    , HttpStatus.BAD_REQUEST
            );
        }
    }
}
