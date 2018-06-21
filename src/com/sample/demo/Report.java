 
 
package com.sample.demo;
 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
 

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
 
public class Report {
 
                private static int passCount = 0;
                private static int failCount = 0;
                private static int skipCount = 0;
               
                static List<testresult> TestCases = new ArrayList<testresult>();
 
                public static void Fail(String testCaseID, String testCaseName) {
                                testresult testcase = new testresult(testCaseID, testCaseName, "FAIL");
                                TestCases.add(testcase);
                                failCount++;
                }
 
                public static void Pass(String testCaseID, String testCaseName) {
                                testresult testcase = new testresult(testCaseID, testCaseName, "PASS");
                                TestCases.add(testcase);
                                passCount++;
                }
 
                public static void Skip(String testCaseID, String testCaseName) {
                                testresult testcase = new testresult(testCaseID, testCaseName, "SKIP");
                                TestCases.add(testcase);
                                skipCount++;
                }
 
                public static void publish(String reportPath) {
 
                                String HTMLContent = "<HTML><HEAD><TITLE>Test Results</TITLE><style type=\"text/css\">"
                                                                + " td.datacellone {         background-color: #CC9990; color: black;}"
                                                                + " td.datacelltwo {         background-color: #9999CC; color: black; }"
                                                                + " td.datacellfail {           background-color: red; color: black; }"
                                                                + " td.datacellskip {         background-color: yellow; color: black; }"
                                                                + " td.datacellpass {        background-color: green; color: black; }</style>"
                                                                + "</HEAD><BODY><FORM NAME=\"Form1\"><div align=center vertical-align=middle ><table border=1 ><tbody><tr><td>"
                                                                + "<TABLE border=0 cellpadding=0 cellspacing=0 width=\"100%\" style=\"background-color: #E0E0E0;\"><TBODY><tr> <td height=80 style=\"font-size:18.0pt; font-weight:700; font-style:normal;"
                                                                + "font-family:Cambria, sans-serif; text-align:center;vertical-align:middle; background:#454545;color:white;\""
                                                                + " width=640>Test Results</td>"
                                                                + "<td height=80 width=\"20%\" style=\" background:#454545;color:white;\"> "
                                                                + "</td></tr></tbody></table></td></tr><tr><td>";
 
                                // Summary of the Execution
                                HTMLContent += "<table border=\"1\" style=\"background-color: #E0E0E0;\" ><tbody><tr><td><B>Total Test Run</td><td>"
                                                                + (failCount + passCount + skipCount) + "</td></tr>" + "<tr><td><B>Pass</td><td>" + passCount
                                                                + "</td></tr>" + "<tr><td><B>Fail</td><td>" + failCount + "</td></tr>" + "<tr><td><B>Skip</td><td>"
                                                                + skipCount + "</td></tr>";
 
                                HTMLContent += "</td></tr> ";
 
                                // Begining of the Table content
                                HTMLContent += "<tr><td><table border=\"1\" style=\"background-color: #E0E0E0;\" ><tbody><tr><td><B>Test Class</td><td><B>Test Case Name</td><td><B>Status</td></tr>";
                                for (int index = 0; index < TestCases.size(); index++) {
                                                String css = "datacellone";
                                                if (index % 2 == 1) {
                                                                css = "datacelltwo";
                                                }
                                                String cell = "<td class = \"" + css + "\">";
                                                HTMLContent += "<tr>" + cell + TestCases.get(index).getTestCaseID() + "</td>";
                                                HTMLContent += cell + TestCases.get(index).testCaseName + "</td>";
 
                                                if (TestCases.get(index).getTestCaseStatus().equalsIgnoreCase("fail"))
                                                                HTMLContent += "<td class = \"datacellfail\">" + TestCases.get(index).getTestCaseStatus() + "</td></tr>";
                                                else if (TestCases.get(index).getTestCaseStatus().equalsIgnoreCase("pass"))
                                                                HTMLContent += "<td class = \"datacellpass\">" + TestCases.get(index).getTestCaseStatus() + "</td></tr>";
                                                else if (TestCases.get(index).getTestCaseStatus().equalsIgnoreCase("skip"))
                                                                HTMLContent += "<td class = \"datacellskip\">" + TestCases.get(index).getTestCaseStatus() + "</td></tr>";
                                }
 
                                // Ending of the Table Content
                                HTMLContent += "</td></tr></tbody></table></div></body></html>";
                                reportWritter(HTMLContent, reportPath);
                }
 
                private static void reportWritter(String HTMLContent, String reportPath) {
                                String reportFile = "\\Report_" + getTimeStamp() + ".html";
                                File report = new File(reportPath + reportFile);
                                try {
                                                if (report.exists() == false) {
                                                                report.createNewFile();
                                                               
                                                }
                                                PrintWriter out = new PrintWriter(report);
                                                out.append(HTMLContent);
                               
                                                out.close();
                                } catch (IOException e) {
                               
                                }
                }
 
                public static String getTimeStamp() {
                                Date currentDate = new Date();
                                String currentTime = currentDate.toString();
                                currentTime = currentTime.replace(" ", "_");
                                currentTime = currentTime.replace(":", "");
                                return currentTime;
                }
               
                public static void main(String[] args) {
                               
                                Pass( "123", "tc01");
                                Pass( "156", "tc02");
                                Pass( "853", "tc03");
                               
                                Fail( "953", "tc04");
                                Fail( "852", "tc05");
                               
                                Skip( "985", "tc06");
                                Skip( "456", "tc07");
 
                               
                                getTimeStamp();
                               
                                publish(".\\");
                               
                }
 
}
 
 
 
 