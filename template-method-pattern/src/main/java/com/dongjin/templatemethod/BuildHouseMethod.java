package main.java.com.dongjin.templatemethod;

public abstract class BuildHouseMethod {

    // template method
    public final void buildHouse(){
        buildFoundation();
        buildPillars();
        buildWalls();
        buildWindows();
    }

    private void buildWindows(){
        System.out.println("Build Window!");
    }

    protected abstract void buildWalls();
    protected abstract void buildPillars();

    private void buildFoundation() {
        System.out.println("Building foundation with cement,iron rods and sand");
    }

}