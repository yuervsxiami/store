package com.cnuip.user;

import io.swagger.annotations.ApiOperation;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by IntelliJ IDEA.
 * User:zhaozhihui
 * Date: 2020-01-15
 * Time: 16:59
 */
public class test {

    public Future<Map<Long, JudgeAfterSaleDto>> judgeStartAfterSaleMap(List<Long> orderIds, DeliverListQueryVo deliverListQueryVo) {
        return Executors.newFixedThreadPool(20).submit(() -> judgeStartAfterSale(orderIds, deliverListQueryVo));
    }


    @RequestMapping(value = "/exportPurchaseOrder/{purchaseOrderId}", method = RequestMethod.GET)
    @ApiOperation(value = "采购订单导出", notes = "采购订单导出")
    public void exportPurchaseOrder(@PathVariable Integer purchaseOrderId,
                                    HttpServletRequest request,
                                    HttpServletResponse response) {
        LOG.info("exportPurchaseOrder() interface start");
        String webUrl = request.getSession().getServletContext().getRealPath(""); //应用的相对路径

        File file = deliverPurchaseOrderService.exportPurchaseOrder(purchaseOrderId, webUrl, DmsConstant.ZERO);
        if (file != null) {
            String fname = null;
            try {
                fname = new String(file.getName().getBytes("GB2312"), "ISO_8859_1");
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }
            if (fname != null && !"".equals(fname)) {
                BufferedInputStream br = null;
                OutputStream os = null;
                try {
                    // 创建一个缓冲输入流对象
                    br = new BufferedInputStream(new FileInputStream(file));
                    byte[] buf = new byte[1024];
                    int len = 0;
                    response.reset(); // 非常重要
                    response.setContentType("application/vnd.ms-excel; charset=utf-8");
                    response.setHeader("Content-Disposition", "attachment;filename=" + fname + "");
                    response.addHeader("Content-Length", "" + file.length());
                    response.setCharacterEncoding("ISO_8859_1");
                    os = response.getOutputStream();
                    // 开始输出
                    while ((len = br.read(buf)) > 0) {
                        os.write(buf, 0, len);
                    }
                    // 关闭流对象
                    br.close();
                    os.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (br != null) {
                            br.close();
                        }
                        if (os != null) {
                            os.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (file.exists()) {
                        if (file.delete()) {
                        }
                    }
                }
            }
        }
    }


    @Override
    public File exportPurchaseOrder(Integer purchaseOrderId, String webUrl,int terminal) {
        if (null == purchaseOrderId) {
            return null;
        }
        DeliverPurcahseOrderDetailDto dto = new DeliverPurcahseOrderDetailDto();
        dto.setOrderId(purchaseOrderId);
        ResponseVo<DeliverPurchaseOrderDetail> responseVo = newQueryPurchaseDetail(dto);
        if (null == responseVo || HttpResponseCode.SUCCESS.intValue() != responseVo.getCode()) {
            return null;
        }

        DeliverPurchaseOrderDetail purchaseOrderDetail = responseVo.getData();
        if (null == purchaseOrderDetail) {
            return null;
        }

        IReportTemplateHandler reportTemplateHandler = new DefaultReportTemplateHandler();

        convertSheet(reportTemplateHandler, purchaseOrderDetail,terminal);

        StringBuffer fileName = new StringBuffer();
        //fileName.append(purchaseOrderDetail.getSupplierName());
        fileName.append("艾佳采购订单-");
        fileName.append(purchaseOrderDetail.getOrderName());
        //fileName.append("-");
        //fileName.append(purchaseOrderDetail.getSupplierName());
        //fileName.append(")");
//		File file = new File(commonConfig.getSaveFileCatalog() + File.separator + REPORT_NAME
//				+ purchaseOrderDetail.getOrderNum() + ".xls");
        String nameStr = fileName.toString();
        nameStr = nameStr.replaceAll("/", "&");
        //如果采购订单名称过长，保留60个字符长度
        if (StringUtils.hasText(nameStr)) {
            if (nameStr.length() > 60) {
                nameStr = nameStr.substring(0, 60);
            }
        }
        File file = new File(webUrl + File.separator + nameStr + ".xls");
        // File file = new File(
        // "/Users/corbin/Desktop" + File.separator + REPORT_NAME +
        // purchaseOrderDetail.getOrderNum() + ".xls");

        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            LOG.error("error {}", e);
        }
        if (null == fileOutputStream) {
            return null;
        }
        try {
            reportTemplateHandler.getWorkbook().write(fileOutputStream);
        } catch (IOException e) {
            LOG.error("error {}", e);
        }
        try {
            fileOutputStream.close();
        } catch (IOException e) {
            LOG.error("error {}", e);
        }

        return file;
    }

