package jenny.folkloresearch;

import com.telerik.everlive.sdk.core.model.base.DataItem;
import com.telerik.everlive.sdk.core.serialization.ServerProperty;
import com.telerik.everlive.sdk.core.serialization.ServerType;

import java.io.Serializable;

@ServerType("Region")
public class Region extends DataItem implements Serializable {
    @ServerProperty("Name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ServerProperty("Location")
    private String location;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @ServerProperty("Clothes")
    private String clothes;

    public String getClothes() {
        return clothes;
    }

    public void setClothes(String clothes) {
        this.clothes = clothes;
    }

    @ServerProperty("Crafts")
    private String crafts;

    public String getCrafts() {
        return crafts;
    }

    public void setCrafts(String crafts) {
        this.crafts = crafts;
    }

    @ServerProperty("Dances")
    private String dances;

    public String getDances() {
        return dances;
    }

    public void setDances(String dances) {
        this.dances = dances;
    }

    @ServerProperty("Habits")
    private String habits;

    public String getHabits() {
        return habits;
    }

    public void setHabits(String habits) {
        this.habits = habits;
    }

    @ServerProperty("Music")
    private String music;

    public String getMusic() {
        return music;
    }

    public void setMusic(String music) {
        this.music = music;
    }
}