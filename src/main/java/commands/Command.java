package commands;

import manager.CollectionManager;

public abstract class Command {

    protected Object parameter;
    protected final CollectionManager collectionManager;

    public Command(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public void setParameter(Object parameter) {
        this.parameter = parameter;
    }

    public abstract boolean isValid();
    public abstract void execute();
}