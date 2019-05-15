
- ## 除了BOOLEAN 之外的七种类型都有缓冲区类

- ## ByteBuffer
    - ### 构造
        - > ByteBuffer buffer = ByteBuffer.allocate(100);
            >> - 指定固定空间构造
        - > ByteBuffer buffer = ByteBuffer.wrap(byteArray);
            >> - 使用二进制数据进行构造
            >> - 这种创建方式，position依然是第0位，因为这种构造出来的缓冲区一般是用来读的。

    - ### 重要的位置标记
        - > position 当前下标位置
        - > capacity    缓冲区容量
        - > limit   可操作上限
        - > mark    标记位置, 一般情况仅仅在reset 的时候配合使用，其他情况只是将其置为-1。

    - ### 重要的操作
        - > flip 翻转缓冲区，它用于从写转换到读的过程。
        - > reset 重置缓冲区 重置到mark 标识的位置，mark 之前的将不会发生变化
        - > rewind: 重绕缓冲区
        - > clear 清空缓冲区

    - ### 常用接口
        - > buffer.array();
        - > hasRemaining();   一般用在读的时候，返回是否还有数据可读

    - ### 详细接口
        - > table 模式表格
            >> <details>
            >> <summary><mark><font color=darkred>详细说明</font></mark></summary>
            >> 
            >> <table style="font-family: Arial; line-height: 26px; margin-bottom: 20px;" cellspacing="1" cellpadding="1" border="1">
            >> 
            >> <thead>
            >>     <tr><td>方法</td><td>描述</td></tr>
            >> </thead>
            >> 
            >> <tr>
            >> <td style="text-align: center;">limit(), limit(10)等</td>
            >> <td>其中读取和设置这4个属性的方法的命名和jQuery中的val(),val(10)类似，一个负责get，一个负责set</td>
            >> </tr>
            >> <tr>
            >> <td style="text-align: center;">reset()</td>
            >> <td>把position设置成mark的值，相当于之前做过一个标记，现在要退回到之前标记的地方</td>
            >> </tr>
            >> <tr>
            >> <td style="text-align: center;">clear()</td>
            >> <td>
            >> <span style="white-space: pre;">position = 0;limit = capacity;mark = -1; </span> 有点初始化的味道，但是并不影响底层byte数组的内容</td>
            >> </tr>
            >> <tr>
            >> <td style="text-align: center;">flip()</td>
            >> <td>
            >> <span style="white-space: pre;">limit = position;position = 0;mark = -1; </span> 
            >> 翻转，也就是让flip之后的position到limit这块区域变成之前的0到position这块，翻转就是将一个处于存数据状态的缓冲区变为一个处于准备取数据的状态
            >> </td>
            >> </tr>
            >> <tr>
            >> <td style="text-align: center;">rewind()</td>
            >> <td>把position设为0，mark设为-1，不改变limit的值</td>
            >> </tr>
            >> <tr>
            >> <td style="text-align: center;">remaining()</td>
            >> <td>return limit - position;返回limit和position之间相对位置差</td>
            >> </tr>
            >> <tr>
            >> <td style="text-align: center;">hasRemaining()</td>
            >> <td>
            >> <span style="white-space: pre;">return position &lt; limit</span>返回是否还有未读内容</td>
            >> </tr>
            >> <tr>
            >> <td style="text-align: center;">compact()</td>
            >> <td>把从position到limit中的内容移到0到limit-position的区域内，position和limit的取值也分别变成limit-position、capacity。如果先将positon设置到limit，再compact，那么相当于clear()</td>
            >> </tr>
            >> <tr>
            >> <td style="text-align: center;">get()</td>
            >> <td>相对读，从position位置读取一个byte，并将position+1，为下次读写作准备</td>
            >> </tr>
            >> <tr>
            >> <td style="text-align: center;">get(int index)</td>
            >> <td>绝对读，读取byteBuffer底层的bytes中下标为index的byte，不改变position</td>
            >> </tr>
            >> <tr>
            >> <td style="text-align: center;">get(byte[] dst, int offset, int length)</td>
            >> <td>从position位置开始相对读，读length个byte，并写入dst下标从offset到offset+length的区域</td>
            >> </tr>
            >> <tr>
            >> <td style="text-align: center;">put(byte b)</td>
            >> <td><span style="white-space: pre;">相对写，向position的位置写入一个byte，并将postion+1，为下次读写作准备</span></td>
            >> </tr>
            >> <tr>
            >> <td style="text-align: center;">put(int index, byte b)</td>
            >> <td>绝对写，向byteBuffer底层的bytes中下标为index的位置插入byte b，不改变position</td>
            >> </tr>
            >> <tr>
            >> <td style="text-align: center;">put(ByteBuffer src)</td>
            >> <td>用相对写，把src中可读的部分（也就是position到limit）写入此byteBuffer</td>
            >> </tr>
            >> <tr>
            >> <td style="text-align: center;">put(byte[] src, int offset, int length)</td>
            >> <td><span style="white-space: pre;">从src数组中的offset到offset+length区域读取数据并使用相对写写入此byteBuffer</span></td>
            >> </tr>
            >> 
            >> </table>
            >> </details>
            >>

        - > md 模式表格
            >> | 方法 | 描述 |
            >> | :---: | :--- |
            >> | limit(), limit(10)等                    | 其中读取和设置这4个属性的方法的命名和jQuery中的val(),val(10)类似，一个负责get，一个负责set |
            >> | reset()                                 | 把position设置成mark的值，相当于之前做过一个标记，现在要退回到之前标记的地方 |
            >> | clear()                                 | position = 0;limit = capacity;mark = -1;  有点初始化的味道，但是并不影响底层byte数组的内容 |
            >> | flip()	                                 | limit = position;position = 0;mark = -1;  翻转，也就是让flip之后的position到limit这块区域变成之前的0到position这块，翻转就是将一个处于存数据状态的缓冲区变为一个处于准备取数据的状态 |
            >> | rewind()                                |  把position设为0，mark设为-1，不改变limit的值 |
            >> | remaining()	                            | return limit - position;返回limit和position之间相对位置差 |
            >> |hasRemaining()                           |	return position < limit返回是否还有未读内容 |
            >> | compact()                               |	把从position到limit中的内容移到0到limit-position的区域内，position和limit的取值也分别变成limit-position、capacity。如果先将positon设置到limit，再compact，那么相当于clear() |
            >> | get()                                   |	相对读，从position位置读取一个byte，并将position+1，为下次读写作准备 |
            >> | get(int index)                          |	绝对读，读取byteBuffer底层的bytes中下标为index的byte，不改变position |
            >> | get(byte[] dst, int offset, int length) | 	从position位置开始相对读，读length个byte，并写入dst下标从offset到offset+length的区域 |
            >> | put(byte b)                             | 	相对写，向position的位置写入一个byte，并将postion+1，为下次读写作准备 |
            >> | put(int index, byte b)                  |	绝对写，向byteBuffer底层的bytes中下标为index的位置插入byte b，不改变position |
            >> | put(ByteBuffer src)                     |	用相对写，把src中可读的部分（也就是position到limit）写入此byteBuffer |
            >> | put(byte[] src, int offset, int length) |	从src数组中的offset到offset+length区域读取数据并使用相对写写入此byteBuffer |


