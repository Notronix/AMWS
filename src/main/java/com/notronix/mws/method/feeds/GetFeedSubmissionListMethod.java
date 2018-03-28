package com.notronix.mws.method.feeds;

import com.amazonaws.mws.model.*;
import com.notronix.mws.AmazonAPIException;
import com.notronix.mws.model.FeedType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.notronix.albacore.ContainerUtils.thereAreNo;
import static java.util.Collections.singletonList;

public class GetFeedSubmissionListMethod extends FeedsAPIMethod<GetFeedSubmissionListRequest>
{
    private String feedSubmissionId;
    private Integer maxCount;
    private List<FeedType> feedTypes;
    private List<FeedProcessingStatus> feedStatuses;

    @Override
    public GetFeedSubmissionListRequest buildRequest() throws AmazonAPIException {
        return new GetFeedSubmissionListRequest()
                .withFeedSubmissionIdList(feedSubmissionId == null ? null : new IdList(singletonList(feedSubmissionId)))
                .withMaxCount(maxCount)
                .withFeedTypeList(thereAreNo(feedTypes) ? null : new TypeList(
                        feedTypes.stream().map(FeedType::value).collect(Collectors.toList())))
                .withFeedProcessingStatusList(thereAreNo(feedStatuses) ? null : new StatusList(
                        feedStatuses.stream().map(FeedProcessingStatus::value).collect(Collectors.toList())));
    }

    public String getFeedSubmissionId() {
        return feedSubmissionId;
    }

    public void setFeedSubmissionId(String feedSubmissionId) {
        this.feedSubmissionId = feedSubmissionId;
    }

    public GetFeedSubmissionListMethod withFeedSubmissionId(String feedSubmissionId) {
        this.feedSubmissionId = feedSubmissionId;
        return this;
    }

    public Integer getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(Integer maxCount) {
        this.maxCount = maxCount;
    }

    public List<FeedType> getFeedTypes() {
        return feedTypes;
    }

    public void setFeedTypes(List<FeedType> feedTypes) {
        this.feedTypes = feedTypes;
    }

    public void addFeedType(FeedType feedType) {
        if (feedTypes == null) {
            feedTypes = new ArrayList<>();
        }

        feedTypes.add(feedType);
    }

    public List<FeedProcessingStatus> getFeedStatuses() {
        return feedStatuses;
    }

    public void setFeedStatuses(List<FeedProcessingStatus> feedStatuses) {
        this.feedStatuses = feedStatuses;
    }

    public void addFeedStatus(FeedProcessingStatus feedProcessingStatus) {
        if (feedStatuses == null) {
            feedStatuses = new ArrayList<>();
        }

        feedStatuses.add(feedProcessingStatus);
    }
}
