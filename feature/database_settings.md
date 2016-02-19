数据库设置
===========

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
