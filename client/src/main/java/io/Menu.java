package io;

public class Menu {

    public static void menu () {
    System.out.println("\nMENU");
    System.out.println("---------------");
    System.out.println("1. Show All Posts");
    System.out.println("2. Find Post By ID");
    System.out.println("3. Add Post");
    System.out.println("4. Change Post Text");
    System.out.println("5. Remove Post");
    System.out.println("0. Exit");
    System.out.println("---------------");
    System.out.print("Choice: ");

    Integer choice = Input.inputInt();
    switch (choice) {
        case 1:
            CRUD.getAllPosts();
            break;
        case 2:
            CRUD.findPost();
            break;
        case 3:
            CRUD.addPost();
            break;
        case 4:
            CRUD.updatePost();
            break;
        case 5:
            CRUD.removePost();
            break;
        case 0:
            break;
        default:
            System.out.println("Invalid choice: (" + choice + ") try again!");
            menu();
            break;
        }
    }
}
