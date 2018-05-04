package first.price;

import first.movie.Movie;

public class NewReleasePrice extends Price {
    @Override
    public int getPriceCode() {
        return Movie.NEW_RELRASE;
    }

    @Override
    public double getCharge(int daysRented) {
        double result = 0 ;
        result += 3;
        return result;
    }

    @Override
    public int getFrequentRenterPoints(int dayRenTend){
        return (dayRenTend > 1) ? 2:1;
    }
}
