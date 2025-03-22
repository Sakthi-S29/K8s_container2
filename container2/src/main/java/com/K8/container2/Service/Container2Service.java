package com.K8.container2.Service;

import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Container2Service {
    public Map<String,Object> calculateSum(String fileName, String product){
        String path = "/SakthiSharan_PV_dir/";
        Map<String,Object> response = new HashMap<>();
        response.put("file",fileName);
        Path filePath = Paths.get(path + fileName);
        if (!Files.exists(filePath)) {
            response.put("error", "File not found.");
        }
        else{
            try {
                List<String> lines = Files.readAllLines(filePath);
                int sum = 0;
                boolean isCsvFormatValid = false;

                for (String line : lines) {
                    String[] parts = line.split(",");
                    if (parts.length == 2 && parts[0].trim().equalsIgnoreCase(product)) {
                        sum += Integer.parseInt(parts[1].trim());
                        isCsvFormatValid = true;
                    }
                }

                if (!isCsvFormatValid) {
                    response.put("error", "Input file not in CSV format.");
                }
                else {
                    response.put("sum", sum);
                }
                return response;
            } catch (Exception e) {
                response.put("error", "Error while processing the file.");
                return response;
            }
        }
        return response;
        }

    }
