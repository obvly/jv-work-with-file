package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {

        int supplyAmount = 0;
        int buyAmount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line = reader.readLine();

            while (line != null) {
                String[] split = line.split(",");

                if (split.length == 2) {
                    String operationType = split[0];
                    String amountStr = split[1];

                    if (operationType.equals("supply")) {
                        supplyAmount += Integer.parseInt(amountStr);
                    } else if (operationType.equals("buy")) {
                        buyAmount += Integer.parseInt(amountStr);
                    }
                }

                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }

        int result = supplyAmount - buyAmount;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write("supply," + supplyAmount);
            writer.newLine();
            writer.write("buy," + buyAmount);
            writer.newLine();
            writer.write("result," + result);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file", e);
        }
    }
}
