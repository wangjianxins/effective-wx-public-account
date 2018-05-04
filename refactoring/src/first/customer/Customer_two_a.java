package first.customer;

import first.movie.Movie;
import first.rental.Rental;

import java.util.Enumeration;
import java.util.Vector;

/**
 * 最初的顾客类,第一次修改
 *
 * ONE：-提炼statement方法
 *
 * ONE_a:这里对提炼出来的statement方法继续修改
 *
 * TWO：这里发现了新提炼出来的amountFor方法虽然在customer（忽略类后面的_tow_a/b/c/d的命名，统一都是customer类）类中
 * 但是没有使用customer的信息，都是使用Rental类中的信息 - 对应Rental_one类
 *
 * TWO_a将frequentRenterPoints的++提取
 */
public class Customer_two_a {

    //姓名
    private String _name;
    //租凭记录
    private Vector _rentals = new Vector();

    public Customer_two_a(String name){
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
//        double totalAmount = 0;
        //常客积点
        int frequentRenterPoints = 0;

        Enumeration rentals = _rentals.elements();
        String result = "Rental Record For "+getName()+"\n";

        while (rentals.hasMoreElements()){

            Rental each = (Rental) rentals.nextElement();

            //在这里提炼的计算消费金额的方法
//            thisAmount = each.getChange();

            //累加常客积点数提取方法
            frequentRenterPoints += each.getFrequentRenterPoints();

            result = "\t" +each.get_movie().get_title()+"\t"+String.valueOf(each.getCharge());
//            totalAmount += each.getChange();

        }

        result += "Amount owed is "+ String.valueOf(getTotalCharge())+"\n";
        result += "You earned "+ String.valueOf(getTotalFrequentRenterPoints()) +" frquent renter points";
        return result;
    }

    public String htmlStatement(){

        Enumeration rentals = _rentals.elements();
        String result = "<H1>Rental for<EM>" + getName() + "</EM></H1>";

        while (rentals.hasMoreElements()){
            Rental each = (Rental) rentals.nextElement();
            result += each.get_movie().get_title()+": "+String.valueOf(each.getCharge());
        }

        //add fre
        result += getTotalCharge()+"...";
        result += getTotalFrequentRenterPoints();
        return result;
    }

    public double getTotalCharge(){
        double result = 0;
        Enumeration rentals = _rentals.elements();
        while (rentals.hasMoreElements()){
            Rental each = (Rental) rentals.nextElement();
            result += each.getCharge();
        }
        return result;
    }

    public int getTotalFrequentRenterPoints(){
        int result = 0;
        Enumeration rentals = _rentals.elements();
        while (rentals.hasMoreElements()){
            Rental each = (Rental) rentals.nextElement();
            result += each.getFrequentRenterPoints();
        }
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
        return aRental.getCharge();
    }
}
