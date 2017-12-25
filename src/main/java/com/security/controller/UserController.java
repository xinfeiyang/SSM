package com.security.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.security.bean.Bean;
import com.security.bean.CustomBean;
import com.security.bean.Department;
import com.security.bean.User;
import com.security.exception.BusinessException;
import com.security.service.DepartmentService;
import com.security.service.EmailService;
import com.security.service.ThumbnailService;
import com.security.service.UploadService;
import com.security.service.UserService;
import com.security.util.HttpClientUtil;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private UploadService uploadService;
	
	@Autowired
	private ThumbnailService thumbnailService;
	
	@PostMapping("/file/upload")
	public ModelAndView uploadFile(@RequestParam("image")CommonsMultipartFile file,HttpSession session){
		String uploadPath = "/resource/img";//图片保存位置
		String realUploadPath = session.getServletContext().getRealPath(uploadPath);
		System.out.println("-->"+realUploadPath);
		String imageUrl = uploadService.uploadImage(file, uploadPath, realUploadPath);//图片原图路径
		String thumbnailUrl = thumbnailService.thumbnail(file, uploadPath, realUploadPath);//图片缩略图路径
		
		ModelAndView md = new ModelAndView();
		md.addObject("imageURL", imageUrl);
		md.addObject("thumbnailURL", thumbnailUrl);
		md.setViewName("thumbnail");
		
		return md;
	}
	
	@GetMapping("/sendEmail")
	public void sendEmail() throws Exception{
		emailService.sendEmail();
	}
	
	@GetMapping("/list")
	public String getUsers(Model model){
		List<User> list = userService.getUsers();
		model.addAttribute("list",list);
		return "success";
	}
	
	@GetMapping(value="/useredit/{id}")
	public String userEdit(@PathVariable("id") Integer id,Model model){
		User user=userService.getUserById(id);
		List<Department> departments=departmentService.getDepartments();
		model.addAttribute("user",user);
		model.addAttribute("departments",departments);
		return "edit";
	}
	
	/**
	 * Redirect:重定向
	 * 重定向后浏览器地址栏变更为重定向的地址
	 * 重定向相当于执行了新的request和response，所以之前的请求参数都会丢失
	 * 如果要指定请求参数，需要在重定向的url后面添加 ?itemId=1 这样的请求参数
	 */
	@PostMapping("/update")
	public String update(User user){
		userService.updateUser(user);
		return "redirect:/list";
	}
	
	@PostMapping("/queryByCondition")
	public String query(String condition,Model model){
		System.out.println(condition);
		List<User> list = userService.getUsersByConditon(condition);
		model.addAttribute("list",list);
		return "success";
	}
	
	/**
	 * 测试Json;
	 * 注意：在使用时,为了解决延迟加载出现的错误,应该在实体类上添加@JsonIgnoreProperties, 
	 * 作用是json序列化时忽略bean中的一些属性序列化和反序列化时抛出的异常.
	 * @return
	 */
	@ResponseBody
	@GetMapping("/json")
	public List<User> testJson(){
		List<User> list = userService.getUsers();
		return list;
	}
	
	@GetMapping("/testforward")
	public String forward(Model model){
		List<String> list=new ArrayList<>();
		list.add("冯朗");
		list.add("冯跃");
		list.add("李晓丹");
		list.add("李佩丹");
		list.add("郑艳玲");
		list.add("郑玲玲");
		model.addAttribute("list",list);
		return "forward";
	}
	
	/**
	 * forward:请求转发
	 * 使用转发的方式实现,转发后浏览器地址栏还是原来的请求地址;
	 * 转发并没有执行新的request和response,所以之前的请求参数都存在
	 * 效果：在forward.jsp页面中,可以利用jstl表达式取出username的值;
	 */
	@GetMapping("/forward")
	public String testForward(Model model){
		model.addAttribute("username","我以后会有个女儿,叫冯朗");
		return "forward:/testforward";
	}
	
	/**
	 * forward:请求转发
	 * 使用转发的方式实现,转发后浏览器地址栏还是原来的请求地址;
	 * 转发并没有执行新的request和response,所以之前的请求参数都存在
	 */
	@GetMapping("/forward2")
	public String testForward(@RequestParam("username")String username){
		System.out.println(username);
		return "forward:/testforward";
	}
	
	@PostMapping("/convert")
	public String convert(Bean bean){
		System.out.println(bean.getUsername());
		System.out.println("------------>"+bean);
		return "redirect:/list";
	}
	
	@GetMapping("/testconvert")
	public String testConvert(CustomBean user){
		System.out.println(user);
		return "forward";
	}
	
	/**
	 * 此方法抛出的异常由GlobalExceptionHanlder处理;
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	@GetMapping("/testexception")
	public String testException(CustomBean user) throws Exception{
		if(true){
			throw new SQLException("SQL错误");
		}
		int i=1/0;
		return "forward";
	}
	
	/**注意：
	 * 在Controller中抛出的异常,当没有被catch处理时,
	 * GlobalExceptionHandler中定义的处理方法可以起作用!
	 * 此方法抛出的异常由GlobalExceptionHanlder处理;
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/exception")  
    public String exception() throws Exception {   
        throw new BusinessException("业务执行异常");  
    } 
	
	/**
	 * 此方法抛出的异常不是由GlobalExceptionHandler处理,
	 * 而是在catch代码块内进行处理;
	 */
	@GetMapping("/exceptions")  
    public String exceptions(){   
        try {
			throw new BusinessException("业务执行异常");
		} catch (BusinessException e) {
			e.printStackTrace();
		}  
        return "forward";
    } 
	
	@GetMapping("/login")
	public String toLoginPage(){
		return "login";
	}
	
	@PostMapping("/login")
	public String login(String username,HttpSession session){
		session.setAttribute("username",username);
		return "redirect:/list";
	}
	
	@GetMapping("/sendget")
	@ResponseBody
	public String sendGet(){
		String url="http://apis.juhe.cn/idcard/index?key=5086bcbb0779bea2f88385de94ce89bd&dtype=json&cardno=410185199004141596";
		String result=HttpClientUtil.doGet(url);
		System.out.println(result);
		return result;
	}

}
