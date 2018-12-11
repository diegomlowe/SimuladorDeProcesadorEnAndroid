package documents.diego.procesador;

import android.graphics.Region;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Proceso implements Parcelable {

    int id, ut, ti, proxRafaga, tFin, tBloq, tCarga;

    ArrayList utList = new ArrayList();

    ArrayList<ES> esList = new ArrayList<>();

    String estado;

    boolean carga;


    public Proceso (int id, int ut, int ti, ArrayList<ES> esList){

      this.id=id;
      this.ut=ut;
      this.ti=ti;
      this.esList=esList;
      this.carga=false;
      this.orderESList();
      this.llenarUTList();
      this.proxRafaga=updateRafaga();
      this.tBloq=0;

   }



    public int updateRafaga(){

        int count=0;

        for(Object o: utList){

            if(o.equals("es"))break;
            else count++;

        }
        return count;
     }

    public void llenarUTList(){

       for(int i=1; i<=this.ut+this.esList.size();i++){

           boolean positivoES=false;

           for(ES es : this.esList){

              if(es.getTi()==i){
                  this.utList.add("es");
                  positivoES=true;
                  break;
              }

          }

          if(!positivoES)this.utList.add(1);

       }


    }

    public int getTCarga() {
        return tCarga;
    }

    public void setTCarga(int tCarga) {
        this.tCarga = tCarga;
    }

    public boolean getCarga(){return carga;}

    public void setCarga(boolean carga){this.carga=carga;}

    public int gettCarga() {
        return tCarga;
    }

    public void settCarga(int tCarga) {
        this.tCarga = tCarga;
    }

    public int getProxRafaga() {
        return proxRafaga;
    }

    public void setProxRafaga(int proxRafaga) {
        this.proxRafaga = proxRafaga;
    }

    public int gettFin() {
        return tFin;
    }

    public void settFin(int tFin) {
        this.tFin = tFin;
    }

    public int gettBloq() {
        return tBloq;
    }

    public void settBloq(int tBloq) {
        this.tBloq = tBloq;
    }

    public boolean isCarga() {
        return carga;
    }

    public ArrayList getUtList() {
        return utList;
    }

    public void setUtList(ArrayList utList) {
        this.utList = utList;
    }

    public ArrayList<ES> getEsList() {
        return esList;
    }

    public void setEsList(ArrayList<ES> esList) {
        this.esList = esList;
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

    public int getTi() {
        return ti;
    }

    public void setTi(int ti) {
        this.ti = ti;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void orderESList(){

        if(this.esList.size()>1){

            ES aux;

            for(int i=0; i<esList.size()-1; i++){
                for(int z=0; z<esList.size()-1; z++){

                   if(esList.get(z).getTi()>esList.get(z+1).getTi()){

                       aux=esList.get(z+1);
                       esList.set(z+1, esList.get(z));
                       esList.set(z, aux);

                   }

                }

            }

        }

    }

    //PARCELABLE MTHDS

    protected Proceso(Parcel in) {
        id = in.readInt();
        ut = in.readInt();
        ti = in.readInt();
        proxRafaga = in.readInt();
        estado = in.readString();
        carga = in.readByte() != 0;
    }

    public static final Creator<Proceso> CREATOR = new Creator<Proceso>() {
        @Override
        public Proceso createFromParcel(Parcel in) {
            return new Proceso(in);
        }

        @Override
        public Proceso[] newArray(int size) {
            return new Proceso[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(ut);
        parcel.writeInt(ti);
        parcel.writeInt(proxRafaga);
        parcel.writeString(estado);
        parcel.writeByte((byte) (carga ? 1 : 0));
    }
}
