public class Ticket {
    private final int row;
    private final int seat;
    private final double price;
    Person person;

    //setters
//    public void setRow(int row){
//        this.row=row;
//    }
//    public void setSeat(int seat){
//        this.seat=seat;
//    }
//    public void setPrice(int price){
//        this.price=price;
//    }
//    public void setPerson(Person person){
//        this.person=person;
//    }

    //getters
    public int getRow(){
        return row;
    }
    public int getSeat(){
        return seat;
    }
    public double getPrice(){return price;}
    public Person getPerson(){
        return person;
    }

    Ticket(int row,int seat,double price,Person person){
        this.row=row;
        this.seat=seat;
        this.price=price;
        this.person=person;
    }
    public void print(){
        System.out.println("Person name :"+getPerson().getName());
        System.out.println("Person Surname :"+getPerson().getSurname());
        System.out.println("Person email :"+getPerson().getEmail());
        System.out.println("Row Number :"+getRow());
        System.out.println("Seat Number :"+getSeat());
        System.out.println("Price of the ticket :"+getPrice());
    }
}
