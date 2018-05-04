package first.customer;

import first.movie.Movie;
import first.rental.Rental;

import java.util.Enumeration;
import java.util.Vector;

/**
 * 最初的顾客类,第一次修改
 *
 * ONE-提炼statement方法
 *
 * a:这里对提炼出来的statement方法继续修改
 */
public class Customer_one_a {

    //姓名
    private String _name;
    //租凭记录
    private Vector _rentals = new Vector();

    public Customer_one_a(String name){
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
     * 这里修改提炼该方法
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

            //在这里提炼的计算消费金额的方法
            thisAmount = amountFor(each);

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

    /**
     * 这里是新提炼出来的方法
     *
     * a:对于提出来的方法需要抽象化大众化
     * @param aRental
     * @return
     */
    //将each改为aRental 表示一笔租金，更加具体这个提出来的方法
    //修改好的变量名称能够清楚的表达方法的含义，进一步加强效率
    public double amountFor(Rental aRental){
        double thisAmount = 0;
        switch (aRental.get_movie().get_priceCode()){

            case Movie.REGULAR:
                thisAmount += 2;
                if(aRental.get_daysRented() > 2){
                    thisAmount += ((aRental.get_daysRented()-2)*1.5);
                }
                break;
            case Movie.NEW_RELRASE:
                thisAmount += aRental.get_daysRented()*3;
                break;

            case Movie.CHLLDRENS:
                thisAmount += 1.5;
                if(aRental.get_daysRented() > 3){
                    thisAmount += (aRental.get_daysRented() -3)*1.5;
                }
                break;
                default:break;
        }

        return thisAmount;
    }
}
