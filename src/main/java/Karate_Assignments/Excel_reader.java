package Karate_Assignments;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;

public class Excel_reader
{
    public static PetData getPetData(String testCaseName)
    {
        String excelFilePath = "C:\\Users\\arpitp\\Pet_Test_Data.xlsx";
        PetData petData = null;

        try (FileInputStream fis = new FileInputStream(excelFilePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            DataFormatter dataFormatter = new DataFormatter();

            // Assuming 'testcasename', 'petid', and 'petname' are in the first row (index 0)
            Row headerRow = sheet.getRow(0);
            int columnIndexTestCaseName = -1;
            int columnIndexPetId = -1;
            int columnIndexPetName = -1;

            // Find the column indexes for 'testcasename', 'petid', and 'petname'
            for (Cell cell : headerRow) {
                String cellValue = dataFormatter.formatCellValue(cell);
                if ("testcasename".equalsIgnoreCase(cellValue)) {
                    columnIndexTestCaseName = cell.getColumnIndex();
                } else if ("petid".equalsIgnoreCase(cellValue)) {
                    columnIndexPetId = cell.getColumnIndex();
                } else if ("petname".equalsIgnoreCase(cellValue)) {
                    columnIndexPetName = cell.getColumnIndex();
                }
            }

            // Find the row with the matching test case name
            for (Row row : sheet) {
                if (row.getRowNum() == 0) { // Skip the header row
                    continue;
                }

                String currentTestCaseName = dataFormatter.formatCellValue(row.getCell(columnIndexTestCaseName));

                if (testCaseName.equalsIgnoreCase(currentTestCaseName)) {
                    String petId = dataFormatter.formatCellValue(row.getCell(columnIndexPetId));
                    String petName = dataFormatter.formatCellValue(row.getCell(columnIndexPetName));
                    petData = new PetData(petId, petName);
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return petData;
    }

    public static class PetData {
        private String petId;
        private String petName;

        public PetData(String petId, String petName) {
            this.petId = petId;
            this.petName = petName;
        }


        public String getPetId() {
            return petId;
        }

        public String getPetName() {
            return petName;
        }
    }
}

