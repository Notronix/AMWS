package com.notronix.mws.method;

import com.notronix.mws.AmazonAPIException;

public interface AmazonAPIMethod<R>
{
    R buildRequest() throws AmazonAPIException;
}
