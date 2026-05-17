package trabajo_fis.usuarios.factory;

import trabajo_fis.usuarios.dominio.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class CreadorUsuario implements ICreadorUsuarios {

    public CreadorUsuario() {
    }

    @Override
    public Usuario crearUsuario(HashMap<String, String> datos) {

        String tipo = datos.get("tipoUsuario");

        switch (tipo) {

            case "instructor":
                return new Instructor(
                        datos.get("nickUsuario"),
                        datos.get("nombreCompleto"),
                        datos.get("correoElectronico"),
                        datos.get("contraseña"),
                        datos.get("DNI"),
                        datos.get("IBAN")
                );

            case "participanteExterno":
                return new ParticipanteExterno(
                        datos.get("nickUsuario"),
                        datos.get("nombreCompleto"),
                        datos.get("correoElectronico"),
                        datos.get("contraseña"),
                        datos.get("DNI"),
                        datos.get("tarjetaBancaria")
                );

            case "estudianteUPM":
                return new EstudianteUPM(
                        datos.get("nickUsuario"),
                        datos.get("nombreCompleto"),
                        datos.get("correoElectronico"),
                        datos.get("contraseña"),
                        datos.get("DNI"),
                        datos.get("tarjetaBancaria"),
                        datos.get("rolUPM"),
                        datos.get("numMatricula")
                );

            case "personalUPM":
                return new PersonalUPM(
                        datos.get("nickUsuario"),
                        datos.get("nombreCompleto"),
                        datos.get("correoElectronico"),
                        datos.get("contraseña"),
                        datos.get("DNI"),
                        datos.get("tarjetaBancaria"),
                        datos.get("rolUPM"),
                        LocalDate.parse(datos.get("fechaAntiguedad"), DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                        Boolean.parseBoolean(datos.get("esPDI"))
                );

            default:
                System.out.println("Tipo de usuario no válido");
                return null;
        }

    }
}
