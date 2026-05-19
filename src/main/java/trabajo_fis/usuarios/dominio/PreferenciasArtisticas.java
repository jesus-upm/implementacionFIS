package trabajo_fis.usuarios.dominio;

public class PreferenciasArtisticas {

    private TipoDisciplina nombre;
    private int nExp;

    // Constructor
    public PreferenciasArtisticas(TipoDisciplina nombre, int nExp) {
        this.nombre = nombre;
        this.nExp = nExp;
    }

    public void cambiarPreferencia(int nExpNuevo) {
        this.nExp = nExpNuevo;
    }

    public TipoDisciplina getNombre() {
        return nombre;
    }

    public int getNExp() {
        return nExp;
    }
}