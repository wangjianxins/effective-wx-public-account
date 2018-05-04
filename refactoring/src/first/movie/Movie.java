package first.movie;

import first.price.ChildrensPrice;
import first.price.NewReleasePrice;
import first.price.Price;
import first.price.RegularPrice;

/**
 * 简单的电影类
 */
public class Movie {

    /**
     * 电影的类型
     */
    public static final int CHLLDRENS = 2;
    public static final int REGULAR = 0;
    public static final int NEW_RELRASE = 1;

    private String _title ;
    private int _priceCode;

    //new
    private Price _price;

    public Movie(String title,int priceCode){
        _title = title;
        set_priceCode(priceCode);
    }

    public static int getCHLLDRENS() {
        return CHLLDRENS;
    }

    public static int getREGULAR() {
        return REGULAR;
    }

    public static int getNewRelrase() {
        return NEW_RELRASE;
    }

    public String get_title() {
        return _title;
    }

    public void set_title(String _title) {
        this._title = _title;
    }

    public int get_priceCode() {
        return _price.getPriceCode();
    }

    public void set_priceCode(int _priceCode) {

        switch (_priceCode){
            case REGULAR:
                _price = new RegularPrice();
                break;
            case NEW_RELRASE:
                _price = new NewReleasePrice();
                break;

            case CHLLDRENS:
                _price = new ChildrensPrice();
                break;
            default:break;
        }
    }


    /**
     * 原amountFor方法,该方法移动到movie中
     * @return
     */
    public double getCharge(int daysRented){
        return _price.getCharge(daysRented);
    }

    public int getFrequentRenterPoints(int daysRented){

        return _price.getFrequentRenterPoints(daysRented);

    }
}
