package trabajo_fis.usuarios.logica;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mindrot.jbcrypt.BCrypt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import trabajo_fis.usuarios.dominio.Usuario;
import trabajo_fis.usuarios.factory.*;
import trabajo_fis.usuarios.persistencia.IPersistenciaUsuarios;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("Pruebas de Caja Negra - ControladorUsuario")
class ControladorUsuarioTest {

    private ControladorUsuario controlador;
    private IPersistenciaUsuarios persistenciaMock;
    private ICreadorUsuarios factoriaMock;
    private List<Usuario> listaUsuariosSimulada;

    @BeforeEach
    void setUp() throws Exception {
        persistenciaMock = mock(IPersistenciaUsuarios.class);
        factoriaMock = mock(ICreadorUsuarios.class);

        // Simulamos una base de datos vacía al iniciar el controlador
        listaUsuariosSimulada = new ArrayList<>();
        when(persistenciaMock.cargarTodos()).thenReturn(listaUsuariosSimulada);

        controlador = new ControladorUsuario();

        // Inyección por reflexión del Mock de persistencia para evitar escrituras reales en disco
        java.lang.reflect.Field field = ControladorUsuario.class.getDeclaredField("persistenciaUsuarios");
        field.setAccessible(true);
        field.setAccessible(true);
        field.set(controlador, persistenciaMock);

        // Reinyectamos la lista limpia asociada al mock
        java.lang.reflect.Field fieldLista = ControladorUsuario.class.getDeclaredField("usuarios");
        fieldLista.setAccessible(true);
        fieldLista.set(controlador, listaUsuariosSimulada);
    }

    @Nested
    @DisplayName("Pruebas de la funcionalidad: registrarse(...)")
    class RegistroUsuariosTests {

        // Datos robustos que garantizan cumplir las reglas de negocio (Regex alfanumérico estricto y >12 caracteres)
        private HashMap<String, String> crearDatosBaseValidos(String tipo) {
            HashMap<String, String> datos = new HashMap<>();
            datos.put("nickUsuario", "alumnofis2026"); // 13 caracteres, alfanumérico, sin símbolos
            datos.put("contraseña", "Segura12345678"); // 14 caracteres: Mayúscula, Minúscula y Número
            datos.put("correoElectronico", "alta@upm.es");
            datos.put("DNI", "12345678A");
            datos.put("tipoUsuario", tipo);
            return datos;
        }

        @Test
        @DisplayName("CN-REG-01: Registro Exitoso Estudiante UPM (Datos válidos en límites)")
        void testRegistrarse_EstudianteValido_CasoExitoso() {
            HashMap<String, String> datos = crearDatosBaseValidos("estudianteUPM");
            datos.put("nickUsuario", "testupm"); // 7 caracteres (Cumple >=4 y <=12)
            datos.put("tarjetaBancaria", "455712345678");
            datos.put("rolUPM", "Estudiante");
            datos.put("numMatricula", "M21005");

            Usuario usuarioMock = mock(Usuario.class);
            // IMPORTANTE: Aseguramos que la factoría devuelva el objeto simulado cuando tu código lo invoque
            when(factoriaMock.crearUsuario(any(HashMap.class))).thenReturn(usuarioMock);

            controlador.registrarse(factoriaMock, datos);

            // Verificación: Al ser válidos los campos, el flujo debió llamar a .insertar() en la base de datos
            verify(persistenciaMock, times(1)).insertar(usuarioMock);
        }

        @Test
        @DisplayName("CN-REG-04: Error cuando el Nick es demasiado corto (< 4 caracteres)")
        void testRegistrarse_NickCorto_NoRegistra() {
            HashMap<String, String> datos = crearDatosBaseValidos("participanteExterno");
            datos.put("nickUsuario", "abc"); // 3 caracteres: Inválido por límite inferior
            datos.put("tarjetaBancaria", "12345678");

            controlador.registrarse(factoriaMock, datos);

            verify(factoriaMock, never()).crearUsuario(any());
            verify(persistenciaMock, never()).insertar(any());
        }

