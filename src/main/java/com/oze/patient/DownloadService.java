package com.oze.patient;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

@Service
public class DownloadService {
    private static final String[] HEADERS = {"name", "Age", "Last Visit Date"};
    private static final CSVFormat FORMAT = CSVFormat.DEFAULT.withHeader(HEADERS);

    //load data into csv
    public ByteArrayInputStream load(final List<PatientDTO> patientList) {
        return writeDataToCsv(patientList);
    }

    //write data to csv
    private ByteArrayInputStream writeDataToCsv(final List<PatientDTO> patientList) {
        try (final ByteArrayOutputStream stream = new ByteArrayOutputStream();
             final CSVPrinter printer = new CSVPrinter(new PrintWriter(stream), FORMAT)) {
            for (final PatientDTO patient : patientList) {
                final List<String> data = Arrays.asList(
                        patient.getName(),
                        String.valueOf(patient.getAge()),
                        String.valueOf(patient.getLastVisitDate())
                        );
                printer.printRecord(data);
            }

            printer.flush();
            return new ByteArrayInputStream(stream.toByteArray());
        } catch (final IOException e) {
            throw new RuntimeException("Csv writing error: " + e.getMessage());
        }
    }
}
