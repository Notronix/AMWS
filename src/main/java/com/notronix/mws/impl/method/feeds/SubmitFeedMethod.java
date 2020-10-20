package com.notronix.mws.impl.method.feeds;

import com.amazonaws.mws.model.IdList;
import com.amazonaws.mws.model.SubmitFeedRequest;
import com.notronix.mws.api.AmazonAPIException;
import com.notronix.mws.api.GeneralAmazonAPIException;
import com.notronix.mws.api.model.FeedType;
import org.apache.commons.codec.binary.Base64;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import static com.notronix.albacore.ContainerUtils.thereAreOneOrMore;

public class SubmitFeedMethod extends FeedsAPIMethod<SubmitFeedRequest>
{
    private FeedType feedType;
    private FileInputStream feedContent;
    private List<String> marketplaceIds;
    private Boolean purgeAndReplace = Boolean.FALSE;

    public SubmitFeedMethod(FeedType feedType, FileInputStream feedContent) {
        this.feedType = feedType;
        this.feedContent = feedContent;
    }

    @Override
    public SubmitFeedRequest buildRequest() throws AmazonAPIException {
        try {
            return new SubmitFeedRequest()
                    .withPurgeAndReplace(purgeAndReplace)
                    .withMarketplaceIdList(thereAreOneOrMore(marketplaceIds) ? new IdList(marketplaceIds) : null)
                    .withFeedType(feedType.value())
                    .withContentMD5(computeContentMD5Value(feedContent))
                    .withFeedContent(feedContent);
        }
        catch (Exception e) {
            throw new GeneralAmazonAPIException("An error occurred building request.", e);
        }
    }

    public SubmitFeedMethod withPurgeAndReplace(boolean purgeAndReplace) {
        this.purgeAndReplace = purgeAndReplace;
        return this;
    }

    public SubmitFeedMethod withMarketplaceIds(List<String> marketplaceIds) {
        this.marketplaceIds = new ArrayList<>(marketplaceIds);
        return this;
    }

    private static String computeContentMD5Value(FileInputStream fis) throws IOException, NoSuchAlgorithmException {
        DigestInputStream dis = new DigestInputStream(fis, MessageDigest.getInstance("MD5"));
        byte[] buffer = new byte[8192];

        //noinspection StatementWithEmptyBody
        while (dis.read(buffer) > 0) {
            ;
        }
        String md5Content = new String(Base64.encodeBase64(dis.getMessageDigest().digest()));
        fis.getChannel().position(0);

        return md5Content;
    }
}
