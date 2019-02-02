package com.noisyle.demo.jxls;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;

import org.jxls.builder.xls.XlsCommentAreaBuilder;
import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;

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
                educations.add(new Education("EDU_" + j, "教育经历" + j));
            }

            List<Working> workings = new LinkedList<Working>();
            for (int j = 0; j < i % 4; j++) {
                workings.add(new Working("WORK_" + j, "工作经历" + j));
            }

            int maxOrgdepth = 6;
            
            List<Organization> orgs = new LinkedList<Organization>();
            for (int j = 0; j < maxOrgdepth; j++) {
                if (j <= i % maxOrgdepth) {
                    orgs.add(new Organization("ORG_" + j, "机构" + j));
                } else {
                    orgs.add(null);
                }
            }

            employees.add(new Employee("EMP_" + i, "员工" + i, educations, workings, orgs));
        }

        try {
            InputStream is = App.class.getClassLoader().getResourceAsStream("template.xls");
            OutputStream os = new FileOutputStream("target/output.xls");
            Context context = new Context();
            context.putVar("employees", employees);
            JxlsHelper.getInstance().processTemplate(is, os, context);
        } catch (Exception e) {
        }
    }
}
