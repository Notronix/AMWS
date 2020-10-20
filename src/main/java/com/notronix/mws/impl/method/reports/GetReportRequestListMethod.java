package com.notronix.mws.impl.method.reports;

import com.amazonaws.mws.model.GetReportRequestListRequest;
import com.amazonaws.mws.model.IdList;
import com.notronix.mws.api.service.AmazonAPIException;

import java.util.List;

public class GetReportRequestListMethod extends ReportsAPIMethod<GetReportRequestListRequest>
{
    private List<String> reportRequestIds;

    @Override
    public GetReportRequestListRequest buildRequest() throws AmazonAPIException {
        return new GetReportRequestListRequest()
                .withReportRequestIdList(new IdList(reportRequestIds));
    }

    public GetReportRequestListMethod withReportRequestIds(List<String> reportRequestIds) {
        this.reportRequestIds = reportRequestIds;
        return this;
    }
}
