package federicopini;

import federicopini.entities.*;
import federicopini.exceptions.DuplicateIdException;
import federicopini.exceptions.NotFoundException;
import federicopini.exceptions.ValidationException;

import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Collection collezione = new Collection();

        collezione.add(new Videogame(1, "Elden Ring", 2022, 59.99, "PC", 120, Genre.RPG));
        collezione.add(new Videogame(2, "FIFA 23", 2022, 69.99, "PlayStation 5", 500, Genre.SPORT));
        collezione.add(new Videogame(3, "Gran Turismo 7", 2022, 69.99, "PlayStation 5", 200, Genre.DRIVING));
        collezione.add(new Videogame(4, "Zelda: Breath of the Wild", 2017, 59.99, "Nintendo Switch", 150, Genre.ADVENTURE));
        collezione.add(new Videogame(5, "Call of Duty: Modern Warfare II", 2022, 69.99, "Xbox Series X", 80, Genre.ACTION));
        collezione.add(new Videogame(6, "The Witcher 3", 2015, 39.99, "PC", 200, Genre.RPG));
        collezione.add(new Videogame(7, "Assassin’s Creed Valhalla", 2020, 59.99, "PlayStation 5", 120, Genre.ADVENTURE));
        collezione.add(new Videogame(8, "Forza Horizon 5", 2021, 59.99, "Xbox Series X", 300, Genre.DRIVING));
        collezione.add(new Videogame(9, "Dark Souls III", 2016, 49.99, "PC", 100, Genre.ACTION));
        collezione.add(new Videogame(10, "NBA 2K23", 2022, 69.99, "PlayStation 5", 400, Genre.SPORT));

        collezione.add(new Tabletop(11, "Catan", 1995, 39.99, 4, 90));
        collezione.add(new Tabletop(12, "Monopoly", 1935, 29.99, 6, 120));
        collezione.add(new Tabletop(13, "Carcassonne", 2000, 24.99, 5, 45));
        collezione.add(new Tabletop(14, "Pandemic", 2008, 34.99, 4, 60));
        collezione.add(new Tabletop(15, "Ticket to Ride", 2004, 39.99, 5, 75));
        collezione.add(new Tabletop(16, "Risk", 1959, 29.99, 6, 180));
        collezione.add(new Tabletop(17, "Dixit", 2008, 29.99, 6, 30));
        collezione.add(new Tabletop(18, "7 Wonders", 2010, 39.99, 7, 45));
        collezione.add(new Tabletop(19, "Bang!", 2002, 24.99, 7, 40));
        collezione.add(new Tabletop(20, "Twilight Imperium", 1997, 99.99, 8, 480));

        int sel = 10;
        while(sel!=0){

            System.out.println("--- MENU COLLEZIONE ---");
            System.out.println("1 Aggiungi gioco");
            System.out.println("2 Cerca gioco per ID");
            System.out.println("3 Cerca giochi per prezzo massimo");
            System.out.println("4 Cerca Gioco da tavolo per numero di giocatori");
            System.out.println("5 Aggiorna gioco");
            System.out.println("6 Rimuovi gioco per ID");
            System.out.println("7 Mostra statistiche");
            System.out.println("0 Esci");

            sel= Integer.parseInt(sc.nextLine());

            try {
                switch(sel) {
                    case 1 -> {

                        System.out.println("Vuoi inserire un Videogame (1) o un Tabletop (2)?");
                        int tipo = Integer.parseInt(sc.nextLine());

                        while (tipo !=1 && tipo !=2)
                        {
                        System.out.println("Selezione sbagliata, devi inserire (1) o (2)");
                        tipo = Integer.parseInt(sc.nextLine());
                        }

                        System.out.print("ID: ");
                        int id = Integer.parseInt(sc.nextLine());
                        System.out.print("Titolo: ");
                        String title = sc.nextLine();
                        System.out.print("Anno: ");
                        int year = Integer.parseInt(sc.nextLine());
                        System.out.print("Prezzo: ");
                        double price = Double.parseDouble(sc.nextLine());
                        if(tipo==1) {
                            System.out.print("Piattaforma: ");
                            String platform = sc.nextLine();
                            System.out.print("Longevità (ore): ");
                            int longevity = Integer.parseInt(sc.nextLine());
                            System.out.print("Genere (ACTION, RPG, ADVENTURE, SPORT, DRIVING): ");
                            Genre genre = Genre.valueOf(sc.nextLine().toUpperCase());

                            collezione.add(new Videogame(id, title, year, price, platform, longevity, genre));
                            System.out.println("Videogioco aggiunto!");
                        }
                        else {

                                System.out.print("Numero giocatori: ");
                                int players = Integer.parseInt(sc.nextLine());
                                System.out.print("Durata (minuti): ");
                                int minutes = Integer.parseInt(sc.nextLine());

                                collezione.add(new Tabletop(id, title, year, price, players, minutes));
                                System.out.println("Tabletop aggiunto!");
                            }

                        }
                        case 2 -> {
                        System.out.println("ID del gioco da cercare:");
                        int id = Integer.parseInt(sc.nextLine());
                        Game g=collezione.findById(id);
                        System.out.println("Trovato: "+ g);

                        }

                        case 3-> {
                        System.out.println("Prezzo massimo :");
                        double max = Double.parseDouble(sc.nextLine());
                        List<Game> results = collezione.findByPrice(max);
                        if (results.isEmpty()) {
                            System.out.println("Nessun gioco trovoato con prezzo minore o uguale a quello inserito");
                        }
                        else {
                            System.out.println("Risultati :");
                            for (Game g: results) System.out.println(g);
                        }
                        }

                        case 4 -> {
                        System.out.println("Numero preciso di giocatori");
                        int n = Integer.parseInt(sc.nextLine());
                        List<Tabletop> results = collezione.findTTByPlayers(n);
                        if (results.isEmpty()) {
                            System.out.println("Nessun gioco trovato con quel numero di giocatori");
                        } else {
                            System.out.println("Risultati :");
                            for (Tabletop t : results) System.out.println(t);
                        }

                        }

                        case 5 -> {
                            System.out.println("Vuoi aggiornare un Videogame (1) o un Tabletop (2)?");
                            int tipo = Integer.parseInt(sc.nextLine());

                            while (tipo !=1 && tipo !=2)
                            {
                                System.out.println("Selezione sbagliata, devi inserire (1) o (2)");
                                tipo = Integer.parseInt(sc.nextLine());
                            }

                            System.out.print("ID del gioco da aggiornare: ");
                            int id = Integer.parseInt(sc.nextLine());

                            System.out.print("Nuovo titolo (invio per lasciare invariato): ");
                            String title = sc.nextLine();
                            if (title.isBlank()) title = null;

                            System.out.print("Nuovo prezzo (invio per lasciare invariato): ");
                            String priceStr = sc.nextLine().trim();
                            Double price = priceStr.isEmpty() ? null : Double.parseDouble(priceStr);

                            if (tipo == 1) {
                                System.out.print("Nuova longevità (invio per lasciare invariato): ");
                                String lonStr = sc.nextLine().trim();
                                Integer longevity = lonStr.isEmpty() ? null : Integer.parseInt(lonStr);

                                Videogame updated = collezione.updateVideogameById(id, title, price, longevity);
                                System.out.println("Aggiornato: " + updated);
                            } else {
                                System.out.print("Nuovi minuti per partita (invio per lasciare invariato): ");
                                String minStr = sc.nextLine().trim();
                                Integer minutes = minStr.isEmpty() ? null : Integer.parseInt(minStr);

                                Tabletop updated = collezione.updateTabletopById(id, title, price, minutes);
                                System.out.println("Aggiornato: " + updated);
                            }

                        }
                        case 6 -> {
                        System.out.println("ID da rimuovere: ");
                        int id= Integer.parseInt(sc.nextLine());
                        collezione.removeById(id);
                        System.out.println("Gioco rimosso");
                        }
                        case 7 -> collezione.stats();
                        case 0 -> System.out.println("Uscita dal software ");
                        default -> System.out.println("Scelta non valida");
                    }



                } catch (ValidationException | DuplicateIdException | NotFoundException e) {
                System.out.println("Errore: " + e.getMessage());
            }
        }
        sc.close();
    }
}
