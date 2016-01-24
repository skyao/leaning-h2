数据库特性和URL
=========

H2数据库支持多种连接模式和连接设置，这是通过使用不同的数据库URL来实现的。

# 设置运行模式

## 内嵌模式

### 本地文件连接

连接到本地数据库的URL是"jdbc:h2:[file:][<path>]<databaseName>"。其中前缀"file:"是可选的。如果没有设置路径或者只使用了相对路径，则当前工作目录将被作为起点使用。路径和数据库名称的大小写敏感取决于操作系统， 不过推荐只使用小写字母。数据库名称必须最少三个字母（File.createTempFile的限制）。数据库名字不容许包含分号";"。可以使用"~/"来指向当前用户home目录，例如"jdbc:h2:~/test"。

内嵌模式下的本地文件连接URL的格式是：

	jdbc:h2:[file:][<path>]<databaseName>

例如：

    jdbc:h2:~/test
    jdbc:h2:file:/data/sample
    jdbc:h2:file:C:/data/sample (仅仅用于Windows)

### 内存数据库连接

对于特殊使用场景(例如：快速原型开发，测试，高性能操作，只读数据库)，可能不需要持久化数据或数据的改变。H2数据库支持内存模式，数据不被持久化。

内存数据库资料存储有两种方式：

1. private/私有

	在一些场景中，要求仅仅有一个到内存数据库的连接。这意味着被开启的数据库是私有的(private)。在这个场景中，数据库URL是"jdbc:h2:mem:"。在同一个虚拟机中开启两个连接意味着打开两个不同的（私有）数据库。

2. named/命名： 其他应用可以通过使用命令来访问

	有时需要到同一个内存数据库的多个连接，在这个场景中，数据库URL必须包含一个名字。例如："jdbc:h2:mem:db1"。仅在同一个虚拟机和class loader下可以通过这个URL访问到同样的数据库。

对应的内存数据库连接URL的格式是：

- 私有格式：jdbc:h2:mem:
- 命名格式：jdbc:h2:mem:< databaseName >

命名格式的例子如下：

	jdbc:h2:mem:test

默认，最后一个连接到数据库的连接关闭时就会关闭数据库。对于一个内存数据库，这意味着内容将会丢失。为了保持数据库开启，可以添加"DB_CLOSE_DELAY=-1"到数据库URL中。为了让内存数据库的数据在虚拟机运行时始终存在，请使用"jdbc:h2:mem:test;DB_CLOSE_DELAY=-1"。

## 服务器模式

为了从其他进程或者其他机器访问一个内存数据库，需要在创建内存数据库的进程中启动一个TCP服务器。其他进程就需要通过TCP/IP 或者 TLS来访问数据库，使用URL类似"jdbc:h2:tcp://localhost/mem:db1".

H2支持两种连接方式， TCP和TLS：

### 使用TCP

格式如下，通过指定server和port连接到以内嵌模式运行的H2服务器，参数path和databaseName对应该内嵌数据库启动时的参数：

	jdbc:h2:tcp://<server>[:<port>]/[<path>]<databaseName>

范例：

    jdbc:h2:tcp://localhost/~/test		（对应内嵌模式下的jdbc:h2:~/test)
    jdbc:h2:tcp://dbserv:8084/~/sample （对应内嵌模式下的jdbc:h2:~/sample)
    jdbc:h2:tcp://localhost/mem:test	（对应内嵌模式下的jdbc:h2:mem:test)

### 使用TLS

可以通过采用SSL在传输层对数据库访问内容做加密，URL格式和TCP相同，只是TCP替换为ssl：

	jdbc:h2:ssl://<server>[:<port>]/<databaseName>

范例：

	jdbc:h2:ssl://localhost:8085/~/sample;

## 混合模式

通过设置AUTO_SERVER=TRUE可以开启自动混合模式：

	jdbc:h2:<url>;AUTO_SERVER=TRUE

范例：

	jdbc:h2:~/test;AUTO_SERVER=TRUE


# 数据库设置

