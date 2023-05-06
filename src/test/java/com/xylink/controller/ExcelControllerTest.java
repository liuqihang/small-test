package com.xylink.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.context.ApplicationContext;


public class ExcelControllerTest {
    @Mock
    ApplicationContext applicationContext;
    @Mock
    Logger log;
    @InjectMocks
    ExcelController excelController;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExportExcel() throws Exception {
        log.info("testImportData start");
        excelController.exportExcel(null);
    }

    @Test
    public void testImportData() throws Exception {

        excelController.importData(null);
    }
}
