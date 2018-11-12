package main.java.com.dongjin.templatemethod;

public class BuildGlassHouseMethod extends BuildHouseMethod {
    @Override
    protected void buildWalls() {
        System.out.println("Build Glass Walls!!");
    }

    @Override
    protected void buildPillars() {
        System.out.println("Build Glass Pillars!");
    }
}
