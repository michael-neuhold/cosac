package cosac.model;

import java.io.Serializable;

public class SectionData implements Serializable {

    private int id;
    private String name;

    public SectionData(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SectionData that = (SectionData) o;
        return id == that.id;
    }

    @Override
    public String toString() {
        return "SectionData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
