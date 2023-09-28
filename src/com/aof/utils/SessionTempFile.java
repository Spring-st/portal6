package com.aof.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionTempFile {
    public static final String TEMP_FILE_LIST = "X_SESSIONTEMPFILELIST";

    public static int createNewTempFile(HttpServletRequest request) throws IOException {
        HttpSession session = request.getSession();
        List<File> tempFileList = (List)session.getAttribute("X_SESSIONTEMPFILELIST");
        if (tempFileList == null) {
            tempFileList = new ArrayList();
            session.setAttribute("X_SESSIONTEMPFILELIST", tempFileList);
        }
        File tempFile = File.createTempFile("sessionTempFile", ".tmp");
        tempFileList.add(tempFile);
        return tempFileList.size();
    }

    public static File getTempFile(int index, HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<File> tempFileList = (List)session.getAttribute("X_SESSIONTEMPFILELIST");
        if (tempFileList != null && tempFileList.size() >= index)
            return tempFileList.get(index - 1);
        return null;
    }

    public static void clearTempFile(HttpSession session) {
        List tempFileList = (List)session.getAttribute("X_SESSIONTEMPFILELIST");
        if (tempFileList != null)
            for (Iterator<File> itor = tempFileList.iterator(); itor.hasNext(); ) {
                File tempFile = itor.next();
                tempFile.delete();
            }
    }
}
