package main.java.com.dongjin.templatemethod;

public class BuildWoodenHouseMethod extends BuildHouseMethod {
    @Override
    protected void buildWalls() {
        System.out.println("Build Wooden Walls!");
    }

    @Override
    protected void buildPillars() {
        System.out.println("Build Wooden Pillars!");
    }
}
