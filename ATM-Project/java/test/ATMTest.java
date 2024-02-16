import static org.junit.jupiter.api.Assertions.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;


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

    @Test
    public void testAuthenticateUser() {
        List<String> userRecords = new ArrayList<>();
        userRecords.add("robin_hag,ro123,10000.0");

        assertTrue(ATM.authenticateUser("terissa_gwe", userRecords));
        assertFalse(ATM.authenticateUser("michelle_jan", new ArrayList<>()));
        assertFalse(ATM.authenticateUser("lioannis_gu", userRecords));
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

    }

    @Test
    public void testDisplayScheduledTransactions() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Call the method to be tested
        ATM.displayScheduledTransactions();

        // Assert the output
        assertEquals("No scheduled transactions.\n", outContent.toString());

        // Clean up
        System.setOut(System.out);
    }

    @Test
    public void testDisplayAccountAlerts() {
        // Redirect System.out to capture output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Call the method to be tested
        ATM.displayAccountAlerts();

        // Assert the output
        String expectedOutput = "Account Alert: Unusual activity detected!";
        assertEquals("Expected output", expectedOutput, outContent.toString().trim());

        // Clean up
        System.setOut(System.out);
    }

}