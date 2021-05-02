import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;

public class ExcelReaderTest {
    public static void main(String[] args) {
        ExcelReader readerXls = ExcelUtil.getReader(
                ResourceUtil.getStream("issue.xls"));
        readerXls.readAll();

        ExcelReader readerXlsx = ExcelUtil.getReader(
                ResourceUtil.getStream("issue.xlsx"));
        readerXlsx.readAll();

    }
}
