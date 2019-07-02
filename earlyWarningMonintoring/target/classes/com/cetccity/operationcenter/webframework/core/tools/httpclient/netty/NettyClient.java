/*
package com.cetccity.operationcenter.webframework.core.tools.httpclient.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;

import java.net.InetSocketAddress;

public class NettyClient {
    private final String host;
    private final int port;

    public NettyClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws Exception {
        */
/*创建NIO线程组*//*

        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)              */
/*指定线程组*//*

                    .channel(NioSocketChannel.class)    */
/*指定处理客户端事件,需要适用于NIO的实现*//*

                    .remoteAddress(new InetSocketAddress(host, port))
                    .handler(new MyHttpClientHandler(true));
            ChannelFuture f = b.connect().sync();   */
/*连接到远程节点，阻塞等待直到连接完成*//*

            f.channel().closeFuture().sync();   */
/*阻塞，直到channel关闭*//*

        } finally {
            group.shutdownGracefully().sync(); */
/*关闭连接池并且释放所有资源*//*

        }
    }

    public static void main(String[] args) throws Exception {
        String host = "192.168.16.220";
        int port = 9200;
        new NettyClient(host, port).start();

    }
}
*/
