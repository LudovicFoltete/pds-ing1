package org.ing1.pds;

public class Shop {

    private int id;
    private String name;
    private String category;
    private Location location;
    private int max_area;
    private int min_area;

    public Shop() { }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getCategory() { return category; }

    public void setCategory(String category) { this.category = category; }

    public Location getLocation() { return location; }

    public void setLocation(Location location) { this.location = location; }

    public int getMax_area() { return max_area; }

    public void setMax_area(int max_area) { this.max_area = max_area; }

    public int getMin_area() { return min_area; }

    public void setMin_area(int min_area) { this.min_area = min_area; }
}