## 指定用户名和密码

	jdbc:h2:<url>[;USER=<username>][;PASSWORD=<value>]

范例：

	jdbc:h2:file:~/sample;USER=sa;PASSWORD=123

## 调试跟踪设置

通过在URL中指定TRACE_LEVEL_FILE可以设置debug级别，取值为：

- 0： （TODO，未知）
- 1：
- 2：
- 3：

格式如下：

	jdbc:h2:<url>;TRACE_LEVEL_FILE=<level 0..3>

范例：

	jdbc:h2:file:~/sample;TRACE_LEVEL_FILE=3

## 忽略未知设置

猜测应该是为了不同版本之间的兼容性吧，格式如下：

	jdbc:h2:<url>;IGNORE_UNKNOWN_SETTINGS=TRUE

## 兼容模式

这是一个至关重要的特性，设置兼容模式后可以非常大程度的模拟该数据库的一些常见语法和行为，以便mock，格式如下：

	jdbc:h2:<url>;MODE=<databaseType>

范例：

	jdbc:h2:~/test;MODE=MYSQL

TODO：兼容数据库的列表和设置名，后面再细看。

## 当虚拟机退出时不要关闭数据库

默认情况下H2在虚拟机退出时是会自动关闭数据库， 通过在URl中设置DB_CLOSE_ON_EXIT=FALSE可以改变这个行为（注：暂不清楚申明情况下需要不关闭？），格式：

	jdbc:h2:<url>;DB_CLOSE_ON_EXIT=FALSE

# 数据库文件设置

## 文件加密

可以对数据库文件做加密，通过在URL中指定"CIPHER=AES":

	jdbc:h2:<url>;CIPHER=AES

范例：

    jdbc:h2:ssl://localhost/~/test;CIPHER=AES
    jdbc:h2:file:~/secure;CIPHER=AES

## 文件加锁方法

H2可以对数据库文件做加锁操作，指定FILE_LOCK：

	jdbc:h2:<url>;FILE_LOCK={FILE|SOCKET|NO}

范例：

	jdbc:h2:file:~/private;CIPHER=AES;FILE_LOCK=SOCKET

## 仅当数据库存在时打开

默认情况下，当URL指定的数据库文件不存在时，H2会自动创建文件并打开该数据库。可以通过设置IFEXISTS=TRUE来改变这个行为，仅当指定数据库已经存在时才打开：

	jdbc:h2:<url>;IFEXISTS=TRUE

范例：

	jdbc:h2:file:~/sample;IFEXISTS=TRUE

## 定制文件访问模式

TODO：暂不清楚具体有那些文件访问模式，如何设置，后面再细看。格式如下：

	jdbc:h2:<url>;ACCESS_MODE_DATA=rws

## 将数据库存放在zip文件中

可以通过这个方式将数据库文件存放在一个zip文件中，虽然不清楚何时适合如此做，格式如下：

	jdbc:h2:zip:<zipFileName>!/<databaseName>

范例：

	jdbc:h2:zip:~/db.zip!/test

# 数据库连接设置

## 在连接时执行SQL

可以通过下面的方式在连接到数据库时立即保存在文件中的SQl文件：

	jdbc:h2:<url>;INIT=RUNSCRIPT FROM '~/create.sql'

如果需要执行多个SQL文件，则可以重复多次：

	jdbc:h2:file:~/sample;INIT=RUNSCRIPT FROM '~/create.sql'\;RUNSCRIPT FROM '~/populate.sql'


## 自动重连

	jdbc:h2:<url>;AUTO_RECONNECT=TRUE

范例：

	jdbc:h2:tcp://localhost/~/test;AUTO_RECONNECT=TRUE


Page size	jdbc:h2:<url>;PAGE_SIZE=512
Changing other settings	jdbc:h2:<url>;<setting>=<value>[;<setting>=<value>...]
jdbc:h2:file:~/sample;TRACE_LEVEL_SYSTEM_OUT=3

注：资料来源[官方文档--Database URL](http://h2database.com/html/features.html#database_url)