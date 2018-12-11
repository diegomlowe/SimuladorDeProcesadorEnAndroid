package documents.diego.procesador;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class ExpandableProcesador extends ExpandableGroup {

    int id, ut, utO, so;
    String p;

    public ExpandableProcesador(String title, List items) {
        super(title, items);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUt() {
        return ut;
    }

    public void setUt(int ut) {
        this.ut = ut;
    }

    public int getUtO() {
        return utO;
    }

    public void setUtO(int utO) {
        this.utO = utO;
    }

    public int getSo() {
        return so;
    }

    public void setSo(int so) {
        this.so = so;
    }

    public String getP() {
        return p;
    }

    public void setP(String p) {
        this.p = p;
    }
}
