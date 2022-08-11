package db;

import java.util.ArrayList;

public class InMemoryDB {
    private static ArrayList<Book> bookDataBase = new ArrayList<>();
    private static ArrayList<Member> memberDataBase = new ArrayList<>();

    static {
        bookDataBase.add(new Book("978-3-16-148410-0","Liner Algebra","W.D.Daas"));
        bookDataBase.add(new Book("456-4-14-567891-1","Quantum Physics","W.A.Henrics"));
        bookDataBase.add(new Book("789-2-56-469873-5","Combined Maths","P.A.Silva"));
        bookDataBase.add(new Book("423-1-72-456984-8","Organic chemistry","K.Henrics"));
        bookDataBase.add(new Book("865-8-89-456987-9","Liner Algebra","K.S.Withanage"));

        memberDataBase.add(new Member("123456789V","Nipuna","034-8810532"));
        memberDataBase.add(new Member("789654123V","Eranga","038-4569712"));
        memberDataBase.add(new Member("896413576V","Naveen","071-8045698"));
        memberDataBase.add(new Member("944568123V","Chathura","077-7896541"));
        memberDataBase.add(new Member("456987123V","Praveen","011-7841025"));
    }

    public static Book findBook(String isbn) {
        for (Book book : bookDataBase) {
            if (book.getIsbn().equalsIgnoreCase(isbn)) {
                return book;
            }
        }
        return null;
    }
    public static Member findMember(String nic) {
        for (Member member : memberDataBase) {
            if (member.getNic().equalsIgnoreCase(nic)) {
                return member;
            }
        }
        return null;
    }
    public static boolean addBook(Book book) {
        if (findBook(book.getIsbn()) != null) return false;
        bookDataBase.add(book);
        return true;
    }
    public static boolean addMember(Member member) {
        if (findMember(member.getNic()) != null) return false;
        memberDataBase.add(member);
        return true;
    }
    public static Book updateBook(String isbn, String name, String author) {
        Book book = findBook(isbn);
        if (book != null) {
            book.setName(name);
            book.setAuthor(author);
            return book;
        }
        return null;
    }
    public static Member updateMember(String nic, String name, String contact) {
        Member member = findMember(nic);
        if (member != null) {
            member.setName(name);
            member.setContact(contact);
            return member;
        }
        return null;
    }
    public static Book removeBook(String isbn) {
        Book book = findBook(isbn);
        if (book != null) {
            bookDataBase.remove(book);
            return book;
        }
        return null;
    }
    public static Member removeMember(String nic) {
        Member member = findMember(nic);
        if (member != null) {
            memberDataBase.remove(member);
            return member;
        }
        return null;
    }
    public static ArrayList<Book> getBookDataBase() {
        return bookDataBase;
    }
    public static ArrayList<Member> getMemberDataBase() {
        return memberDataBase;
    }
}
