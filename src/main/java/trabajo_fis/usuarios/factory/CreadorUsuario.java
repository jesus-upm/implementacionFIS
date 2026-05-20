package trabajo_fis.usuarios.factory;

import trabajo_fis.usuarios.dominio.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class CreadorUsuario implements ICreadorUsuario {
    @Override
    public Usuario crearUsuario(HashMap<String, String> datos) {
        String tipo = datos.get("tipoUsuario");

        return switch (tipo) {
            case "instructor" -> new Instructor(
                    datos.get("nickUsuario"),
                    datos.get("nombreCompleto"),
                    datos.get("correoElectronico"),
                    datos.get("contraseña"),
                    datos.get("DNI"),
                    datos.get("IBAN")
            );
            case "participanteExterno" -> new ParticipanteExterno(
                    datos.get("nickUsuario"),
                    datos.get("nombreCompleto"),
                    datos.get("correoElectronico"),
                    datos.get("contraseña"),
                    datos.get("DNI"),
                    datos.get("tarjetaBancaria")
            );
            case "estudianteUPM" -> new EstudianteUPM(
                    datos.get("nickUsuario"),
                    datos.get("nombreCompleto"),
                    datos.get("correoElectronico"),
                    datos.get("contraseña"),
                    datos.get("DNI"),
                    datos.get("tarjetaBancaria"),
                    datos.get("numMatricula")
            );
            case "personalUPM" -> new PersonalUPM(
                    datos.get("nickUsuario"),
                    datos.get("nombreCompleto"),
                    datos.get("correoElectronico"),
                    datos.get("contraseña"),
                    datos.get("DNI"),
                    datos.get("tarjetaBancaria"),
                    LocalDate.parse(datos.get("fechaAntiguedad"), DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                    Boolean.parseBoolean(datos.get("esPDI"))
            );
            case "administrador" -> new Administrador(
                    datos.get("nickUsuario"),
                    datos.get("nombreCompleto"),
                    datos.get("correoElectronico"),
                    datos.get("contraseña"),
                    datos.get("telefono")
            );
            default -> {
                System.out.println("Tipo de usuario no válido");
                yield null;
            }
        };

    }
}