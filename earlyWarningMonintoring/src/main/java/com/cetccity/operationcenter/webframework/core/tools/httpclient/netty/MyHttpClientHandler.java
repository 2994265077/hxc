/*
package com.cetccity.operationcenter.webframework.core.tools.httpclient.netty;


import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ChannelHandler.Sharable */
/*标示一个 ChannelHandler 可以被多个 Channel 安全地共享*//*

public class MyHttpClientHandler  extends ChannelInitializer<Channel>{
    private static final Logger logger = LoggerFactory.getLogger(MyHttpClientHandler.class);

    private final boolean client;

    public MyHttpClientHandler(boolean client){
        this.client = client;
    }

    @Override
    protected void  initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

       */
/*Http编解码*//*

        if (client) { */
/*客户端，http返回请求需要解码，http请求需要编码*//*

            pipeline.addLast("decoder", new HttpResponseDecoder());
            pipeline.addLast("encoder", new HttpRequestEncoder());
        } else {*/
/*服务端,接收http响应解码，返回数据时需要编码*//*

            pipeline.addLast("decoder", new HttpRequestDecoder());
            pipeline.addLast("encoder", new HttpResponseEncoder());
        }
        */
/*聚合Http消息*//*

        if (client) {
            pipeline.addLast("codec", new HttpClientCodec());
        } else {
            pipeline.addLast("codec", new HttpServerCodec());
        }
        */
/*将最大的消息大小为512KB的HttpObjectAggregator添加到ChannelPipeline*//*

        pipeline.addLast("aggregator",
                new HttpObjectAggregator(512 * 1024));
        */
/*增加业务逻辑处理*//*

        ch.pipeline().addLast("myApplication",
                new MyHttpClientHandlerSimple());
    }

}
*/
