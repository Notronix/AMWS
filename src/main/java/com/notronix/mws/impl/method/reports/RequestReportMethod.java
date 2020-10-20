package com.notronix.mws.impl.method.reports;

import com.amazonaws.mws.model.IdList;
import com.amazonaws.mws.model.RequestReportRequest;
import com.notronix.mws.api.service.AmazonAPIException;
import com.notronix.mws.api.model.ReportType;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class RequestReportMethod extends ReportsAPIMethod<RequestReportRequest>
{
    private ReportType reportType;
    private Date startDate;
    private Date endDate;
    private List<String> marketplaceIds;

    @Override
    public RequestReportRequest buildRequest() throws AmazonAPIException {
        IdList marketplaces = null;
        if (marketplaceIds != null) {
            marketplaces = new IdList(marketplaceIds);
        }

        return new RequestReportRequest()
                .withReportType(reportType.value())
                .withStartDate(buildDate(startDate))
                .withEndDate(buildDate(endDate))
                .withMarketplaceIdList(marketplaces);
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

    public RequestReportMethod withMarketplaceIds(List<String> marketplaceIds) {
        this.marketplaceIds = marketplaceIds;
        return this;
    }

    private XMLGregorianCalendar buildDate(Date date) {
        if (date == null) {
            return null;
        }

        LocalDateTime ldt = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());

        try {
            DatatypeFactory df = DatatypeFactory.newInstance();
            return df.newXMLGregorianCalendar(new GregorianCalendar(ldt.getYear(),
                    ldt.getMonthValue() - 1,
                    ldt.getDayOfMonth(),
                    ldt.getHour(),
                    ldt.getMinute(),
                    ldt.getSecond()));
        }
        catch (DatatypeConfigurationException e) {
            throw new RuntimeException("Unable to create XMLGregorianCalendar", e);
        }
    }
}
