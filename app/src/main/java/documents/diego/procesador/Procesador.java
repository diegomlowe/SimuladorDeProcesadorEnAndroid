package documents.diego.procesador;

import android.os.Parcel;
import android.os.Parcelable;


import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;



public class Procesador extends ExpandableGroup implements Parcelable {

    private ArrayList<ArrayList<String>> tabla;

    private ArrayList<Proceso> procesosList= new ArrayList<>();

    private List<Object> objectList;

    private LinkedList<Proceso> colaListos=new LinkedList<>();

    private LinkedList<Proceso> bloqueadosList = new LinkedList<>();

    private Proceso ejecutando=null;

    private String politica;

    public int count, utOsciosas, utRSO, utEs, id, totalSO, procesosTerminados, desalojo;

    ArrayList<String> filaTiempo = new ArrayList<>();
    ArrayList<String> filaRutinas = new ArrayList<>();
    ArrayList<String> filaEjecutando = new ArrayList<>();
    ArrayList<String> filaColaListos = new ArrayList<>();
    ArrayList<String> filaColaBloqueados = new ArrayList<>();



    public Procesador (String title, List items, int utRSO, int utEs, String politica){
        super(title, items);

        this.id = Integer.valueOf(title);

        this.utRSO=utRSO;
        this.politica=politica;
        this.procesosList=(ArrayList<Proceso>)items;

        tabla = new ArrayList<ArrayList<String>>();


        filaTiempo.add("T");
        filaRutinas.add("Rutinas SO");
        filaEjecutando.add("Ejecutando");
        filaColaListos.add("Cola Listos");
        filaColaBloqueados.add("Bloqueados");

/*

        tabla.add(filaTiempo);
        tabla.add(filaRutinas);
        tabla.add(filaEjecutando);
        tabla.add(filaColaListos);
        tabla.add(filaColaBloqueados);

*/


   }



    public ArrayList<ArrayList<String>> procesar(int desalojo){

       // this.desalojo = desalojo;
        count = 0;
        utOsciosas = 0;
        int utDesalojo = desalojo;
        boolean mcp = false;
        boolean des = false;
        procesosTerminados=0;




        if(politica.equals("Round Robin"))des=true;
        if(politica.equals("MCP"))mcp=true;
        if(politica.equals(("MCP con Desalojo"))){
            mcp=true;
           // des=true;
        }


        while(procesosTerminados<procesosList.size()){



            boolean rutina = false;
            Proceso aux = null;

            if(this.ejecutando==null) { //NO HAY PROCESO EN EJECUCION

                //CARGA PROCESOS A COLA LISTOS

                for (Proceso p : procesosList) {

                    if (!p.getCarga() && p.getTi() <= count+1) { ///////////////////

                        p.setCarga(true);
                        p.setTCarga(count);

                        rutina("P"+p.getId()+" Carga");
                        this.colaListos.addFirst(p);
                        if(mcp)orderColaListos();
                        rutina=true;

                        break;
                    }

                }

                //CAMBIO CONTEXTO: ULTIMO DE COLA LISTOS PASA A PROCESANDO

                if (!rutina && colaListos.size()>0){


                    this.ejecutando=colaListos.getLast();

                    rutina("P"+ejecutando.getId()+" Ej");
                    colaListos.removeLast();
                    if(des)utDesalojo=desalojo;

                    rutina=true;

                // SE DESBLOQUEA ULTIMO DE COLA BLOQUEADOS

                } else if(!rutina && bloqueadosList.size()>0 && bloqueadosList.getLast().getEsList().get(0).getUt()<=0){

                    bloqueadosList.getLast().getEsList().remove(0);
                    aux = bloqueadosList.getLast();
                    bloqueadosList.removeLast();
                    rutina("P"+aux.getId()+" Desb");
                    rutina=true;

                    colaListos.addFirst(aux);

                    if(mcp){

                        colaListos.getFirst().updateRafaga();
                        orderColaListos();

                    }


                    //UNIDAD DE TIEMPO OSCIOSA

                } else if (!rutina){   ////////////////////////////////////////////

                    refreshTableOnEj(0);
                    utOsciosas++;

                }

            } else { //SI HAY PROCESO EN EJECUCION

                //BLOQUEA PROCESO EN EJECUCION

                if(this.ejecutando.getUtList().size()>0) {

                     if (this.ejecutando.getUtList().get(0).equals("es")) {


                         rutina("P" + ejecutando.getId() + " Bloq");
                         rutina = true;
                         this.ejecutando.getUtList().remove(0);
                         this.bloqueadosList.addFirst(ejecutando);
                         this.ejecutando = null;


                     }else if (des && utDesalojo==0 && colaListos.size()>0){

                         //this.ejecutando.getUtList().remove(0);
                         colaListos.addFirst(ejecutando);
                         utDesalojo=desalojo;
                         rutina("P"+colaListos.getFirst().getId()+" Des");
                         rutina=true;
                         this.ejecutando=null;


                         if(mcp){

                             colaListos.getFirst().updateRafaga();
                             orderColaListos();

                         }

                         //EJECUTANDO REFRESH
                    } else {

                            this.ejecutando.getUtList().remove(0);
                            refreshTableOnEj(ejecutando.getId());
                            if(des)utDesalojo--;

                    }

                    //FINALIZA PROCESO EN EJECUCION

                }else if (this.ejecutando.getUtList().size() == 0) {

                    procesosTerminados++;
                    this.ejecutando.settFin(count);
                    rutina("P" + ejecutando.getId() + " Fin");
                    this.ejecutando = null;
                    rutina = true;

                }

            }

            //LISTA BLOQUEADOS REFRESH

            if(bloqueadosList.size()>0){

                for(Proceso p: bloqueadosList){

                    p.getEsList().get(0).setUt(p.getEsList().get(0).getUt()-1);
                    p.settBloq(p.gettBloq()+1);

                }

            }


            if (rutina) count += utRSO;
            else count++;


        }


        tabla.add(filaTiempo);

        tabla.add(filaRutinas);

        tabla.add(filaEjecutando);

        tabla.add(filaColaListos);

        tabla.add(filaColaBloqueados);

        return this.tabla;

    }

