package id.ac.ui.cs.muhammarr.testapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

// Source: https://stackoverflow.com/q/12120569
public class AnimalContent {

    public final static ArrayList<Animal> ITEMS = new ArrayList<>();

    public static class Animal implements Parcelable {
        public String name;
        public int picture;
        public String detail;

        public Animal(){

        }

        public Animal(String name, int picture, String detail) {
            this.name = name;
            this.picture = picture;
            this.detail = detail;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        // Source: https://www.codepolitan.com/menggunakan-parcelable-untuk-pengiriman-data-antar-komponen-di-android-599848cf0f158
        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.name);
            dest.writeInt(this.picture);
            dest.writeString(this.detail);
        }

        protected Animal(Parcel in) {
            this.name = in.readString();
            this.picture = in.readInt();
            this.detail = in.readString();
        }

        public static final Parcelable.Creator<Animal> CREATOR = new Parcelable.Creator<Animal>() {
            @Override
            public Animal createFromParcel(Parcel source) {
                return new Animal(source);
            }

            @Override
            public Animal[] newArray(int size) {
                return new Animal[size];
            }
        };
    }

    static {
        addItems("Kucing", R.drawable.cat, "*meow*");
        addItems("Anjing", R.drawable.dog, "*woof*");
    }

    public static void addItems(String name, int picture, String detail){
        ITEMS.add(new Animal(name, picture, detail));
    }
}