        @Test
        @DisplayName("CN-REG-05: Error cuando el Nick es demasiado largo (> 12 caracteres)")
        void testRegistrarse_NickLargo_NoRegistra() {
            HashMap<String, String> datos = crearDatosBaseValidos("participanteExterno");
            datos.put("nickUsuario", "nickMaximoTrece"); // 14 caracteres: Límite inválido
            datos.put("tarjetaBancaria", "1234");

            controlador.registrarse(factoriaMock, datos);

            verify(factoriaMock, never()).crearUsuario(any());
        }
        @Test
        @DisplayName("CN-REG-07: Error si el nick se encuentra en la blacklist")
        void testRegistrarse_NickEnBlacklist_NoRegistra() {
            HashMap<String, String> datos = crearDatosBaseValidos("participanteExterno");
            datos.put("nickUsuario", "palabrarestringida"); // Palabra que guardamos en el archivo mock
            datos.put("tarjetaBancaria", "12345678");

            controlador.registrarse(factoriaMock, datos);

            verify(factoriaMock, never()).crearUsuario(any());
        }


        @Test
        @DisplayName("CN-REG-06: Error si el nick contiene caracteres especiales no alfanuméricos")
        void testRegistrarse_NickConSimbologia_NoRegistra() {
            HashMap<String, String> datos = crearDatosBaseValidos("participanteExterno");
            datos.put("nickUsuario", "user_12!");
            datos.put("tarjetaBancaria", "1234");

            controlador.registrarse(factoriaMock, datos);

            verify(factoriaMock, never()).crearUsuario(any());
        }

        @Test
        @DisplayName("CN-REG-08: Error si la contraseña es menor de 12 caracteres")
        void testRegistrarse_ContraseniaCorta_NoRegistra() {
            HashMap<String, String> datos = crearDatosBaseValidos("participanteExterno");
            datos.put("contraseña", "Short123456"); // 11 caracteres
            datos.put("tarjetaBancaria", "1234");

            controlador.registrarse(factoriaMock, datos);

            verify(factoriaMock, never()).crearUsuario(any());
        }

        @Test
        @DisplayName("CN-REG-09: Error si la contraseña no cumple la complejidad (Falta número)")
        void testRegistrarse_ContraseniaSinNumero_NoRegistra() {
            HashMap<String, String> datos = crearDatosBaseValidos("participanteExterno");
            datos.put("contraseña", "ContraseniaSinNumeros");
            datos.put("tarjetaBancaria", "1234");

            controlador.registrarse(factoriaMock, datos);

            verify(factoriaMock, never()).crearUsuario(any());
        }

        @Test
        @DisplayName("CN-REG-10: Error si el correo ya está registrado en el sistema")
        void testRegistrarse_CorreoDuplicado_NoRegistra() {
            // Simulamos que ya existe un usuario con ese correo electrónico
            Usuario usuarioExistente = mock(Usuario.class);
            when(usuarioExistente.getEmail()).thenReturn("alta@upm.es");
            listaUsuariosSimulada.add(usuarioExistente);

            HashMap<String, String> datos = crearDatosBaseValidos("participanteExterno");
            datos.put("tarjetaBancaria", "1234");

            controlador.registrarse(factoriaMock, datos);

            verify(factoriaMock, never()).crearUsuario(any());
        }

        @Test
        @DisplayName("CN-REG-11: Error si el campo DNI es nulo o viene vacío")
        void testRegistrarse_DniVacio_NoRegistra() {
            HashMap<String, String> datos = crearDatosBaseValidos("participanteExterno");
            datos.put("DNI", ""); // Entrada inválida vacía
            datos.put("tarjetaBancaria", "1234");

            controlador.registrarse(factoriaMock, datos);

            verify(factoriaMock, never()).crearUsuario(any());
        }

