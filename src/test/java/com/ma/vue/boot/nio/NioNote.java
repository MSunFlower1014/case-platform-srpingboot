package com.ma.vue.boot.nio;

/**
 * 主要包含 channel，buffer，selector
 * 即管道，缓冲，选择器
 * 管道通过缓冲进行读写操作
 * 然后将管道注册到选择器，监听对应的事件触发
 * register 方法的第二个 int 型参数（使用二进制的标记位）用于表明需要监听哪些感兴趣的事件，共以下四种事件：
 *
 * SelectionKey.OP_READ
 * 对应 00000001，通道中有数据可以进行读取
 *
 * SelectionKey.OP_WRITE
 * 对应 00000100，可以往通道中写入数据
 *
 * SelectionKey.OP_CONNECT
 * 对应 00001000，成功建立 TCP 连接
 *
 * SelectionKey.OP_ACCEPT
 * 对应 00010000，接受 TCP 连接
 *
 * 循环通过选择器获取准备好的管道进行处理
 */
public class NioNote {
}
