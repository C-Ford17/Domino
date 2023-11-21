import java.util.List;

public class Jugador {
    private List<Ficha> fichas;
    private boolean turno;

    public Jugador(List<Ficha> fichas) {
        this.fichas = fichas;
        this.turno = false;
    }

    public List<Ficha> getFichas() {
        return fichas;
    }

    public boolean addFicha(Ficha ficha){
        return fichas.add(ficha);
    }

    public void setTurno(boolean turno) {
        this.turno = turno;
    }

    public boolean isTurno() {
        return turno;
    }
}
