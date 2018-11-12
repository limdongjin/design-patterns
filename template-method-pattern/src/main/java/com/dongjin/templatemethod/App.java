package main.java.com.dongjin.templatemethod;

public class App {
    public static void main(String[] args) {
        House glassHouse = new House(new BuildGlassHouseMethod());
        glassHouse.build();

        House woodenHouse = new House(new BuildWoodenHouseMethod());
        woodenHouse.build();
    }
}