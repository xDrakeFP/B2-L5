package federicopini.entities;

public class Videogame extends Game {
    private final String platform;
    private int longevity;
    private final Genre genre;

    public Videogame(int id, String title, int year, double price, String platform, int longevity, Genre genre) {
        super(id, title, year, price);
        this.platform = platform;
        this.longevity = longevity;
        this.genre = genre;
    }

    public String getPlatform() {
        return platform;
    }

    public int getLongevity() {
        return longevity;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setLongevity(int longevity) {
        this.longevity = longevity;
    }

    @Override
    public String toString() {
        return "Videogame{" +
                "platform='" + platform + '\'' +
                ", longevity=" + longevity +
                ", genre=" + genre +
                "} " + super.toString();
    }
}
