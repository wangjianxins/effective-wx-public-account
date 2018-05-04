package first.price;

import first.movie.Movie;

public class ChildrensPrice extends Price {


    @Override
    public int getPriceCode() {
        return Movie.CHLLDRENS;
    }

    @Override
    public double getCharge(int daysRented) {
        double result = 0;
        result += 1.5;
        if(daysRented > 3){
            result += (daysRented - 3)*2.5;
        }
        return result;
    }
}
