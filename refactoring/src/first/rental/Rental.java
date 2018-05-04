package first.rental;

import first.movie.Movie;

/**
 * 最初的租凭类
 */
public class Rental {

    //影片
    private Movie _movie;
    //租期
    private int _daysRented;

    public Rental(Movie movie,int daysRented){

        _movie = movie;
        _daysRented = daysRented;
    }

    public Movie get_movie() {
        return _movie;
    }

    public void set_movie(Movie _movie) {
        this._movie = _movie;
    }

    public int get_daysRented() {
        return _daysRented;
    }

    public void set_daysRented(int _daysRented) {
        this._daysRented = _daysRented;
    }

    /**
     * 原amountFor方法,该方法移动到movie中
     * @return
     */
    public double getCharge(){
       return get_movie().getCharge(get_daysRented());
    }

    /**
     * 将customer中的++计算提出来
     * @return
     */
    public int getFrequentRenterPoints(){
        return get_movie().getFrequentRenterPoints(get_daysRented());
    }
}
