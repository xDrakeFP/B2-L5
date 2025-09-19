package federicopini.entities;

public class Tabletop extends Game{
    private final int players;
    private int minutesToPlay;

    public Tabletop(int id, String title, int year, double price, int players, int minutesToPlay) {
        super(id, title, year, price);
        this.players = players;
        this.minutesToPlay = minutesToPlay;
    }

    public int getPlayers() {
        return players;
    }

    public int getMinutesToPlay() {
        return minutesToPlay;
    }

    public void setMinutesToPlay(int minutesToPlay) {
        this.minutesToPlay = minutesToPlay;
    }

    @Override
    public String toString() {
        return "Tabletop{" +
                "players=" + players +
                ", minutesToPlay=" + minutesToPlay +
                "} " + super.toString();
    }
}
