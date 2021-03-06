package se.lexicon.alnajjar.booklender.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.lexicon.alnajjar.booklender.entity.Book;
import se.lexicon.alnajjar.booklender.entity.LibraryUser;
import se.lexicon.alnajjar.booklender.entity.Loan;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class LoanRepositoryTest {

    @Autowired
    LoanRepository loanRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    LibraryUserRepository libraryUserRepository;

    Book book;
    LibraryUser libraryUser;
    Loan loan;

    List<LibraryUser> libraryUsers = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        libraryUser = new LibraryUser(1, LocalDate.now(), "Maha", "m@yahoo.com");
        book = new Book(1, "Hello Pippi", true, true, 30, BigDecimal.valueOf(222.344), "good");
        loan = new Loan(1, libraryUser, book, LocalDate.now(), false);

        bookRepository.save(book);
        libraryUserRepository.save(libraryUser);
        loanRepository.save(loan);
    }

    @Test
    public void find_By_User_Id() {
        Loan findByUserId = loanRepository.findByLoanTakerUserId(1);
        System.out.println("findByUserId =" + findByUserId.toString());
        assertEquals(1, findByUserId.getLoanTaker().getUserId());
    }

    @Test
    public void find_By_Book_Id() {
        Loan findByBookId = loanRepository.findByBookBookId(1);
        System.out.println("findByBookId =" + findByBookId.toString());
        assertEquals(1, findByBookId.getBook().getBookId());
    }

    @Test
    public void findByTerminate() {
        List<Loan> findByTerminate = loanRepository.findByTerminate(false);
        System.out.println("findByTerminate =" + findByTerminate.toString());
    }

}
