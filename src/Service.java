import java.util.Scanner;

public class Service {
    public static void main(String[] args) {
        int choice;
        while(true)
        {
            System.out.println("Select the option");
            System.out.println("1.Add Customer");
            System.out.println("2.search Customer");
            System.out.println("3.Delete customer");
            System.out.println("4.Update customer");
            System.out.println("5.View all customer");
            System.out.println("6.Generate bill ");
            System.out.println("6.Generate bill ");
            System.out.println("7.View all bills ");
            System.out.println("8 Top two high bill");
            System.out.println("9.Exit ");
            System.out.println("*****************");
            System.out.println("ENTER YOUR CHOICE:--");
            Scanner sc=new Scanner(System.in);
            choice=sc.nextInt();
            switch(choice)
            {
                case 1:
                    System.out.println("Add customer details...");
                    break;
                case 2:
                    System.out.println("Search customer");
                    break;
                case 3:
                    System.out.println("Delete customer");
                    break;
                case 4:
                    System.out.println("Update customer");
                    break;
                case 5:
                    System.out.println("View all customer");
                    break;
                case 6:
                    System.exit(0);

            }
        }
    }
}