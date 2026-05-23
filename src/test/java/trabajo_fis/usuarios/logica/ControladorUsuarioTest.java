package trabajo_fis.usuarios.logica;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import trabajo_fis.usuarios.dominio.Usuario;
import trabajo_fis.usuarios.factory.ICreadorUsuario;
import trabajo_fis.usuarios.factory.CreadorUsuario;
import trabajo_fis.usuarios.persistencia.IPersistenciaUsuarios;
import trabajo_fis.usuarios.persistencia.PersistenciaUsuarios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ControladorUsuarioTest {

    private ControladorUsuario controlador;
    private ICreadorUsuario creadorReal;
    private IPersistenciaUsuarios resistenciaFake;
    private HashMap<String, String> datos;

    @BeforeEach
    public void setUp() {
        creadorReal = new CreadorUsuario();
        resistenciaFake = new PersistenciaUsuarios(creadorReal);
        controlador = new ControladorUsuario(creadorReal, resistenciaFake);

        datos = new HashMap<>();

        // Generamos un DNI único aleatorio cortando el UUID para que no sea excesivamente largo (ej: 1234567a1b2c3d4X)
        String subId = UUID.randomUUID().toString().substring(0, 8);
        datos.put("DNI", "12345" + subId + "X");
        datos.put("nombreCompleto", "Usuario Real " + subId);
    }

    @Test
    public void CP_V01_registroValidoPersonalUPM() {
        String nickUnico = "N" + UUID.randomUUID().toString().substring(0, 8); // Máximo 9 caracteres (válido)

        datos.put("tipoUsuario", "PersonalUPM");
        datos.put("nickUsuario", nickUnico);
        datos.put("contrasena", "Password123456");
        datos.put("correoElectronico", nickUnico + "@upm.es");

        datos.put("tarjetaBancaria", "1234567890123456");
        datos.put("fechaAntiguedad", "2020-01-01");
        datos.put("esPDI", "true");

        controlador.registrarse(datos);

        assertNotNull(controlador.getTipoUsuario(), "El PersonalUPM con @upm.es debería ser válido");
    }

    @Test
    public void CP_V02_registroValidoEstudianteUPM() {
        String nickUnico = "E" + UUID.randomUUID().toString().substring(0, 8);

        datos.put("tipoUsuario", "EstudianteUPM");
        datos.put("nickUsuario", nickUnico);
        datos.put("contrasena", "Password123456");
        datos.put("correoElectronico", nickUnico + "@alumnos.upm.es"); // Corrección: El correo de estudiante debe acabar en @alumnos.upm.es

        datos.put("tarjetaBancaria", "1234567890123456");
        datos.put("numMatricula", "12345");

        controlador.registrarse(datos);

        assertNotNull(controlador.getTipoUsuario(), "El EstudianteUPM con @alumnos.upm.es debería ser válido");
    }

    @Test
    public void CP_V03_registroValidoParticipanteExterno() {
        String nickUnico = "X" + UUID.randomUUID().toString().substring(0, 8);

        datos.put("tipoUsuario", "ParticipanteExterno");
        datos.put("nickUsuario", nickUnico);
        datos.put("contrasena", "Password123456");
        datos.put("correoElectronico", nickUnico + "@gmail.com");

        datos.put("tarjetaBancaria", "1234567890123456");

        controlador.registrarse(datos);

        assertNotNull(controlador.getTipoUsuario(), "Un ParticipanteExterno debería poder usar un correo externo como Gmail");
    }

    @Test
    public void CP_N01_nickDemasiadoCorto() {
        String nickUnico = "A" + UUID.randomUUID().toString().substring(0, 8);

        datos.put("tipoUsuario", "ParticipanteExterno");
        datos.put("nickUsuario", "Ana"); // Provoca el fallo por longitud (3 caracteres)
        datos.put("contrasena", "Password123456");
        datos.put("correoElectronico", nickUnico + "@gmail.com");
        datos.put("tarjetaBancaria", "1234567890123456");

        controlador.registrarse(datos);

        assertNull(controlador.getTipoUsuario(), "No debería registrarse un nick menor a 4 caracteres");
    }

    @Test
    public void CP_N02_nickDemasiadoLargo() {
        String nickUnico = "A" + UUID.randomUUID().toString().substring(0, 8);

        datos.put("tipoUsuario", "ParticipanteExterno");
        datos.put("nickUsuario", "ABCDEFGHIJKLMN"); // Provoca el fallo por longitud (14 caracteres)
        datos.put("contrasena", "Password123456");
        datos.put("correoElectronico", nickUnico + "@gmail.com");
        datos.put("tarjetaBancaria", "1234567890123456");

        controlador.registrarse(datos);

        assertNull(controlador.getTipoUsuario(), "No debería registrarse un nick mayor a 12 caracteres");
    }

    @Test
    public void CP_N03_nickNulo() {
        String nickUnico = "A" + UUID.randomUUID().toString().substring(0, 8);

        datos.put("tipoUsuario", "ParticipanteExterno");
        datos.put("nickUsuario", null);
        datos.put("contrasena", "Password123456");
        datos.put("correoElectronico", nickUnico + "@gmail.com");
        datos.put("tarjetaBancaria", "1234567890123456");

        controlador.registrarse(datos);

        assertNull(controlador.getTipoUsuario(), "No debería registrarse un nick nulo");
    }

    @Test
    public void CP_N04_contrasenaCorta() {
        String nickUnico = "N" + UUID.randomUUID().toString().substring(0, 8);

        datos.put("tipoUsuario", "ParticipanteExterno");
        datos.put("nickUsuario", nickUnico);
        datos.put("contrasena", "Hola");
        datos.put("correoElectronico", nickUnico + "@gmail.com");
        datos.put("tarjetaBancaria", "1234567890123456");

        controlador.registrarse(datos);

        assertNull(controlador.getTipoUsuario(), "La contraseña debe tener al menos 12 caracteres");
    }

    @Test
    public void CP_N05_contrasenaNula() {
        String nickUnico = "N" + UUID.randomUUID().toString().substring(0, 8);

        datos.put("tipoUsuario", "ParticipanteExterno");
        datos.put("nickUsuario", nickUnico);
        datos.put("contrasena", null);
        datos.put("correoElectronico", nickUnico + "@gmail.com");
        datos.put("tarjetaBancaria", "1234567890123456");

        controlador.registrarse(datos);

        assertNull(controlador.getTipoUsuario(), "La contraseña no puede ser nula");
    }

    @Test
    public void CP_N06_contrasenaSinMayuscula() {
        String nickUnico = "N" + UUID.randomUUID().toString().substring(0, 8);

        datos.put("tipoUsuario", "ParticipanteExterno");
        datos.put("nickUsuario", nickUnico);
        datos.put("contrasena", "password123456");
        datos.put("correoElectronico", nickUnico + "@gmail.com");
        datos.put("tarjetaBancaria", "1234567890123456");

        controlador.registrarse(datos);

        assertNull(controlador.getTipoUsuario(), "La contraseña debe incluir al menos una mayúscula");
    }

    @Test
    public void CP_N07_contrasenaSinMinuscula() {
        String nickUnico = "N" + UUID.randomUUID().toString().substring(0, 8);

        datos.put("tipoUsuario", "ParticipanteExterno");
        datos.put("nickUsuario", nickUnico);
        datos.put("contrasena", "PASSWORD123456");
        datos.put("correoElectronico", nickUnico + "@gmail.com");
        datos.put("tarjetaBancaria", "1234567890123456");

        controlador.registrarse(datos);

        assertNull(controlador.getTipoUsuario(), "La contraseña debe incluir al menos una minúscula");
    }

    @Test
    public void CP_N08_contrasenaSinNumero() {
        String nickUnico = "N" + UUID.randomUUID().toString().substring(0, 8);

        datos.put("tipoUsuario", "ParticipanteExterno");
        datos.put("nickUsuario", nickUnico);
        datos.put("contrasena", "PasswordCharms");
        datos.put("correoElectronico", nickUnico + "@gmail.com");
        datos.put("tarjetaBancaria", "1234567890123456");

        controlador.registrarse(datos);

        assertNull(controlador.getTipoUsuario(), "La contraseña debe incluir al menos un número");
    }

    @Test
    public void CP_N09_estudianteNoPuedeUsarGmail() {
        String nickUnico = "E" + UUID.randomUUID().toString().substring(0, 8);

        datos.put("tipoUsuario", "EstudianteUPM");
        datos.put("nickUsuario", "NickEstuGmail" + nickUnico);
        datos.put("contrasena", "Password123456");
        datos.put("correoElectronico", "alumno" + nickUnico + "@gmail.com");

        datos.put("tarjetaBancaria", "1234567890123456");
        datos.put("numMatricula", "5432" + nickUnico);

        controlador.registrarse(datos);

        assertNull(controlador.getTipoUsuario(), "Un estudiante de la UPM no puede registrarse con un correo externo");
    }

    @Test
    public void CP_N10_personalNoPuedeUsarGmail() {
        String nickUnico = "P" + UUID.randomUUID().toString().substring(0, 8);
            datos.put("tipoUsuario", "PersonalUPM");
            datos.put("nickUsuario", "NickPersGmail" + nickUnico);
            datos.put("contrasena", "Password123456");
            datos.put("correoElectronico", "profesor" + nickUnico + "@gmail.com");

            datos.put("tarjetaBancaria", "1234567890123456");
            datos.put("fechaAntiguedad", "2020-01-01");
            datos.put("esPDI", "true");

            controlador.registrarse(datos);

            assertNull(controlador.getTipoUsuario(), "El personal de la UPM no puede registrarse con un correo externo");
        }
}