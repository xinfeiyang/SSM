package com.security.service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import com.security.bean.User;
import freemarker.template.Template;

/**
 * 发送邮件
 */
@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private SimpleMailMessage simpleMailMessage;
	
	@Autowired
	private FreeMarkerConfigurer freeMarkerConfigurer;

	public void sendEmail() throws Exception {
		Template template = freeMarkerConfigurer.getConfiguration().getTemplate("email.ftl");
		User user = new User();
		user.setName("冯朗");
		user.setGender("fenglang2016@126.com");
		Map<String, Object> map = new HashMap<>();
		map.put("user", user);
		String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);

		// 建立邮件消息
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
		// 设置发件人邮箱;
		messageHelper.setFrom(simpleMailMessage.getFrom());
		// 设置收件人邮箱;
		messageHelper.setTo(simpleMailMessage.getTo());
		// 设置邮件主题;
		messageHelper.setSubject(simpleMailMessage.getSubject());
		// true表示启动HTML格式的邮件;
		messageHelper.setText(text, true);
		// 添加附件;
		File file = new File("f:/upload/hibernate讲义.pdf");
		messageHelper.addAttachment(MimeUtility.encodeWord(file.getName()), file);
		// 发送邮件
		javaMailSender.send(message);
	}

}
