package first.price;

public abstract class Price {

    public abstract int getPriceCode();

    public abstract double getCharge(int daysRented);

    /**
     * 保留成为缺省行为
     * @param daysRented
     * @return
     */
    public int getFrequentRenterPoints(int daysRented){
        return 1;
    }

}