package de.schemmea.ma.utils;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public final class Configuration {
    public static final DateTimeFormatter Formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
    public static final String formattedDate = LocalDateTime.now().format(Formatter);
    public static final String SOURCE_PATH = "/nextflow/";
    public static final String TEMPLATES = "templates/";
    public static final String OUTPUT_PATH = "generatedflows/";
    public static final String DATA = "data/";
    public static final String TEMPLATE_SOURCE_PATH = SOURCE_PATH + TEMPLATES;
    public static final String DATA_SOURCE_PATH = SOURCE_PATH + DATA;
    public static final String OUTPUT_TEMPLATE_PATH = OUTPUT_PATH + TEMPLATES;
    public static final String OUTPUT_DATA_PATH = OUTPUT_PATH + DATA;
}
