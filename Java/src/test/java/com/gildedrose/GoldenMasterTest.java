package com.gildedrose;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * GildedRose-Refactoring-Kata
 *
 * @author vianney On 06/10/2026
 */
public class GoldenMasterTest {

    @Test
    void thirtyDaysOutputMatchesApprovedOutput() throws Exception {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buffer, true, "UTF-8"));

        try {
            TexttestFixture.main(new String[]{"30"});
        } finally {
            System.setOut(originalOut);
        }

        String expected = new String(
            Files.readAllBytes(Paths.get("../texttests/ThirtyDays/stdout.gr")),
            StandardCharsets.UTF_8);

        String actual = buffer.toString("UTF-8");

        assertEquals(expected, actual);
    }
}
