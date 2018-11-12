package main.java.com.dongjin.templatemethod;

public class House {
    private BuildHouseMethod build_method;

    public House(BuildHouseMethod build_method) {
        this.build_method = build_method;
    }

    public void build(){
        build_method.buildHouse();
    }

}