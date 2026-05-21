package trabajo_fis;

import trabajo_fis.usuarios.factory.CreadorUsuario;
import trabajo_fis.usuarios.logica.ControladorUsuario;
import trabajo_fis.usuarios.persistencia.PersistenciaUsuarios;
import trabajo_fis.usuarios.vistas.*;

public class Main {
	public static void main(String[] args) {
		CreadorUsuario creadorUsuario = new CreadorUsuario();
		ControladorUsuario controladorUsuario = new ControladorUsuario(creadorUsuario, new PersistenciaUsuarios(creadorUsuario));

        new VistaPrincipal(controladorUsuario, controladorUsuario, controladorUsuario, creadorUsuario).iniciar();
		//TODO: DAR DE ALTA Y BAJA INSTRUCTOR
	}
}