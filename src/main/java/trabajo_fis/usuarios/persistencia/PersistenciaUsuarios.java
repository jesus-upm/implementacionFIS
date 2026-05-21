package trabajo_fis.usuarios.persistencia;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import trabajo_fis.usuarios.dominio.Usuario;
import trabajo_fis.usuarios.factory.ICreadorUsuario;

public class PersistenciaUsuarios implements IPersistenciaUsuarios {
   private static final String ARCHIVO = System.getProperty("user.dir") + File.separator + "usuarios.txt";
   private ICreadorUsuario factoria;

   public PersistenciaUsuarios(ICreadorUsuario factoria) { this.factoria = factoria; }

   @Override
   public List<Usuario> cargarTodos() {
      File file = new File(ARCHIVO);
      if (!file.exists()) return new ArrayList<>();

      List<Usuario> usuarios = new ArrayList<>();
      try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO))) {
         String linea;
         while ((linea = br.readLine()) != null) {
            if (linea.trim().isEmpty()) continue;
            String[] partes = linea.split(";");

            HashMap<String, String> datos = new HashMap<>();

            for (int i = 0; i < partes.length - 1; i += 2) {
               String key = partes[i];
               String value = partes[i + 1];

               datos.put(key, value);
            }

            usuarios.add(factoria.crearUsuario(datos));
         }
      } catch (Exception e) {
         System.out.println(e);
      }
      return usuarios;
   }

   @Override
   public void insertar(Usuario usuario) {
      try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO, true))) {
         bw.newLine();
         bw.write(usuario.toString());
      } catch (Exception e) {
         System.out.println(e);
      }
   }

   @Override
   public void borrar(String email) {
      System.out.println("Usuario borrado con email: " + email);
   }

   @Override
   public Usuario seleccionar(String email) {
      System.out.println("Usuario seleccionado con email: " + email);
      return null;
   }

   @Override
   public void actualizar(Usuario usuario) {
      System.out.println("Usuario actualizado: " + usuario.getNickUsuario());
   }
}
