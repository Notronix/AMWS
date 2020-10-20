package com.notronix.mws.impl.method.feeds;

import com.amazonaws.mws.model.GetFeedSubmissionResultRequest;
import com.notronix.mws.api.AmazonAPIException;

import java.io.OutputStream;

public class GetFeedSubmissionResultMethod extends FeedsAPIMethod<GetFeedSubmissionResultRequest>
{
    private String feedSubmissionId;
    private OutputStream outputStream;

    public GetFeedSubmissionResultMethod(String feedSubmissionId, OutputStream outputStream) {
        this.feedSubmissionId = feedSubmissionId;
        this.outputStream = outputStream;
    }

    @Override
    public GetFeedSubmissionResultRequest buildRequest() throws AmazonAPIException {
        GetFeedSubmissionResultRequest request = new GetFeedSubmissionResultRequest();
        request.setFeedSubmissionResultOutputStream(outputStream);

        return request.withFeedSubmissionId(feedSubmissionId);
    }

    public String getFeedSubmissionId() {
        return feedSubmissionId;
    }

    public void setFeedSubmissionId(String feedSubmissionId) {
        this.feedSubmissionId = feedSubmissionId;
    }

    public GetFeedSubmissionResultMethod withFeedSubmissionId(String feedSubmissionId) {
        this.feedSubmissionId = feedSubmissionId;
        return this;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }

    public void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }
}