    public void rutina(String rutina){

        for(int i=0; i<this.utRSO; i++){



              filaTiempo.add(String.valueOf(filaTiempo.size()));
              filaRutinas.add(rutina);
              filaEjecutando.add(" ");
              printCola(colaListos, filaColaListos);
              printCola(bloqueadosList, filaColaBloqueados);


        }

        totalSO+=utRSO;

    }

    public void printCola (LinkedList<Proceso> cola, ArrayList<String> sList){

        String s ="";

        if(cola.size()>0) {


            for (Proceso p : cola) {

                String id = "P"+String.valueOf(p.getId()+" ");

                s+=id;

            }
        }

        sList.add(s);

    }

    public void refreshTableOnEj (int id){


        filaTiempo.add(String.valueOf(filaTiempo.size()));
        filaRutinas.add(" ");
        if(id!=0)filaEjecutando.add("P"+id);
        else filaEjecutando.add(" ");
        this.printCola(colaListos, filaColaListos);
        this.printCola(bloqueadosList, filaColaBloqueados);





    }

    public ArrayList<Proceso> getProcesosList() {
        return procesosList;
    }

    public ArrayList<ArrayList<String>> getTabla() {
        return tabla;
    }

    public void orderColaListos(){

        if(this.colaListos.size()>1){

            Proceso aux;

            for(int i=0; i<colaListos.size()-1; i++){
                for(int z=0; z<colaListos.size()-1; z++){

                    if(colaListos.get(z).getProxRafaga()<colaListos.get(z+1).getProxRafaga()){

                        aux=colaListos.get(z+1);
                        colaListos.set(z+1, colaListos.get(z));
                        colaListos.set(z, aux);

                    }

                }

            }

        }

    }

    public String getPolitica() {
        return politica;
    }

    public int getCount() {
        return count;
    }

    public int getUtOsciosas() {
        return utOsciosas;
    }

    public int getUtRSO() {
        return utRSO;
    }

    public int getId() {
        return id;
    }

    public int getTotalSO() {
        return totalSO;
    }

    public int getUtEs() {
        return utEs;
    }

    public int getDesalojo() {
        return desalojo;
    }


    //PARCELABLE MTHDS

    protected Procesador(Parcel in) {
        super(in);
        procesosList = in.createTypedArrayList(Proceso.CREATOR);
        ejecutando = in.readParcelable(Proceso.class.getClassLoader());
        politica = in.readString();
        count = in.readInt();
        utOsciosas = in.readInt();
        utRSO = in.readInt();
        utEs = in.readInt();
        id = in.readInt();
        totalSO = in.readInt();
        procesosTerminados = in.readInt();
        desalojo = in.readInt();
        filaTiempo = in.createStringArrayList();
        filaRutinas = in.createStringArrayList();
        filaEjecutando = in.createStringArrayList();
        filaColaListos = in.createStringArrayList();
        filaColaBloqueados = in.createStringArrayList();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeTypedList(procesosList);
        dest.writeParcelable(ejecutando, flags);
        dest.writeString(politica);
        dest.writeInt(count);
        dest.writeInt(utOsciosas);
        dest.writeInt(utRSO);
        dest.writeInt(utEs);
        dest.writeInt(id);
        dest.writeInt(totalSO);
        dest.writeInt(procesosTerminados);
        dest.writeInt(desalojo);
        dest.writeStringList(filaTiempo);
        dest.writeStringList(filaRutinas);
        dest.writeStringList(filaEjecutando);
        dest.writeStringList(filaColaListos);
        dest.writeStringList(filaColaBloqueados);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Procesador> CREATOR = new Creator<Procesador>() {
        @Override
        public Procesador createFromParcel(Parcel in) {
            return new Procesador(in);
        }

        @Override
        public Procesador[] newArray(int size) {
            return new Procesador[size];
        }
    };


}
