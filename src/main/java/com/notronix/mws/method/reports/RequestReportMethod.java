package com.notronix.mws.method.reports;

import com.amazonaws.mws.model.RequestReportRequest;
import com.notronix.mws.AmazonAPIException;
import com.notronix.mws.model.ReportType;
import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;

import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class RequestReportMethod extends ReportsAPIMethod<RequestReportRequest>
{
    private ReportType reportType;
    private Date startDate;
    private Date endDate;

    @Override
    public RequestReportRequest buildRequest() throws AmazonAPIException {
        return new RequestReportRequest()
                .withReportType(reportType.value())
                .withStartDate(buildDate(startDate))
                .withEndDate(buildDate(endDate));
    }

    public RequestReportMethod withReportType(ReportType reportType) {
        this.reportType = reportType;
        return this;
    }

    public RequestReportMethod withStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public RequestReportMethod withEndDate(Date endDate) {
        this.endDate = endDate;
        return this;
    }

    private XMLGregorianCalendar buildDate(Date date) {
        if (date == null) {
            return null;
        }

        LocalDateTime ldt = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());

        return XMLGregorianCalendarImpl.createDateTime(
                ldt.getYear(),
                ldt.getMonthValue(),
                ldt.getDayOfMonth(),
                ldt.getHour(),
                ldt.getMinute(),
                ldt.getSecond());
    }
}
