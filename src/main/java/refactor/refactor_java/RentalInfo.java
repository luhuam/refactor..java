package refactor.refactor_java;

import java.util.HashMap;

public class RentalInfo {
    public String statement(Customer customer) {
        HashMap<String, Movie> movies = new HashMap<>();
        movies.put("F001", new Movie("You've Got Mail", "regular"));
        movies.put("F002", new Movie("Matrix", "regular"));
        movies.put("F003", new Movie("Cars", "childrens"));
        movies.put("F004", new Movie("Fast & Furious X", "new"));
        double totalAmount = 0;
        int frequentEnterPoints = 0;
        StringBuilder result = new StringBuilder("Rental Record for " + customer.getName() + "\n");

        for (MovieRental r : customer.getRentals()) {
            double thisAmount = calculateAmount(movies.get(r.getMovieId()), r.getDays());
            frequentEnterPoints += calculateFrequentEnterPoints(movies.get(r.getMovieId()), r.getDays());
            
            result.append("\t").append(movies.get(r.getMovieId()).getTitle()).append("\t").append(thisAmount).append("\n");
            totalAmount += thisAmount;
        }
        
        return result.toString();
    }
    
    private double calculateAmount(Movie movie, int rentalDays) {
        double amount = 0;
        String code = movie.getCode();

        if (code.equals("regular")) {
            amount = 2;
            if (rentalDays > 2) {
                amount += (rentalDays - 2) * 1.5;
            }
        } else if (code.equals("new")) {
            amount = rentalDays * 3;
        } else if (code.equals("childrens")) {
            amount = 1.5;
            if (rentalDays > 3) {
                amount += (rentalDays - 3) * 1.5;
            }
        }
        
        return amount;
    }
    
    private int calculateFrequentEnterPoints(Movie movie, int rentalDays) {
        if (movie.getCode().equals("new") && rentalDays > 2) {
            return 2;
        } else {
            return 1;
        }
    }
}

