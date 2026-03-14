package model;

import org.jspecify.annotations.NonNull;

public class Car implements Comparable<Car> {
    @NonNull String name; //Поле не может быть null
    @NonNull Boolean cool; //Поле не может быть null
    public  Car(@NonNull String name, @NonNull Boolean cool) {
        this.name = name;
        this.cool = cool;
    }
    public @NonNull String getName() {
        return name;
    }
    public @NonNull Boolean getCool() {
        return cool;
    }
    public void setCool(@NonNull Boolean cool) {
        this.cool = cool;
    }
    public void setName(@NonNull String name) {
        this.name = name;
    }
    @Override
    public int compareTo(@NonNull Car o) {
        if (o == null) return 1;
        return name.compareTo(o.name);
    }
}