import java.io.*;
import java.util.*;

class Book implements Serializable{
    public static final long serialVersionUID=1L;
    private String bookname;
    private String author;
    private String ISBN;

    public Book(String title, String author, String ISBN){
        this.bookname=title;
        this.author=author;
        this.ISBN=ISBN;
    }

    public String getBookname(){
        return bookname;
    }

    public String getAuthor(){
        return author;
    }

    public String getISBN(){
        return ISBN;
    }

    @Override
    public String toString(){
        return "User{" +
                "title = "+bookname
                +"author = "+author+
                "ISBM = "+ISBN+
                "}";
    }
}

class User implements Serializable{
    private static final long serialVersionUID = 1L;
    private String username;
    private String userID;

    public User(String username,String userID){
        this.username=username;
        this.userID=userID;
    }

    public String getname(){
        return username;
    }

    public String getUserID(){
        return userID;
    }

    @Override
    public String toString(){
        return "User{" +
                "name = "+username+
                "UserID = "+userID+
                "}";
    }
}

class Library implements Serializable{

    private static final long serialVersionUID = 1L;
    private List<Book>books;
    private Map<String ,User> users;
    private Map<String ,String >borrowed_book;

    public Library(){
        this.books=new ArrayList<>();
        this.users=new HashMap<>();
        this.borrowed_book=new HashMap<>();
    }

    public void addBook(Book book){
        books.add(book);
    }

    public void removebook(String ISBN){
        books.removeIf(book-> book.getISBN().equals(ISBN));
        borrowed_book.remove(ISBN);
        saveLibraryData();

    }

    public void registerUser(User user){
        users.put(user.getUserID(),user);
        saveLibraryData();
    }

    public boolean borrowBook(String ISBN,String userID){
        if(borrowed_book.containsKey(ISBN)){
            System.out.println("Book already borrowed. ");
            return false;
        }
        if(users.containsKey(userID)){
            borrowed_book.put(ISBN,userID);
            saveLibraryData();
            return true;
        }
        System.out.println("User not registrated.");
        return false;
    }

    public boolean returnBook(String ISBN) {
        if (borrowed_book.containsKey(ISBN)) {
            borrowed_book.remove(ISBN);
            saveLibraryData();
            return true;
        }
        System.out.println("Book was not borrowed.");
        return false;
    }


    public void displayBooks(){
        if(books.isEmpty()){
            System.out.println("No books available.");
            return ;
        }
        for(Book book : books){
            System.out.println(book);
        }
    }

    public void displayBorrowedBooks(){
        if(borrowed_book.isEmpty()){
            System.out.println("No books are currently borrowed.");
            return ;
        }
        for(Map.Entry<String,String>entry:borrowed_book.entrySet()){
            String ISBN=entry.getKey();
            String userID=entry.getValue();
            Book book= findBookByISBN(ISBN);
            User user=users.get(userID);
            if(book!=null && user!=null) {
                System.out.println(book + " borrowed by " + user);
            }
        }
    }

    private Book findBookByISBN(String ISBN){
        for(Book book: books){
            if(book.getISBN().equals(ISBN)){
                return book;
            }
        }
        return null;
    }

    public void saveLibraryData()  {
        try(ObjectOutputStream out =new ObjectOutputStream(new FileOutputStream("libraryData.ser"))){

        } catch (IOException e) {
            System.out.println("Error saving library data.");
        }
    }

    public static Library loadLibraryData() {
        try(ObjectInputStream in=new ObjectInputStream(new FileInputStream("libraryData.ser"))){
         return (Library) in.readObject();
        }
        catch(IOException | ClassNotFoundException e){
            System.out.println("Error loading library data. Starting fresh ");
            return new Library();
        }
    }
}
public class LibraryManagementSystem{

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        Library library=Library.loadLibraryData();

        while(true){
            System.out.println("\n Library Management System");
            System.out.println("1. Display All Book");
            System.out.println("2. Display Borrowed Book");
            System.out.println("3. Add Book");
            System.out.println("4. Remove a Book");
            System.out.println("5. Register a User");
            System.out.println("6. Boorow a Book");
            System.out.println("7. Return a Book");
            System.out.println("8. Exit");
            System.out.println("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch(choice){
                case 1:
                    System.out.println("\nAvailable Books:");
                    library.displayBooks();
                    break;

                case 2:
                    System.out.println("\nBorrowed Books:");
                    library.displayBorrowedBooks();
                    break;

                case 3:
                    System.out.println("\nEnter book title: ");
                    String title=sc.nextLine();
                    System.out.println("Enter book author: ");
                    String author=sc.nextLine();
                    System.out.println("Enter book ISBN: ");
                    String ISBN=sc.nextLine();
                    library.addBook(new Book(title,author,ISBN));
                    System.out.println("Book added successfully.");
                    break;

                case 4:
                    System.out.println("\nEnter book ISBN to remove: ");
                    ISBN=sc.nextLine();
                    library.removebook(ISBN);
                    System.out.println("Book removed successfully.");
                    break;

                case 5:
                    System.out.println("\nEnter user name: ");
                    String name=sc.nextLine();
                    System.out.println("Enter user ID: ");
                    String userID=sc.nextLine();
                    library.registerUser(new User(name ,userID));
                    System.out.println("User registered successfully.");
                    break;

                case 6:
                    System.out.println("\n Enter book ISBN to borrow: ");
                    ISBN=sc.nextLine();
                    System.out.println("Enter user ID: ");
                    userID= sc.nextLine();
                    if(library.borrowBook(ISBN,userID)){
                        System.out.println("Book borrowed successfully.");
                    }
                    break;

                case 7:
                    System.out.println("\nEnter book ISBN to return: ");
                    ISBN=sc.nextLine();
                    if(library.returnBook(ISBN)){
                        System.out.println("Book returned successfully.");
                    }
                    break;

                case 8:
                    System.out.println("Exiting....");
                    library.saveLibraryData();
                    sc.close();
                    return ;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}
