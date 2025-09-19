package federicopini.entities;

import federicopini.exceptions.*;

import java.util.*;

public class Collection {
        private final List<Game> games = new ArrayList<>();


    public Collection() {
    }

    // validazione generica
    public void validateGame(Game g) {
        if (g instanceof Tabletop tt) {
            if(tt.getPlayers() < 2 || tt.getPlayers() > 10) // check per il numero di giocatori
            { throw new ValidationException("Il numero dei giocatori deve essere tra 2 e 10");
            }
            if (tt.getMinutesToPlay() <= 5) {  // check per la durata minima di una partita per un dato tabletop
                throw new ValidationException("I minuti di gioco devono essere almeno 5");
            }

        }
        if(g.getPrice() <= 0){
            throw new ValidationException("Il prezzo deve essere maggiore di 0");
        }
        if (g.getYear() <1900 || g.getYear() >2030)
        {
            throw new ValidationException("Anno inserito non valido");
        }

    }

    // aggiungi elemento
    public void add(Game g){
        if (games.stream().anyMatch(oG -> oG.getId() == g.getId()))
        {
            throw new DuplicateIdException(g.getId());
        }
        validateGame(g);
        games.add(g);
    }

    // ricerca per id
    public Game findById(int id){
        Optional<Game> r = games.stream().filter(g -> g.getId() == id).findAny();

        if(r.isPresent()){
            return r.get();
        }
        throw new NotFoundException(id);
    }

    // ricerca per prezzo massimo
    public List<Game> findByPrice(double mP) {
        return games.stream().filter(g -> g.getPrice() <= mP).toList();
    }

    // ricerca per numero di giocatori
    public List<Tabletop> findTTByPlayers (int n) {
        return games.stream().filter(g -> g instanceof Tabletop).map(
                g -> (Tabletop) g).filter(t -> t.getPlayers() == n).toList();
    }

    // rimozione per id
    public void removeById (int id)
    {
        Optional <Game> r = games.stream().filter(g -> g.getId() == id).findAny();

        if(r.isPresent()) {
           games.remove(r.get());
        }
        else {
            throw new NotFoundException(id);
        }
    }


    // update tramite id
    // metodo per Tabletop
    public Tabletop updateTabletopById(int id, String newTitle, Double newPrice, Integer newMinutesToPlay) {
        Game g = findById(id);

        if (!(g instanceof Tabletop)) {
            throw new ValidationException("L'ID " + id + " non corrisponde a un Tabletop");
        }

        Tabletop tt = (Tabletop) g;

        if (newTitle != null && !newTitle.isBlank()) {
            tt.setTitle(newTitle);
        }
        if (newPrice != null) {
            tt.setPrice(newPrice);
        }
        if (newMinutesToPlay != null) {
            tt.setMinutesToPlay(newMinutesToPlay);
        }

        validateGame(tt);
        return tt;
    }

    // metodo per Videogame
    public Videogame updateVideogameById(int id, String newTitle, Double newPrice, Integer newLongevity) {
        Game g = findById(id);

        if (!(g instanceof Videogame)) {
            throw new ValidationException("L'ID " + id + " non corrisponde a un Videogame");
        }

        Videogame vg = (Videogame) g;

        if (newTitle != null && !newTitle.isBlank()) {
            vg.setTitle(newTitle);
        }
        if (newPrice != null) {
            vg.setPrice(newPrice);
        }
        if (newLongevity != null) {
            vg.setLongevity(newLongevity);
        }

        validateGame(vg);
        return vg;
    }

   // stats
    public void stats(){
        int nTableTops = games.stream().filter(g -> g instanceof Tabletop).toList().size();
        int nVideoGames = games.stream().filter(g -> g instanceof Videogame).toList().size();

        Optional<Game> mostExpensive = games.stream().max(Comparator.comparing(Game::getPrice));

        double averagePrice = games.stream().mapToDouble(Game::getPrice).average().orElse(0.0);

        System.out.println("Statistiche:");
        System.out.println("Videogiochi: " + nVideoGames);
        System.out.println("Giochi da tavolo: " + nTableTops);
        System.out.println("Prezzo medio: €" + averagePrice);
        System.out.println("Gioco più costoso: " + (mostExpensive.isPresent() ? mostExpensive.get() : "Nessuno"));

    }

}

