package first.customer;

import first.movie.Movie;
import first.rental.Rental;

import java.util.Enumeration;
import java.util.Vector;

/**
 * 最初的顾客类
 */
public class Customer {

    //姓名
    private String _name;
    //租凭记录
    private Vector _rentals = new Vector();

    public Customer(String name){
        _name = name;
    }

    public void addRental(Rental arg){

        _rentals.addElement(arg);
    }

    public String getName(){
        return _name;
    }

    /**
     * statement方式过去庞大复杂，计算
     * @return
     */
    public String statement(){

        //总消费金额
        double totalAmount = 0;
        //常客积点
        int frequentRenterPoints = 0;

        Enumeration rentals = _rentals.elements();
        String result = "Rental Record For "+getName()+"\n";

        while (rentals.hasMoreElements()){
            double thisAmount = 0;
            Rental each = (Rental) rentals.nextElement();


            switch (each.get_movie().get_priceCode()){

                case Movie.REGULAR:
                    thisAmount += 2;
                    if(each.get_daysRented() > 2){
                        thisAmount += ((each.get_daysRented()-2)*1.5);
                    }
                    break;
                case Movie.NEW_RELRASE:
                    thisAmount += each.get_daysRented()*3;
                    break;

                case Movie.CHLLDRENS:
                    thisAmount += 1.5;
                    if(each.get_daysRented() > 3){
                        thisAmount += (each.get_daysRented() -3)*1.5;
                    }
                    break;
            }

            //累加常客积点数
            frequentRenterPoints ++;

            if(each.get_movie().get_priceCode() == Movie.NEW_RELRASE && each.get_daysRented() >1){
                frequentRenterPoints ++;
            }

            result = "\t" +each.get_movie().get_title()+"\t"+String.valueOf(thisAmount);
            totalAmount += thisAmount;

        }

        result += "Amount owed is "+ String.valueOf(totalAmount)+"\n";
        result += "You earned "+ String.valueOf(frequentRenterPoints) +" frquent renter points";
        return result;
    }
}
