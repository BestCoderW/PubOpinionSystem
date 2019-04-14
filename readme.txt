环境：eclipse + java1.6 + tomcat8.0 + selenium3.0 + chrome 71 或 firefox 66 或 ie 11
所需jar包：commons-email-1.5.jar + mail-1.4.jar

完成功能：基于慕课网
		1.在Login.main()中设置好慕课网账户及密码
		2.运行main，通过读取elemen.properties配置文件获取相应元素实现登录操作
		3.把当前页面截图并保存到Login.takeScreenShot()设置好的路径下
		
		4.在SendEmailTest中设置邮箱信息（接收者和发送者）获取指定路径下文件并发送
		
		注：发送邮件功能尚未与Login集成，需单独运行