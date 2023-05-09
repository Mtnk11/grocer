//package com.grocer.csv;
//
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.MappingIterator;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.SerializationFeature;
//import com.fasterxml.jackson.dataformat.csv.CsvMapper;
//import com.fasterxml.jackson.dataformat.csv.CsvSchema;
//import com.grocer.models.Category;
//
//import java.io.File;
//import java.io.IOException;
//
//public class JsonCsvConverter {
//    public static void jsonToCsv(File jsonFile, File csvFile) throws IOException {
//        JsonNode jsonTree = new ObjectMapper()
//                .readTree(jsonFile);
//
//        CsvSchema.Builder csvSchemaBuilder = CsvSchema.builder();
//        JsonNode firstObject = jsonTree.elements().next();
//        firstObject.fieldNames().forEachRemaining(fieldName ->
//        {
//            csvSchemaBuilder.addColumn(fieldName);
//        });
//        CsvSchema csvSchema = csvSchemaBuilder
//                .build().
//                withHeader();
//
//        CsvMapper csvMapper = new CsvMapper();
//        csvMapper.writerFor(JsonNode.class)
//                .with(csvSchema)
//                .writeValue(csvFile, jsonTree);
//    }
//    public static void csvToJson(File csvFile, File jsonFile) throws IOException {
//        CsvMapper csvMapper = new CsvMapper();
//        CsvSchema categoryLineSchema = CsvSchema.emptySchema().withHeader();
//
//        MappingIterator<com.grocer.csv.Category> categoriesLines = csvMapper.readerFor(com.grocer.csv.Category.class)
//                .with(categoryLineSchema)
//                .readValues(csvFile);
//
//        new ObjectMapper()
//                .configure(SerializationFeature.INDENT_OUTPUT, true)
//                .writeValue(jsonFile, categoriesLines.readAll());
//    }
//
//    public static void jsonToFormattedCsv(File jsonFile, File csvFile) throws IOException{
//       CsvMapper csvMapper = new CsvMapper();
//        CsvSchema csvSchema = csvMapper
//                .schemaFor(com.grocer.csv.Category.class)
//                .withHeader();
//
//       csvMapper.addMixIn(Category.class, com.grocer.csv.Category.class);
//       Category[] categories = new ObjectMapper()
//               .readValue(jsonFile, Category[].class);
//       csvMapper.writerFor(Category[].class)
//               .with(csvSchema)
//               .writeValue(csvFile, categories);
//    }
//}
