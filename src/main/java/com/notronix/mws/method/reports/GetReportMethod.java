package com.notronix.mws.method.reports;

import com.amazonaws.mws.model.GetReportRequest;

import java.io.OutputStream;

public class GetReportMethod extends ReportsAPIMethod<GetReportRequest>
{
    private String reportId;
    private OutputStream outputStream;

    public GetReportMethod(String reportId, OutputStream outputStream) {
        this.reportId = reportId;
        this.outputStream = outputStream;
    }

    @Override
    public GetReportRequest buildRequest() {
        GetReportRequest request = new GetReportRequest();
        request.setReportOutputStream(outputStream);

        return request.withReportId(reportId);
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }

    public void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }
}
