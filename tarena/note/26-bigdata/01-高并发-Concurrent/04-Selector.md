

- ## 多路复用选择器

    - > Server 端
        >> <details>
        >> <summary><mark><font color=darkred>示例代码</font></mark></summary>
        >> 
        >>      public static void main(String[] args) throws IOException {
        >>          ServerSocketChannel ssc = ServerSocketChannel.open();
        >>          
        >>          ssc.bind(new InetSocketAddress(8090));
        >>          
        >>          ssc.configureBlocking(false);   // 设置非阻塞
        >>          
        >>          Selector selc = Selector.open();    // 创建选择器
        >>          
        >>          ssc.register(selc, SelectionKey.OP_ACCEPT); // 注册监听事件
        >>          
        >>          while (true) {
        >>              selc.select();  // 阻塞事件的发生
        >>              // 获取所有已发生的监听事件
        >>              Set<SelectionKey> keys = selc.selectedKeys();   
        >>              Iterator<SelectionKey> iter = keys.iterator();
        >>              while (iter.hasNext()) {
        >>                  SelectionKey key = iter.next();
        >>                  
        >>                  if (key.isAcceptable()) {
        >>                      ServerSocketChannel sscx = (ServerSocketChannel) key.channel();
        >>                      SocketChannel sc = sscx.accept();
        >>                      sc.configureBlocking(false);
        >>                      sc.register(selc, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
        >>                  }
        >>                  
        >>                  if (key.isReadable()) {
        >>                      SocketChannel sc = (SocketChannel) key.channel();
        >>                      ByteBuffer buffer = ByteBuffer.allocate(1024);
        >>                      sc.read(buffer);
        >>                  }
        >>                  
        >>                  if (key.isWritable()) {
        >>                      
        >>                  }
        >>                  
        >>                  iter.remove();  // 事件处理完成之后需要将其从队列中移除,不然系统 会认为你还没有处理此事件
        >>              }
        >>          }
        >>      }
        >> </details>


