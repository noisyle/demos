package com.noisyle.demo.jxls;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;

import org.jxls.area.XlsArea;
import org.jxls.builder.xls.XlsCommentAreaBuilder;
import org.jxls.command.EachCommand;
import org.jxls.common.AreaRef;
import org.jxls.common.CellRef;
import org.jxls.common.Context;
import org.jxls.transform.Transformer;
import org.jxls.util.JxlsHelper;
import org.jxls.util.TransformerFactory;

import com.noisyle.demo.jxls.bean.Education;
import com.noisyle.demo.jxls.bean.Employee;
import com.noisyle.demo.jxls.bean.Organization;
import com.noisyle.demo.jxls.bean.Working;

import tech.simter.jxls.ext.EachMergeCommand;

public class App {

    public static void main(String[] args) {
        XlsCommentAreaBuilder.addCommandMapping(EachMergeCommand.COMMAND_NAME, EachMergeCommand.class);

        List<Employee> employees = new LinkedList<Employee>();
        for (int i = 0; i < 10; i++) {
            List<Education> educations = new LinkedList<Education>();
            for (int j = 0; j < i % 3; j++) {
                educations.add(new Education("EDU_" + i, "教育经历" + i));
            }

            List<Working> workings = new LinkedList<Working>();
            for (int j = 0; j < i % 4; j++) {
                workings.add(new Working("WORK_" + i, "工作经历" + i));
            }

            int maxOrgdepth = 6;
            
            List<Organization> orgs = new LinkedList<Organization>();
            for (int j = 0; j < maxOrgdepth; j++) {
                if (j <= i % maxOrgdepth) {
                    orgs.add(new Organization("ORG_" + i, "机构" + i));
                } else {
                    orgs.add(null);
                }
            }

            employees.add(new Employee("EMP_" + i, "员工" + i, educations, workings, orgs));
        }

        test1(employees);
        test2(employees);
    }

    private static void test2(List<Employee> employees) {
        try {
            InputStream is = App.class.getClassLoader().getResourceAsStream("template2.xls");
            OutputStream os = new FileOutputStream("target/output2.xls");
            Context context = new Context();
            context.putVar("employees", employees);
            JxlsHelper.getInstance().processTemplate(is, os, context);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void test1(List<Employee> employees) {
        try {
            InputStream is = App.class.getClassLoader().getResourceAsStream("template1.xls");
            OutputStream os = new FileOutputStream("target/output1.xls");
            
            Transformer transformer = TransformerFactory.createTransformer(is, os);
            
            XlsArea xlsArea = new XlsArea("员工信息!A3:G3", transformer);
            
            XlsArea employeeArea = new XlsArea("员工信息!A3:G3", transformer);
            EachCommand employeeEachCommand = new EachMergeCommand("employee", "employees", employeeArea);
            xlsArea.addCommand(new AreaRef("员工信息!A3:G3"), employeeEachCommand);
            
            XlsArea educationArea = new XlsArea("员工信息!D3:E3", transformer);
            EachCommand educationEachCommand = new EachCommand("education", "employee.educations", educationArea);
            employeeArea.addCommand(new AreaRef("员工信息!D3:E3"), educationEachCommand);

            XlsArea workingArea = new XlsArea("员工信息!F3:G3", transformer);
            EachCommand workingEachCommand = new EachCommand("working", "employee.workings", workingArea);
            employeeArea.addCommand(new AreaRef("员工信息!F3:G3"), workingEachCommand);
            
            Context context = new Context();
            context.putVar("employees", employees);
            
            xlsArea.applyAt(new CellRef("员工信息!A3"), context);
            xlsArea.processFormulas();
            transformer.write();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
