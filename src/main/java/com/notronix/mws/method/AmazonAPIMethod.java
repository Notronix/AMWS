package com.notronix.mws.method;

import com.notronix.mws.AmazonAPIException;

public interface AmazonAPIMethod<Request>
{
    Request buildRequest() throws AmazonAPIException;
}
