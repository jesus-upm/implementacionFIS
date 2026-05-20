package trabajo_fis.usuarios.dominio;

public class PreferenciaArtistica {
    private TipoDisciplina tipo;
    private int nivelExperiencia;

    // Constructor
    public PreferenciaArtistica(TipoDisciplina nombre, int nivelExperiencia) {
        this.tipo = nombre;
        this.nivelExperiencia = nivelExperiencia;
    }

    public int getNivelExperiencia() {
        return nivelExperiencia;
    }
    public void setNivelExperiencia(int nivelExperiencia) {
        this.nivelExperiencia = nivelExperiencia;
    }
    public TipoDisciplina getTipo() {
        return tipo;
    }
    public void setTipo(TipoDisciplina tipo) {this.tipo = tipo;}
}