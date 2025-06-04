package fr.filrougeback.model;

public enum VideoCategory {
    PREPARATION,
    HEALTH,
    ECOLOGY,
    COSMETICS;

    @Override
    public String toString() {
        return this.name().charAt(0) + this.name().substring(1).toLowerCase();
    }
}