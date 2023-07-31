public class Ticket {
    private int row,seat,price;
    private Person person;

    public Ticket(int row_num, int seat_num,int price, Person person) {
        this.row = row_num;
        this.seat = seat_num;
        this.price = price;
        this.person = person;
    }

    public int getRow() {return row;
    }



    public int getSeat() {
        return seat;
    }



    public int getPrice() {
        return price;
    }



    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void print(){
        System.out.println("Name: " + person.getName());
        System.out.println("Surname: " + person.getSurname());
        System.out.println("Email: " + person.getEmail());
        System.out.println("Row: " + row);
        System.out.println("Seat: " + seat);
        System.out.println("Price: $" + price);

    }
}