        @Test
        @DisplayName("CN-REG-12: Error si faltan datos obligatorios para el tipo Estudiante UPM (Falta matrícula)")
        void testRegistrarse_EstudianteFaltaMatricula_NoRegistra() {
            HashMap<String, String> datos = crearDatosBaseValidos("estudianteUPM");
            datos.put("tarjetaBancaria", "1234");
            datos.put("rolUPM", "Estudiante");
            // No añadimos "numMatricula"

            controlador.registrarse(factoriaMock, datos);

            verify(factoriaMock, never()).crearUsuario(any());
        }
    }

    @Nested
    @DisplayName("Pruebas de la funcionalidad: iniciarSesion(...)")
    class LoginUsuariosTests {

        @Test
        @DisplayName("CN-LOG-01: Login Exitoso con datos válidos")
        void testIniciarSesion_CredencialesCorrectas_DevuelveTrue() {
            Usuario usuarioTest = mock(Usuario.class);
            when(usuarioTest.comprobarUsuario("login@upm.es", "Password12345")).thenReturn(true);
            listaUsuariosSimulada.add(usuarioTest);

            boolean resultado = controlador.iniciarSesion("login@upm.es", "Password12345");

            assertTrue(resultado, "El inicio de sesión debería ser exitoso");
        }

        @Test
        @DisplayName("CN-LOG-02: Login fallido debido a contraseña incorrecta")
        void testIniciarSesion_ContraseniaIncorrecta_DevuelveFalse() {
            Usuario usuarioTest = mock(Usuario.class);
            when(usuarioTest.comprobarUsuario("login@upm.es", "WrongPass")).thenReturn(false);
            listaUsuariosSimulada.add(usuarioTest);

            boolean resultado = controlador.iniciarSesion("login@upm.es", "WrongPass");

            assertFalse(resultado, "El inicio de sesión debe denegarse");
        }

        @Test
        @DisplayName("CN-LOG-03: Login fallido debido a que el correo no existe")
        void testIniciarSesion_CorreoNoRegistrado_DevuelveFalse() {
            boolean resultado = controlador.iniciarSesion("inexistente@upm.es", "Password12345");
            assertFalse(resultado);
        }
    }

    @Nested
    @DisplayName("Pruebas de la funcionalidad: altaInstructor(...)")
    class AltaInstructorTests {

        @Test
        @DisplayName("CN-INS-01: Alta de instructor correcta (Contiene IBAN)")
        void testAltaInstructor_ConIbanValido_RegistraCorrectamente() {
            HashMap<String, String> datos = new HashMap<>();
            datos.put("nickUsuario", "instructor1");
            datos.put("contraseña", "InstructorPass1");
            datos.put("correoElectronico", "ins@upm.es");
            datos.put("DNI", "87654321B");
            datos.put("tipoUsuario", "instructor");
            datos.put("IBAN", "ES211465...01");

            Usuario instructorMock = mock(Usuario.class);
            when(factoriaMock.crearUsuario(any())).thenReturn(instructorMock);

            controlador.altaInstructor(factoriaMock, datos);

            verify(persistenciaMock, times(1)).insertar(instructorMock);
            assertTrue(listaUsuariosSimulada.contains(instructorMock));
        }

        @Test
        @DisplayName("CN-INS-02: Error al dar de alta instructor sin campo IBAN")
        void testAltaInstructor_SinIban_NoPermiteRegistro() {
            HashMap<String, String> datos = new HashMap<>();
            datos.put("nickUsuario", "instructor1");
            datos.put("contraseña", "InstructorPass1");
            datos.put("correoElectronico", "ins@upm.es");
            datos.put("DNI", "87654321B");
            datos.put("tipoUsuario", "instructor");
            // Se omite intencionadamente el campo IBAN

            controlador.altaInstructor(factoriaMock, datos);

            verify(persistenciaMock, never()).insertar(any());
        }
    }
}
