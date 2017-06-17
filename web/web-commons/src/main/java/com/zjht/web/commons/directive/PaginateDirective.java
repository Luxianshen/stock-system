package com.zjht.web.commons.directive;

import com.github.pagehelper.PageInfo;
import freemarker.core.Environment;
import freemarker.ext.beans.BeanModel;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

import java.io.IOException;
import java.io.Writer;
import java.text.DecimalFormat;
import java.util.Map;

/**
 * 分页管理
 * Created by leaves chen<leaves615@gmail.com> on 2016/7/14.
 *
 * @Author leaves chen<leaves615@gmail.com>
 */
public class PaginateDirective implements TemplateDirectiveModel {
    @Override
    public void execute(
            Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
            throws TemplateException, IOException {
        Writer out = env.getOut();
        PageInfo result = (PageInfo) ((BeanModel) params.get("result")).getWrappedObject();
        DecimalFormat decimalFormat = new DecimalFormat();
        Integer pageIndex = result.getPageNum();
        Integer pageSize = result.getPageSize();
        Integer totalRecord = (int)result.getTotal();
        Integer pageCount = (totalRecord + pageSize - 1) / pageSize;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<div class=\"row\">\n" + "    <div class=\"col-md-5 col-sm-12\">\n" +
                             "        <div class=\"dataTables_info\" id=\"sample_1_info\">显示" + pageIndex + "/" +
                             pageCount + "页 共计" + decimalFormat.format(totalRecord) + "项</div>\n");
        stringBuilder.append("    </div>\n" + "    <div class=\"col-md-7 col-sm-12\">\n" +
                             "        <div class=\"dataTables_paginate paging_bootstrap\">\n" +
                             "            <ul class=\"pagination\">\n");
        stringBuilder.append("                <li class=\"prev " + (pageIndex == 1 ? "disabled" : "") +
                             "\"><a href=\"javascript:pageNavi(1)\"><i class=\"icon-double-angle-left\"></i></a></li>\n");
        stringBuilder.append("                <li class=\"prev " + (pageIndex == 1 ? "disabled" : "") +
                             "\"><a href=\"javascript:pageNavi(" + (pageIndex - 1) +
                             ")\"><i class=\"icon-angle-left\"></i></a></li>\n");
        int indexStart = 1, indexEnd = 1;
        if (pageIndex < 5) {
            indexStart = 1;
            indexEnd = pageCount < 10 ? pageCount : 10;
        } else if (pageIndex < 10) {
            indexStart = 1;
            indexEnd = pageCount < 10 ? pageCount : 10;
        } else if (pageIndex > (pageCount - 10)) {
            indexStart = (pageCount - 9);
        } else {
            indexEnd = pageCount;
                indexStart = (pageIndex - 4);
                indexEnd = (pageIndex + 5);
        }
        for (int i=indexStart; i <= indexEnd; i++) {
            stringBuilder.append("                <li class=\""+(pageIndex==i?"active disabled":"")+"\"><a href=\"javascript:pageNavi("+(i)+")\" >"+(i)+"</a></li>\n");
        }
        stringBuilder.append("                <li class=\"next "+(pageIndex>=pageCount?"disabled":"")+"\"><a href=\"javascript:pageNavi("+(pageIndex+1)+")\"  title=\"Next\"><i class=\"icon-angle-right\"></i></a></li>\n");
        stringBuilder.append("                <li class=\"next "+(pageIndex>=pageCount?"disabled":"")+"\"><a href=\"javascript:pageNavi("+(pageCount)+")\"  title=\"Next\"><i class=\"icon-double-angle-right\"></i></a></li>\n" +
                             "            </ul>\n" +
                             "        </div>\n" +
                             "    </div>\n" +
                             "</div>");

        out.append(stringBuilder);
    }
}
