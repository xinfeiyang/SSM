#<font color="#FF1493">Spring数据初始化</font>

	@Component
	public class InitListener implements ApplicationListener<ContextRefreshedEvent>{
	
		@Resource
		private EmailService service;
		
		@Override
		public void onApplicationEvent(ContextRefreshedEvent event) {
			//进行某些初始化操作
			System.out.println("----------------->数据初始化了....");
		}
	
	}

#<font color="#FF1493">Spring的分支配置文件的开发</font>

	<!-- 在一个配置文件中引入另外一个配置文件 -->
	<import resource="spring-quartz.xml"/>

#<font color="#FF1493">Spring的石英调度的开发(详情可见黑马程序员-物流BOS系统-day13)</font>

	在pom.xml中配置项目依赖;
	<!-- 使用石英调度需要使用 -->
	<dependency>
		<groupId>org.springframework</groupId>
		<artifactId>spring-context-support</artifactId>
		<version>4.3.8.RELEASE</version>
	</dependency>

	<dependency>
		<groupId>org.quartz-scheduler</groupId>
		<artifactId>quartz</artifactId>
		<version>2.2.3</version>
	</dependency>

	<dependency>
		<groupId>org.quartz-scheduler</groupId>
		<artifactId>quartz-jobs</artifactId>
		<version>2.2.3</version>
	</dependency>


	1、创建要执行的任务;
	public class MyJob{

		@Resource
		private EmailService service;
	
		private String username;
		private String password;
		private String smtpServer;
	
		public void run() {
			service.sendEmail();
			System.out.println("定时任务执行了...."
					+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
							.format(new Date()));
			try {
				sendEmail();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
		//测试发送邮件
		private void sendEmail() throws Exception {
			final Properties mailProps = new Properties();
			mailProps.put("mail.smtp.host",getSmtpServer());
			mailProps.put("mail.smtp.auth", "true");
			mailProps.put("mail.username",getUsername());
			mailProps.put("mail.password",getPassword());
	
			// 构建授权信息，用于进行SMTP进行身份验证
			Authenticator authenticator = new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					// 用户名、密码
					String userName = mailProps.getProperty("mail.username");
					String password = mailProps.getProperty("mail.password");
					return new PasswordAuthentication(userName, password);
				}
			};
			// 使用环境属性和授权信息，创建邮件会话
			Session mailSession = Session.getInstance(mailProps, authenticator);
			// 创建邮件消息
			MimeMessage message = new MimeMessage(mailSession);
			// 设置发件人
			InternetAddress from = new InternetAddress(mailProps.getProperty("mail.username"));
			message.setFrom(from);
			// 设置收件人
			InternetAddress to = new InternetAddress("1875268702@qq.com");
			message.setRecipient(RecipientType.TO, to);
			// 设置邮件标题
			message.setSubject("新邮件");
			// 设置邮件的内容体
			message.setContent("测试邮件", "text/html;charset=UTF-8");
			// 发送邮件
			Transport.send(message);
		}
	

		//将username,password,smtpServer采用set方法注入
		public void setUsername(String username) {
			this.username = username;
		}
	
		public void setPassword(String password) {
			this.password = password;
		}
	
		public void setSmtpServer(String smtpServer) {
			this.smtpServer = smtpServer;
		}
	
		public String getUsername() {
			return username;
		}
	
		public String getPassword() {
			return password;
		}
	
		public String getSmtpServer() {
			return smtpServer;
		}
	
	}

---------------------------------------------------------------------------------

	2、在spring-quartz.xml中进行配置
	<?xml version="1.0" encoding="UTF-8"?>
	<beans xmlns="http://www.springframework.org/schema/beans"
		xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
		xmlns:context="http://www.springframework.org/schema/context"
		xsi:schemaLocation="http://www.springframework.org/schema/beans 
							http://www.springframework.org/schema/beans/spring-beans.xsd
							http://www.springframework.org/schema/context
							http://www.springframework.org/schema/context/spring-context.xsd">
	
		<!-- 注册自定义作业类 -->
		<bean id="myJob" class="com.security.quarz.MyJob">
			<property name="username" value="itcast_server@126.com"/>
			<property name="password" value="147963qP"/>
			<property name="smtpServer" value="smtp.126.com"/>
		</bean>
		
		<!-- 配置JobDetail -->
		<bean id="myJobDetail" class="org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean">
			<!-- 注入目标对象 -->
			<property name="targetObject">
				<ref bean="myJob"/>
			</property>
			<!-- 注入目标方法 -->
			<property name="targetMethod">
				<value>run</value>
			</property>
		</bean>
		
		<!-- 配置触发器 -->
		<bean id="trigger" class="org.springframework.scheduling.quartz.CronTriggerFactoryBean">
			<!-- 注入任务详情对象 -->
			<property name="jobDetail">
				<ref bean="myJobDetail"/>
			</property>
			<!-- 注入cron表达式，通过这个表达式指定触发的时间点 -->
			<property name="cronExpression">
				<value>0/30 * * * * ?</value>
			</property>
		</bean>
		
		<!-- 配置调度工厂 -->
		<bean id="sheduler" class="org.springframework.scheduling.quartz.SchedulerFactoryBean">
			<!-- 注入触发器,可配置多个 -->
			<property name="triggers">
				<list>
					<ref bean="trigger"/>
				</list>
			</property>
		</bean>
		
	</beans>

------------------------------------------------------------------------------

	3、在applicationContext.xml(Spring主配置文件中的配置)
	<?xml version="1.0" encoding="UTF-8"?>
	<beans xmlns="http://www.springframework.org/schema/beans"
		xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:context="http://www.springframework.org/schema/context"
		xsi:schemaLocation="http://www.springframework.org/schema/beans 
							http://www.springframework.org/schema/beans/spring-beans.xsd
							http://www.springframework.org/schema/context
							http://www.springframework.org/schema/context/spring-context.xsd">
		
		<!--Spring可以扫描到的包-->
		<context:component-scan base-package="com.security" ></context:component-scan>
		
		<!-- 在一个配置文件中引入另外一个配置文件 -->
		<import resource="spring-quartz.xml"/>
	
	</beans>

--------------------------------------------------------------------------------

	4、注意事项：
	在测试时，在MyJob中采用EmailService进行测试，发现其为空，发现是因为没用配置
	<context:component-scan base-package="com.security" ></context:component-scan>
	
	
	

		