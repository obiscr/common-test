import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExcelReaderTest {
    public static void main(String[] args) {
        ExcelReader readerXls = ExcelUtil.getReader(
                ResourceUtil.getStream("issue.xls"));
        readerXls.readAll();

        ExcelReader readerXlsx = ExcelUtil.getReader(
                ResourceUtil.getStream("issue.xlsx"));
        readerXlsx.readAll();

    }


    public static String getStackTrace(Throwable t){
        StringWriter sw = new StringWriter();
        try (PrintWriter pw = new PrintWriter(sw)) {
            String stackMsg;
            t.printStackTrace(pw);
            stackMsg = sw.toString();
            int casedInt = stackMsg.lastIndexOf("Caused by:");
            if (casedInt != -1) {
                stackMsg = stackMsg.substring(casedInt, stackMsg.length());
                if (stackMsg.split("at").length > 15) {
                    Pattern p = Pattern.compile("at");
                    Matcher m = p.matcher(stackMsg);
                    int num = 0, index = -1;
                    while (m.find()) {
                        num++;
                        if (15 == num) {
                            index = m.start();
                            System.out.println(m.start());
                            stackMsg = "" + stackMsg.substring(0, index);
                            break;
                        }
                    }
                }
            }
            return stackMsg;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sw.toString();

    }

}
