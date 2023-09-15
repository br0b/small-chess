package players;

import logic.Move;
import logic.Position;

public abstract class Player {
    private final String name;

    public Player(String name) {
        this.name = name;
    }

    public abstract Move getMove(Position position);

    public String getName() {
        return name;
    }
}
