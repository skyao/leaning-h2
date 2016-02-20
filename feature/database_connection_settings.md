数据库连接设置
===========

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

