package cosac.model;

import java.io.*;
import java.util.Objects;

public class FoodData implements Serializable {

    private int id;
    private int sectionId;
    private String name;

    public FoodData(int id, int sectionId, String name) {
        this.id = id;
        this.sectionId = sectionId;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getSectionId() {
        return sectionId;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FoodData foodData = (FoodData) o;
        return id == foodData.id;
    }

    @Override
    public String toString() {
        return "FoodData{" +
                "id=" + id +
                ", sectionId=" + sectionId +
                ", name='" + name + '\'' +
                '}';
    }
}
