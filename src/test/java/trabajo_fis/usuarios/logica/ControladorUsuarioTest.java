package trabajo_fis.usuarios.logica;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import trabajo_fis.usuarios.dominio.Usuario;
import trabajo_fis.usuarios.factory.ICreadorUsuario;
import trabajo_fis.usuarios.persistencia.IPersistenciaUsuarios;

import java.util.ArrayList;
import java.util.HashMap;

// Importación estática estricta de assertTrue
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ControladorUsuarioTest {

    private ControladorUsuario controlador;
    private ICreadorUsuario creadorMock;
    private IPersistenciaUsuarios persistenciaMock;
    private HashMap<String, String> datos;

    @BeforeEach
    public void setUp() {
        creadorMock = mock(ICreadorUsuario.class);
        persistenciaMock = mock(IPersistenciaUsuarios.class);

        when(persistenciaMock.cargarTodos()).thenReturn(new ArrayList<>());
        controlador = new ControladorUsuario(creadorMock, persistenciaMock);

        Usuario usuarioMock = mock(Usuario.class);
        when(creadorMock.crearUsuario(any(HashMap.class))).thenReturn(usuarioMock);

        datos = new HashMap<>();
        datos.put("DNI", "12345678X");
        datos.put("tipoUsuario", "ParticipanteExterno");
    }

    @Test
    public void CP_V01_registroValidoDominioUPM() {
        datos.put("nickUsuario", "Mariolo");
        datos.put("contrasena", "Password123456");
        datos.put("correoElectronico", "test@upm.es");

        controlador.registrarse(datos);

        assertTrue(controlador.getTipoUsuario() != null, "El registro con @upm.es debería ser válido");
    }

    @Test
    public void CP_V02_registroValidoDominioAlumnos() {
        datos.put("nickUsuario", "Mariolo");
        datos.put("contrasena", "Password123456");
        datos.put("correoElectronico", "alumno@alumnos.upm.es");

        controlador.registrarse(datos);

        assertTrue(controlador.getTipoUsuario() != null, "El registro con @alumnos.upm.es debería ser válido");
    }

    // ==========================================
    // CASOS DE PRUEBA INVÁLIDOS (CP_N)
    // El usuario NO debe registrarse (getTipoUsuario() == null)
    // ==========================================

    @Test
    public void CP_N01_nickDemasiadoCorto() {
        datos.put("nickUsuario", "Ana");
        datos.put("contrasena", "Password123456");
        datos.put("correoElectronico", "test@upm.es");

        controlador.registrarse(datos);

        assertTrue(controlador.getTipoUsuario() == null, "No debería registrarse un nick menor a 4 caracteres");
    }

    @Test
    public void CP_N02_nickDemasiadoLargo() {
        datos.put("nickUsuario", "ABCDEFGHIJKLMN");
        datos.put("contrasena", "Password123456");
        datos.put("correoElectronico", "test@upm.es");

        controlador.registrarse(datos);

        assertTrue(controlador.getTipoUsuario() == null, "No debería registrarse un nick mayor a 12 caracteres");
    }

    @Test
    public void CP_N03_nickNulo() {
        datos.put("nickUsuario", null);
        datos.put("contrasena", "Password123456");
        datos.put("correoElectronico", "test@upm.es");

        controlador.registrarse(datos);

        assertTrue(controlador.getTipoUsuario() == null, "No debería registrarse un nick nulo");
    }

    @Test
    public void CP_N04_contrasenaCorta() {
        datos.put("nickUsuario", "Mariolo");
        datos.put("contrasena", "Hola");
        datos.put("correoElectronico", "test@upm.es");

        controlador.registrarse(datos);

        assertTrue(controlador.getTipoUsuario() == null, "La contraseña debe tener al menos 12 caracteres");
    }

    @Test
    public void CP_N05_contrasenaNula() {
        datos.put("nickUsuario", "Mariolo");
        datos.put("contrasena", null);
        datos.put("correoElectronico", "test@upm.es");

        controlador.registrarse(datos);

        assertTrue(controlador.getTipoUsuario() == null, "La contraseña no puede ser nula");
    }

    @Test
    public void CP_N06_contrasenaSinMayuscula() {
        datos.put("nickUsuario", "Mariolo");
        datos.put("contrasena", "password123456");
        datos.put("correoElectronico", "test@upm.es");

        controlador.registrarse(datos);

        assertTrue(controlador.getTipoUsuario() == null, "La contraseña debe incluir al menos una mayúscula");
    }

    @Test
    public void CP_N07_contrasenaSinMinuscula() {
        datos.put("nickUsuario", "Mariolo");
        datos.put("contrasena", "PASSWORD123456");
        datos.put("correoElectronico", "test@upm.es");

        controlador.registrarse(datos);

        assertTrue(controlador.getTipoUsuario() == null, "La contraseña debe incluir al menos una minúscula");
    }

    @Test
    public void CP_N08_contrasenaSinNumero() {
        datos.put("nickUsuario", "Mariolo");
        datos.put("contrasena", "PasswordCharms");
        datos.put("correoElectronico", "test@upm.es");

        controlador.registrarse(datos);

        assertTrue(controlador.getTipoUsuario() == null, "La contraseña debe incluir al menos un número");
    }

    @Test
    public void CP_N09_correoDominioIncorrecto() {
        datos.put("nickUsuario", "Mariolo");
        datos.put("contrasena", "Password123456");
        datos.put("correoElectronico", "hola@gmail.com");

        controlador.registrarse(datos);

        assertTrue(controlador.getTipoUsuario() == null, "El correo electrónico debe pertenecer a la UPM");
    }
}