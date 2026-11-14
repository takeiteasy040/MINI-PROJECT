import java.util.*;

public class miniproject {
    public static void main(String[] args) {
        String wel = "\bwelcome to the mini mart , where you get what you want \n..................................................................................................................................................";
        System.out.println(wel.toUpperCase());

        int choice = 0;
        String[] menu = { "1.VIEW INVENTORY", "2.VIEW CART", "3.REMOVE FROM CART", "4.CHECKOUT", "5.EXIT" };
        String[][] items = {
                { "PENCIL", "$1", "A writing instrument used\n for drawing or writing.", "50" },
                { "PEN", "$2", "A writing instrument that uses\n ink to write on paper.", "50" },
                { "ERASER", "$1", "A tool used to remove pencil\n or pen marks from paper.", "30" },
                { "NOTEBOOK", "$1", "A book of blank or lined\n pages used for writing notes\n or keeping records.","100" },
                { "REGISTER", "$2", "A book used for recording financial\n transactions or keeping track of sales.","200" },
                { "MARKER", "$1","A writing instrument that uses ink\n to create bold, colorful lines\n on paper or other surfaces.","50" },
                { "HIGHLIGHTER", "$2", "A writing instrument used to\n emphasize or highlight text on paper.", "50" },
                { "STAPLER", "$2"," A device used to fasten sheets\n of paper together by driving a\n metal staple through the sheets\n and folding the ends.","30" },
                { "SCISSORS", "$2","A cutting instrument consisting\n of two blades joined together in the\n middle, used for cutting paper,\n fabric, or other materials.","100" },
                { "GLUE", "$1", "A sticky substance used to bond\n two surfaces together.", "200" }
        };
        ArrayList<String> cart = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        Scanner sc3 = new Scanner(System.in);
        Scanner sc4 = new Scanner(System.in);

        do {
            System.out.println("\bMENU\n________________________________________________________________________________________________________________________________________________________________________________________");
            for (String option : menu) {
                System.out.println(option);
            }
            System.out.print("\bEnter your choice number (5 TO EXIT): ");
            choice = sc.nextInt();
            if (choice < 1 || choice > 5) {
                System.out.println("\bInvalid choice. Please try again.");
                sc.nextInt();
            }

            switch (choice) {
                case 1:
                    System.out.println("\bITEMS AVAILABLE IN THE MINI MART\n******************************************************************************************************************************************************");
                    for (String[] item : items) {
                        System.out.println(item[0] + "\n " + item[1] + "\n " + item[2] + "\n " + item[3] + " in stock");
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    }
                    boolean itemAdded = false;
                    do {
                        System.out.println("\bEnter the item you want to purchase: ");
                        String itemName = sc2.nextLine().toUpperCase();
                        System.out.println("\bEnter the quantity you want to purchase: ");
                        int quantity = sc3.nextInt();
                        sc3.nextLine();

                        boolean itemFound = false;
                        boolean quantityCorrect = quantity > 0;

                        for (String[] item : items) {
                            if (item[0].equals(itemName)) {
                                itemFound = true;

                                int stock = Integer.parseInt(item[3]);
                                if (quantity > stock) {
                                    System.out
                                            .println("Sorry, we only have " + stock + " " + itemName + "(s) in stock.");
                                } else {
                                    cart.add(itemName + "\n" + quantity  + "\n"+"$"+quantity * Integer.parseInt(item[1].substring(1)) + "\n" + item[2]);
                                    System.out.println(quantity + " " + itemName + "(s) for a total of $"
                                            + (quantity * Integer.parseInt(item[3].substring(1)))
                                            + " have been added to your cart.");
                                    item[3] = String.valueOf(stock - quantity);
                                    itemAdded = true;
                                }
                                break;
                            }
                        }

                        if (!itemFound || !quantityCorrect) {
                            System.out.println("\bInvalid input. Please try again.");
                        }
                    } while (!itemAdded);
                    break;
                case 2:
                    System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
                    System.out.println("\bITEMS IN YOUR CART :");
                    if (cart.isEmpty()) {
                        System.out.println("Your cart is empty.");
                    } else {
                        for (String item : cart) {
                            System.out.println(item);
                            System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
                        }
                    }
                    break;

                case 3:
                    System.out.println("**********************************************************************************************************************************************************************************************************");
                    System.out.println("REMOVE FROM CART :");
                    if (cart.isEmpty()) {
                        System.out.println("Your cart is empty.");
                        System.out.println("**********************************************************************************************************************************************************************************************************");
                    } else {
                        System.out.println("Enter the item you want to remove from your cart: ");
                        String itemToRemove = sc4.nextLine().toUpperCase();
                        boolean itemRemoved = false;

                        for (int i = 0; i < cart.size(); i++) {
                            if (cart.get(i).startsWith(itemToRemove)) {
                                cart.remove(i);
                                System.out.println(itemToRemove + " has been removed from your cart.");
                                System.out.println("**********************************************************************************************************************************************************************************************************");
                                itemRemoved = true;
                                break;
                            }
                        }

                        if (!itemRemoved) {
                            System.out.println(itemToRemove + " is not in your cart.");
                        System.out.println("**********************************************************************************************************************************************************************************************************");
                        }
                    }
                    break;

                case 4:
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.out.println("CHECKOUT :");
                    if (cart.isEmpty()) {
                        System.out.println("Your cart is empty. Please add items to your cart before checking out.");
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    } else {
                        int total = 0;
                        for (String item : cart) {
                            String[] parts = item.split("\n");
                            total += Integer.parseInt(parts[2].substring(1));
                        }
                        System.out.println("Your total is: $" + total);
                        System.out.println("Thank you for your purchase!");
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        cart.clear();
                    }
                    break;

                case 5:
                    System.out.println("Thank you for visiting the mini mart. Have a great day!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
        if (choice == 5) {
            System.out.println("exiting the program...");
        }

        sc.close();
        sc2.close();
        sc3.close();
        sc4.close();
    }
}