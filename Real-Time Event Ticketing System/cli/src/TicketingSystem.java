import java.util.Scanner;

public class TicketingSystem {

    public static void main(String[] args) {

        // Dummy values for config
        Configuration config = new Configuration(10, 2,2, 20);

        TicketPool ticketPool = new TicketPool(config);

        Customer customer = new Customer();

        Thread cus1 = new Thread(customer);



    }

    public void menu(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the Ticketing System");


        System.out.println("Enter the number of total tickets: ");
        int totalTickets = sc.nextInt();
        System.out.println("Enter the rate of Ticket release: ");
        int ticketReleaseRate = sc.nextInt();
        System.out.println("Enter the rate of Customer retrieval: ");
        int customerRetrievalRate = sc.nextInt();
        System.out.println("Enter the maximum number of ticket capacity: ");
        int maxTicketCapacity = sc.nextInt();


    }
}
