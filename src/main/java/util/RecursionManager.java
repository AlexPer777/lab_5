package util;

public class RecursionManager {
    private static final DefaultRecursionController controller = new DefaultRecursionController();
    public static DefaultRecursionController getController() {
        return controller;
    }
}