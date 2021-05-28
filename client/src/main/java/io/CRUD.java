package io;

import jdk.swing.interop.SwingInterOpUtils;

public class CRUD {
    public static void getAllPosts() {

    }

    public static void findPost() {
        System.out.print("Enter ID of Post: ");
        var id = Input.inputInt();
    }

    public static void addPost() {
        System.out.print("\nEnter Header of Post: ");
        var header = Input.inputString();

        System.out.print("\nEnter Author of Post: ");
        var author = Input.inputString();

        System.out.print("\nEnter Text: ");
        var text = Input.inputString();
    }

    public static void updatePost() {
        System.out.print("\nEnter ID of Post to Update: ");
        var id = Input.inputInt();

        System.out.print("\nEnter new Text: ");
        var text = Input.inputString();
    }

    public static void removePost() {
        System.out.print("\nEnter ID of Post to Delete: ");
        var id = Input.inputInt();
    }
}
