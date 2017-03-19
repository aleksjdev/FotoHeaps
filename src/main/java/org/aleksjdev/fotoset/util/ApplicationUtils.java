package org.aleksjdev.fotoset.util;

import org.aleksjdev.fotoset.model.ServiceConnectionPreference;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.aleksjdev.fotoset.util.ApplicationConstants.BLANK_ACCOUNT_NAME_MSG;

/**
 * Вспомогательный класс для хранения полезных методов и их повторного использования в приложении
 */
public class ApplicationUtils {

    private static final String URN_ID_REGEXP = "(\\D+):(\\d+)";
    private static final String HREF_ID_REGEXP = "(\\D+)/(\\d+)/";
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
            "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public static String getNumericIdForEntry(String elementId) {
        Pattern pattern = Pattern.compile(URN_ID_REGEXP);
        Matcher matcher = pattern.matcher(elementId);
        if (matcher.find()) {
            return matcher.group(2);
        }
        return null;
    }

    public static String getNumericIdFromHREF(String elementId) {
        Pattern pattern = Pattern.compile(HREF_ID_REGEXP);
        Matcher matcher = pattern.matcher(elementId);
        if (matcher.find()) {
            return matcher.group(2);
        }
        return null;
    }

    public static boolean validateEmail(final String input) {
        if (input == null) return Boolean.FALSE;
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    /**
     * Преобразование имени аккаунта, если был введен email
     *
     * @param connectionPreference бин настроек
     * @return преобразованный бин настроек
     */
    public static ServiceConnectionPreference validateConnectionPreference(ServiceConnectionPreference connectionPreference) {
        String accountName = connectionPreference.getUserName();
        if (accountName == null || "".equals(accountName)) throw new RuntimeException(BLANK_ACCOUNT_NAME_MSG);
        if (validateEmail(accountName)) {
            String onlyLoginName = accountName.substring(0, accountName.indexOf("@"));
            connectionPreference.setUserName(onlyLoginName);
        }
        return connectionPreference;
    }

    public static byte[] saveDataToByteArray(InputStream stream) throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] b = new byte[2048];
        int length;
        while ((length = stream.read(b)) != -1) {
            bos.write(b, 0, length);
        }
        return bos.toByteArray();
    }

    /**
     * Формирует http ответ который содержит имя скачиваемого файла
     * @param response http ответ
     * @param request http запрос
     * @param fileNameForDownload имя файла
     * @throws Exception
     */
    public static void setResponseFilename(HttpServletResponse response,
                                           HttpServletRequest request,
                                           String fileNameForDownload) throws Exception {
        String fileName = URLEncoder.encode(fileNameForDownload, "UTF-8");
        String userAgentHeader = request.getHeader("USER-AGENT");
        String userAgent = (userAgentHeader == null || userAgentHeader.isEmpty()) ? "" : userAgentHeader.toLowerCase();
        if ((userAgent.contains("chrome") || userAgent.contains("msie") || userAgent.contains("opera"))) {
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        } else {
            response.setHeader("Content-Disposition", "attachment; filename*=\"utf-8'" + fileName + "\"");
        }
    }

    /**
     * Формирование имени файла для скачивания (проверка, содержит ли имя файла расширение):
     * если не содержит - дописываем его
     *
     * @param fileName исходное имя файла
     * @return сформированное имя файла
     */
    public static String formatImageFileName(String fileName) {
        if (fileName == null || fileName.isEmpty()) return "image.jpg";
        int dotIndex = fileName.indexOf(".");
        if (dotIndex > -1) {
            fileName = fileName.substring(0, dotIndex);
        }

        return fileName + ".jpg";
    }

}
