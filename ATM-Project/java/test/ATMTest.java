import static org.junit.jupiter.api.Assertions.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

class ATMTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setIn(originalIn);
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

   /* @Test
    public void testAuthenticateUser() {
        List<String> userRecords = new ArrayList<>();
        userRecords.add("user1,password1,100.0");

        assertTrue(ATM.authenticateUser("user1", userRecords));
        assertFalse(ATM.authenticateUser("user1", new ArrayList<>()));
        assertFalse(ATM.authenticateUser("user2", userRecords));
    }

    @Test
    public void testDisplayBalance() {
        List<String> userRecords = new ArrayList<>();
        userRecords.add("user1,password1,100.0");
        userRecords.add("user2,password2,200.0");

        final String expectedOutput = "Your current balance is: 100.0\n";

        ATM.displayBalance("user1", userRecords);
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testDisplayTransactionHistory() {
        // Test for empty transaction history
        final String expectedEmptyOutput = "Transaction history is empty.\n";
        ATM.displayTransactionHistory();
        assertEquals(expectedEmptyOutput, outContent.toString());

    }*/

}