    /**
     * 组装汇总的sheet
     *
     * @param reportTemplateHandler
     * @param purchaseOrderDetail
     * @return
     */
    private HSSFSheet convertSheet(IReportTemplateHandler reportTemplateHandler,
                                   DeliverPurchaseOrderDetail purchaseOrderDetail,int terminal) {
        Map<Integer, List<String>> titleListMap = new HashMap<>();
        List<String> titileHeadList = new ArrayList<>();
        titileHeadList.add(StringTemplateUtil.format("{}-采购订单({})", purchaseOrderDetail.getSupplierName(),
                purchaseOrderDetail.getOrderName()));
        titileHeadList.add(IReportTemplateHandler.CELL_MERGE_SPACE_LEFT);
        titileHeadList.add(IReportTemplateHandler.CELL_MERGE_SPACE_LEFT);
        titileHeadList.add(IReportTemplateHandler.CELL_MERGE_SPACE_LEFT);
        titileHeadList.add(IReportTemplateHandler.CELL_MERGE_SPACE_LEFT);
        titileHeadList.add(IReportTemplateHandler.CELL_MERGE_SPACE_LEFT);
        titileHeadList.add(IReportTemplateHandler.CELL_MERGE_SPACE_LEFT);
        titileHeadList.add(IReportTemplateHandler.CELL_MERGE_SPACE_LEFT);
        titileHeadList.add(IReportTemplateHandler.CELL_MERGE_SPACE_LEFT);
        titileHeadList.add(IReportTemplateHandler.CELL_MERGE_SPACE_LEFT);
        titileHeadList.add(IReportTemplateHandler.CELL_MERGE_SPACE_LEFT);
        titileHeadList.add(IReportTemplateHandler.CELL_MERGE_SPACE_LEFT);

        titleListMap.put(0, titileHeadList);

        Map<Integer, Map<Integer, String>> picUrlMap = new HashMap<>();

        List<Integer> rowHightList = new ArrayList<>();
        Map<Integer, List<Object>> dataListMap = convertData(purchaseOrderDetail, picUrlMap,terminal, rowHightList);
        if (null == dataListMap) {
            dataListMap = new HashMap<>();
        }

        HSSFSheet sheet = reportTemplateHandler.createSheet("内容", titleListMap, false);

        reportTemplateHandler.newWritePictureData(sheet, picUrlMap, 1, 1, 1, 1);

        reportTemplateHandler.newWriteSheetData(sheet, dataListMap, rowHightList);

        //设置自适应列宽
        for (int i = 0; i < 12; i++) {
            sheet.autoSizeColumn(i, true);
        }

        Integer rowIndexMax = Integer.valueOf(0);
        Set<Integer> keySet = dataListMap.keySet();
        for (Integer keyItem : keySet) {
            if (keyItem > rowIndexMax) {
                rowIndexMax = keyItem;
            }
        }

        for (int rowIndex = rowIndexMax - 6; rowIndex <= rowIndexMax; rowIndex++) {
            HSSFRow row = sheet.getRow(rowIndex);
            if (null == row) {
                row = sheet.createRow(rowIndex);
            }
            HSSFCell cell = row.getCell(0);
            if (null == cell) {
                cell = row.createCell(rowIndex);
            }

            if (rowIndex > 1) {

                HSSFCellStyle cellStyle = reportTemplateHandler.getWorkbook().createCellStyle();
                cellStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
                HSSFFont discountFont = reportTemplateHandler.getWorkbook().createFont();
                discountFont.setFontName("微软雅黑");
                discountFont.setFontHeightInPoints((short) 10);
                cellStyle.setFont(discountFont);
                cell.setCellStyle(cellStyle);
            }
        }
        // index = 3
        HSSFRow row = sheet.getRow(3);
        if (null == row) {
            row = sheet.createRow(3);
        }
        HSSFCell cell = row.getCell(0);
        if (null == cell) {
            cell = row.createCell(0);
        }

        HSSFCellStyle cellStyle = reportTemplateHandler.getWorkbook().createCellStyle();
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        HSSFFont discountFont = reportTemplateHandler.getWorkbook().createFont();
        discountFont.setFontName("微软雅黑");
        discountFont.setFontHeightInPoints((short) 10);
        cellStyle.setFont(discountFont);
        cell.setCellStyle(cellStyle);

        //居右
        HSSFCellStyle cellStyleCargo = reportTemplateHandler.getWorkbook().createCellStyle();
        cellStyleCargo.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
        cellStyleCargo.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        cellStyleCargo.setFillForegroundColor(HSSFColor.YELLOW.index);
        HSSFFont fontBold = reportTemplateHandler.getWorkbook().createFont();
        fontBold.setFontName("微软雅黑");
        fontBold.setBold(true);
        cellStyleCargo.setFont(fontBold);

        HSSFRow row4 = sheet.getRow(4);
        if (null == row4) {
            row4 = sheet.createRow(4);
        }

        //居中
        HSSFCellStyle cellStyleCenter = reportTemplateHandler.getWorkbook().createCellStyle();
        cellStyleCenter.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cellStyleCenter.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        cellStyleCenter.setFillForegroundColor(HSSFColor.YELLOW.index);
        cellStyleCenter.setFont(fontBold);


        HSSFCellStyle cellStyleBold = reportTemplateHandler.getWorkbook().createCellStyle();
        cellStyleBold.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cellStyleBold.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        HSSFFont discountFontBold = reportTemplateHandler.getWorkbook().createFont();
        discountFontBold.setBold(true);
        discountFont.setFontName("微软雅黑");
        cellStyleBold.setFont(discountFontBold);

        for (int colIndex = 0; colIndex < 12; colIndex++) {
            HSSFCell cellItem = row4.getCell(colIndex);
            if (null == cellItem) {
                cellItem = row4.createCell(colIndex);
            }
            cellItem.setCellStyle(cellStyleCenter);
        }

        HSSFRow row7 = sheet.getRow(rowIndexMax - 7);
        if (null == row7) {
            row7 = sheet.createRow(rowIndexMax - 7);
        }
        for (int colIndex = 0; colIndex < 12; colIndex++) {
            HSSFCell cellItem = row7.getCell(colIndex);
            if (null == cellItem) {
                cellItem = row7.createCell(colIndex);
            }
            cellItem.setCellStyle(cellStyleBold);
        }

        HSSFRow row3 = sheet.getRow(3);
        if (null == row3) {
            row3 = sheet.createRow(3);
        }
        HSSFCell cellItem = row3.getCell(0);
        if (null == cellItem) {
            cellItem = row3.createCell(0);
        }
        cellItem.setCellStyle(cellStyleCargo);


        //index = 1
        HSSFRow row1 = sheet.getRow(1);
        if (null == row1) {
            row1 = sheet.createRow(1);
        }
        for (int colIndex = 0; colIndex < 10; colIndex++) {
            if (colIndex == 0 || colIndex == 3 || colIndex == 5 || colIndex == 7 || colIndex == 9) {
                HSSFCell cellItem1 = row1.getCell(colIndex);
                if (null == cellItem1) {
                    cellItem1 = row1.createCell(colIndex);
                }
                cellItem1.setCellStyle(cellStyleCargo);
            }
        }
        //index = 2
        HSSFRow row2 = sheet.getRow(2);
        if (null == row2) {
            row2 = sheet.createRow(2);
        }
        for (int colIndex = 0; colIndex < 10; colIndex++) {
            if (colIndex == 0 || colIndex == 3 || colIndex == 5 || colIndex == 7 || colIndex == 9) {
                HSSFCell cellItem2 = row2.getCell(colIndex);
                if (null == cellItem2) {
                    cellItem2 = row2.createCell(colIndex);
                }
                cellItem2.setCellStyle(cellStyleCargo);
            }
        }
        //index = 6
        HSSFRow row6 = sheet.getRow(6);
        if (null == row6) {
            row6 = sheet.createRow(6);
        }
        for (int colIndex = 0; colIndex < 11; colIndex++) {
            if (colIndex == 6 || colIndex == 8) {
                HSSFCell cellItem6 = row6.getCell(colIndex);
                if (null == cellItem6) {
                    cellItem6 = row6.createCell(colIndex);
                }
                cellItem6.setCellStyle(cellStyleCargo);
            }
        }

        return sheet;
    }


}
