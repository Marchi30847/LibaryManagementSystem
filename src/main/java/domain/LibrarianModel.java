package domain;

import data.dao.BookDAO;
import data.dao.BorrowingDAO;
import data.dao.CopyDAO;
import data.dao.LibrarianDAO;
import data.dao.MemberDAO;
import data.dao.PublisherDAO;
import data.dependencies.LibrarianContract;
import data.entities.Book;
import data.entities.Borrowing;
import data.entities.Copy;
import data.entities.Librarian;
import data.entities.Member;
import data.entities.Publisher;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

public class LibrarianModel implements LibrarianContract.Model {

    private final BookDAO bookDAO = new BookDAO();
    private final BorrowingDAO borrowingDAO = new BorrowingDAO();
    private final CopyDAO copyDAO = new CopyDAO();
    private final LibrarianDAO librarianDAO = new LibrarianDAO();
    private final MemberDAO memberDAO = new MemberDAO();
    private final PublisherDAO publisherDAO = new PublisherDAO();

    private <T> String[][] mapEntitiesToColumns(List<String> columnNames, List<T> entities, Class<T> entityClass) {
        String[][] result = new String[entities.size() + 1][columnNames.size()];

        for (int i = 0; i < columnNames.size(); i++) {
            result[0][i] = columnNames.get(i);
        }

        for (int j = 0; j < entities.size(); j++) {
            T entity = entities.get(j);

            for (int i = 0; i < columnNames.size(); i++) {
                String columnName = columnNames.get(i);

                try {
                    Field field = entityClass.getDeclaredField(columnName);
                    field.setAccessible(true);
                    Object value = field.get(entity);
                    result[j + 1][i] = value != null ? value.toString() : "null";
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                    throw new RuntimeException("Error accessing field: " + columnName, e);
                }
            }
        }

        return result;
    }

    @Override
    public String[][] getMembersData() {
        List<String> columnNames = memberDAO.getColumnNames();
        List<Member> members = memberDAO.getAll();
        return mapEntitiesToColumns(columnNames, members, Member.class);
    }

    @Override
    public String[][] getBooksData() {
        List<String> columnNames = bookDAO.getColumnNames();
        List<Book> books = bookDAO.getAll();
        return mapEntitiesToColumns(columnNames, books, Book.class);
    }

    @Override
    public String[][] getBorrowingsData() {
        List<String> columnNames = borrowingDAO.getColumnNames();
        List<Borrowing> borrowings = borrowingDAO.getAll();
        return mapEntitiesToColumns(columnNames, borrowings, Borrowing.class);
    }

    @Override
    public String[][] getCopiesData() {
        List<String> columnNames = copyDAO.getColumnNames();
        List<Copy> copies = copyDAO.getAll();
        return mapEntitiesToColumns(columnNames, copies, Copy.class);
    }

    @Override
    public String[][] getLibrariansData() {
        List<String> columnNames = librarianDAO.getColumnNames();
        List<Librarian> librarians = librarianDAO.getAll();
        return mapEntitiesToColumns(columnNames, librarians, Librarian.class);
    }

    @Override
    public String[][] getPublishersData() {
        List<String> columnNames = publisherDAO.getColumnNames();
        List<Publisher> publishers = publisherDAO.getAll();
        return mapEntitiesToColumns(columnNames, publishers, Publisher.class);
    }

    @Override
    public void insertBook(String title , String author, String publisherId, String year, String isbn) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setPublisher(publisherDAO.getById(Integer.parseInt(publisherId)));
        book.setPublicationYear(Integer.parseInt(year));
        book.setIsbn(isbn);

        bookDAO.save(book);
    }

    @Override
    public void insertBorrowing(String memberId, String copyId) {
        Borrowing borrowing = new Borrowing();
        borrowing.setMember(memberDAO.getById(Integer.parseInt(memberId)));
        borrowing.setCopy(copyDAO.getById(Integer.parseInt(copyId)));
        borrowing.setBorrowDate(new Date());

        borrowingDAO.save(borrowing);
    }

    @Override
    public void insertCopy(String bookId, String copyNumber, String condition) {
        Copy copy = new Copy();
        copy.setBook(bookDAO.getById(Integer.parseInt(bookId)));
        copy.setCopyNumber(Integer.parseInt(copyNumber));
        copy.setStatus(condition);

        copyDAO.save(copy);
    }

    @Override
    public void insertLibrarian(String memberId, String position) {
        Librarian librarian = new Librarian();
        librarian.setMember(memberDAO.getById(Integer.parseInt(memberId)));
        librarian.setPosition(position);

        librarianDAO.save(librarian);
    }

    @Override
    public void insertMember(String name, String email, String phone, String address) {
        Member user = new Member();
        user.setName(name);
        user.setEmail(email);
        user.setPhoneNumber(phone);
        user.setAddress(address);

        memberDAO.save(user);
    }

    @Override
    public void insertPublisher(String name, String address, String phone) {
        Publisher publisher = new Publisher();
        publisher.setName(name);
        publisher.setAddress(address);
        publisher.setPhoneNumber(phone);

        publisherDAO.save(publisher);
    }

    @Override
    public void deleteBook(int bookRow) {
        int bookId = bookDAO.getByRow(bookRow).getId();
        bookDAO.delete(bookId);
    }

    @Override
    public void deleteBorrowing(int borrowingRow) {
        int borrowingId = borrowingDAO.getByRow(borrowingRow).getId();
        borrowingDAO.delete(borrowingId);
    }

    @Override
    public void deleteCopy(int copyRow) {
        int copyId = copyDAO.getByRow(copyRow).getId();
        copyDAO.delete(copyId);
    }

    @Override
    public void deleteLibrarian(int librarianRow) {
        int librarianId = librarianDAO.getByRow(librarianRow).getId();
        librarianDAO.delete(librarianId);
    }

    @Override
    public void deleteMember(int memberRow) {
        int memberId = memberDAO.getByRow(memberRow).getId();
        memberDAO.delete(memberId);
    }

    @Override
    public void deletePublisher(int publisherRow) {
        int publisherId = publisherDAO.getByRow(publisherRow).getId();
        publisherDAO.delete(publisherId);
    }

    @Override
    public void updateBook(Book book) {

    }

    @Override
    public void updateBorrowing(Borrowing borrowing) {

    }

    @Override
    public void updateCopy(Copy copy) {

    }

    @Override
    public void updateLibrarian(Librarian librarian) {

    }

    @Override
    public void updateMember(Member member) {

    }

    @Override
    public void updatePublisher(Publisher publisher) {

    }

}