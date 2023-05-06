package com.xylink.controller;

import cn.hutool.core.io.FileUtil;
import com.alibaba.excel.util.FileUtils;
import com.google.common.io.Files;
import com.xylink.excel.EasyExcelUtil;
import com.xylink.excel.vo.ExportVo;
import com.xylink.excel.vo.HonorVo;
import com.xylink.excel.vo.Title;
import com.xylink.service.TestService;
import com.xylink.test.event.MessageEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/test")
@Slf4j
@RequiredArgsConstructor
public class ExcelController {

    @Autowired
    private TestService testService;

    /** 注入ApplicationContext用来发布事件 */
    private final ApplicationContext applicationContext;

    @PostMapping("exportExcel")
    public void exportExcel(HttpServletResponse response) throws IOException {
        log.info("exportExcel start ...");

        ExportVo exportVo = new ExportVo();
        exportVo.setFileName("刘启航的测试excel文件");
        exportVo.setSheetName("lqh_sheet");

        List<Title> titleList = new ArrayList<>();
        Title title1 = new Title()
                .setName("刘启航")
                .setAddress("武汉市")
                .setAge(12);

        Title title2 = new Title()
                .setName("张学友")
                .setAddress("香港")
                .setAge(21);

        titleList.add(title1);
        titleList.add(title2);

        List<Title> collect = titleList.stream().distinct().collect(Collectors.toList());

        exportVo.setHead(Title.class);
        exportVo.setData(titleList);

        applicationContext.publishEvent(new MessageEvent(this, "jahdhk"));

        EasyExcelUtil.exportExcel(response, exportVo);
    }

    @PostMapping(value = "importData")
    public void importData(@RequestParam("file")MultipartFile multipartFile) throws IOException {

//        InputStream inputStream = multipartFile.getInputStream();

        File file = null;
        try {
            file = multipartFileToFile(multipartFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

        log.info(getFileHeader(FileUtil.readBytes(file),3));
//        List<HonorVo> honorVos = EasyExcelUtil.readExcel(inputStream, HonorVo.class);

        log.info("导入完成");
    }

    @GetMapping(value = "testAsync")
    public void testAsync() {

//        testService.test1();
        testService.test2();
//        testService.test3();
//        testService.test4();
        log.info("testAsync end ..");
    }


    public static File multipartFileToFile(MultipartFile file) throws Exception {

        File toFile = null;
        if ("".equals(file) || file.getSize() <= 0) {
            file = null;
        } else {
            InputStream ins = null;
            ins = file.getInputStream();
            toFile = new File(file.getOriginalFilename());
            inputStreamToFile(ins, toFile);
            ins.close();
        }
        return toFile;
    }

    //获取流文件
    private static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取文件头
     *
     * @param file   文件字节码
     * @param length 获取头文件字节码长度
     * @return 文件头
     * @since 2022年3月25日16点55分
     */
    private static String getFileHeader(byte[] file, int length) {
        if (null == file || file.length <= 0 || length > file.length) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int bt = file[i] & 0xFF;
            String str = Integer.toHexString(bt);
            if (str.length() < 2) {
                builder.append(0);
            }
            builder.append(str);
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        File jpgFile = new File("D:\\testFile\\testJpg.jpg");
        File pdfFile = new File("D:\\testFile\\testPdf.pdf");
        File pngFile = new File("D:\\testFile\\testPng.png");
        File gifFile = new File("D:\\testFile\\testGif.gif");
        File docFile = new File("D:\\testFile\\testDocx.docx");
        File sourceJpgToDocx = new File("D:\\testFile\\testJpg-update.docx");
        System.out.println("jpgFile=" + getFileHeader(FileUtil.readBytes(jpgFile),3));
        System.out.println("pdfFile=" + getFileHeader(FileUtil.readBytes(pdfFile),3));
        System.out.println("pngFile=" + getFileHeader(FileUtil.readBytes(pngFile),3));
        System.out.println("gifFile=" + getFileHeader(FileUtil.readBytes(gifFile),3));
        System.out.println("docFile=" + getFileHeader(FileUtil.readBytes(docFile),3));
        System.out.println("sourceJpgToDocx=" + getFileHeader(FileUtil.readBytes(sourceJpgToDocx),3));
    }
}
