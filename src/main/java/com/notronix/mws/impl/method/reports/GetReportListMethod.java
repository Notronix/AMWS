package com.notronix.mws.impl.method.reports;

import com.amazonaws.mws.model.GetReportListRequest;
import com.amazonaws.mws.model.TypeList;
import com.notronix.mws.api.model.ReportType;

import java.util.ArrayList;
import java.util.List;

import static com.notronix.albacore.ContainerUtils.thereAreOneOrMore;

public class GetReportListMethod extends ReportsAPIMethod<GetReportListRequest>
{
    private int maxCount = 10;
    private List<ReportType> reportTypes;

    public GetReportListMethod withMaxCount(int maxCount) {
        if (maxCount < 0 || maxCount > 100) {
            throw new IllegalArgumentException("maxCount must be greater than 0 and less than 100.");
        }

        this.maxCount = maxCount;

        return this;
    }

    public GetReportListMethod withReportTypes(List<ReportType> reportTypes) {
        this.reportTypes = reportTypes;
        return this;
    }

    @Override
    public GetReportListRequest buildRequest() {
        GetReportListRequest request = new GetReportListRequest().withMaxCount(maxCount);

        if (thereAreOneOrMore(reportTypes)) {
            List<String> types = new ArrayList<>();

            for (ReportType reportType : reportTypes) {
                types.add(reportType.value());
            }

            request.setReportTypeList(new TypeList(types));
        }

        return request;
    }

    public int getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }

    public List<ReportType> getReportTypes() {
        return reportTypes;
    }

    public void setReportTypes(List<ReportType> reportTypes) {
        this.reportTypes = reportTypes;
    }
}
