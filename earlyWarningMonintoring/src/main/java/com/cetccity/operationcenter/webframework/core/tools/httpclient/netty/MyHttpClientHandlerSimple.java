/*package com.cetccity.operationcenter.webframework.core.tools.httpclient.netty;

import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.net.URI;*/

/**
 * 实现的是一个进站的channel
 * ChannelInboundHandler 是进站处理器
 * ChannelOutboundHandler 是数据出站处理器
 */


/*@ChannelHandler.Sharable
public class MyHttpClientHandlerSimple extends SimpleChannelInboundHandler<HttpObject> {
    private static final Logger logger = LoggerFactory.getLogger(MyHttpClientHandlerSimple.class);*/
    /*
     * 覆盖channelActive 方法在channel被启用的时候触发（在建立连接的时候）
     * 覆盖了 channelActive() 事件处理方法。服务端监听到客户端活动
     */
    /*@Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        URI uri = new URI("http://192.168.16.220:9200/_search");
        String msg = "";
        DefaultFullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_0,
                HttpMethod.GET,uri.toString(),Unpooled.wrappedBuffer(msg.getBytes("UTF-8")));*/
        /*构建http请求*/
//        request.headers().add("Host", "192.168.16.220:9200");
//        request.headers().add("Connection", "keep-alive");
//        request.headers().add("Upgrade-Insecure-Requests", "1");
//        request.headers().add("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.139 Safari/537.36");
//        request.headers().add("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
//        request.headers().add("Accept-Encoding", "gzip, deflate");
//        request.headers().add("Accept-Language", "zh-CN,zh;q=0.9");
//        request.headers().add("content-type", "application/json; charset=UTF-8");
//        logger.debug("request:\n"+request.toString());
//        ctx.writeAndFlush(Unpooled.copiedBuffer(request.toString(), CharsetUtil.UTF_8));
//    }

    /*
     * 覆盖了 channelRead0() 事件处理方法。
     * 每当从服务端读到客户端写入信息时，
     * 其中如果你使用的是 Netty 5.x 版本时，
     * 需要把 channelRead0() 重命名为messageReceived()
     */
 //   @Override
 //   protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
 //       logger.debug("response: \n"+msg.toString());
 //   }

    /*
     * exceptionCaught() 事件处理方法是当出现 Throwable 对象才会被调用，
     * 即当 Netty 由于 IO 错误或者处理器在处理事件时抛出的异常时。
     * 在大部分情况下，捕获的异常应该被记录下来并且把关联的 channel 给关闭掉。
     * 然而这个方法的处理方式会在遇到不同异常的情况下有不同的实现，
     * 比如你可能想在关闭连接之前发送一个错误码的响应消息。
     */
 //   @Override
 //   public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
 //           throws Exception {
 //       logger.error("netty httpclient error: ",cause);
 //       ctx.close();
 //   }
//}
