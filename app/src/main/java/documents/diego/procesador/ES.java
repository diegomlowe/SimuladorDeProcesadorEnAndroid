package documents.diego.procesador;

import android.os.Parcel;
import android.os.Parcelable;

public class ES implements Parcelable {

    private int ti, ut;

    public ES(int ti){

       this.ti=ti;
    }




    public int getTi() {
        return ti;
    }

    public void setTi(int ti) {
        this.ti = ti;
    }

    public int getUt() {
        return ut;
    }

    public void setUt(int ut) {
        this.ut = ut;
    }

    //PARCELABLE MTHDS

    protected ES(Parcel in) {
        ti = in.readInt();
        ut = in.readInt();
    }

    public static final Creator<ES> CREATOR = new Creator<ES>() {
        @Override
        public ES createFromParcel(Parcel in) {
            return new ES(in);
        }

        @Override
        public ES[] newArray(int size) {
            return new ES[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(ti);
        parcel.writeInt(ut);
    }
}
