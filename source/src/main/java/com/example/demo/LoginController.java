package com.example.demo;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
	@Autowired
	private MemoRepository memoDao;
	
	@Autowired
	private BookInfoRepository bookDao;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String index(HttpServletRequest request) {
		return "index";
	}
	
	@RequestMapping(value="/loginok", method=RequestMethod.GET)
	public String loginok(HttpServletRequest request, HttpSession session) {
		String userId = (String)session.getAttribute("loginok");
		Optional<Memo> result = memoDao.findByUserid(userId);
		Memo resultMemo = result.get();
		
		request.setAttribute("current", userId);
		request.setAttribute("addr", resultMemo.getAddr());
		return "logged";
	}
	
	@RequestMapping(value="/search", method=RequestMethod.GET)
	public String search(HttpServletRequest request) {
		String searchText = request.getParameter("searchText");
		List<BookInfo> result = bookDao.findByNameContaining(searchText);
		request.setAttribute("bookList", result);
		return "book_list";
	}
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String bookList(HttpServletRequest request) {
		List<BookInfo> result = bookDao.findAll(Sort.by(Sort.Direction.DESC, "id"));
		request.setAttribute("bookList", result);
		return "book_list";
	}
	@RequestMapping(value="/insertbook", method=RequestMethod.GET)
	public String bookInsert(HttpServletRequest request) {
		String name = request.getParameter("name");
		String isbn = request.getParameter("isbn");
		String author = request.getParameter("author");
		String date = request.getParameter("date");
		String info = request.getParameter("info");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime dateTime = LocalDate.parse(date, formatter).atStartOfDay(); 
		BookInfo bookInfo = BookInfo.builder()
						.name(name)
						.isbn(isbn)
						.author(author)
						.publishDate(dateTime)
						.info(info)
						.build();
		bookDao.save(bookInfo);
		return "complete_book";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String joinStart(HttpServletRequest request) {
		String userId = request.getParameter("id");
		String userPassword = request.getParameter("password");
		String addr = request.getParameter("addr");
		Memo memo = Memo.builder()
						.userid(userId)
						.password(userPassword)
						.addr(addr)
						.build();
		memoDao.save(memo);
		return "complete";
	}
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String loginStart(HttpServletRequest request, HttpSession session) {
		String userId = request.getParameter("id");
		String userPassword = request.getParameter("password");
		Optional<Memo> result = memoDao.findByUserid(userId);
		Memo resultMemo = result.get();
		
		LoginClass loginClass = new LoginClass();
		if (loginClass.login(memoDao, userId, userPassword) == true) {
			request.setAttribute("current", userId);
			request.setAttribute("addr", resultMemo.getAddr());
			session.setAttribute("loginok", userId);
			return "logged";
		} else {
			return "fail";
		}		
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logoutCall(HttpServletRequest request, HttpSession session) {
		session.setAttribute("loginok", "");
		return "logout";
	}
	
	@RequestMapping(value="/deletemember", method=RequestMethod.GET)
	public String deleteMember(HttpServletRequest request) {
		String userId = request.getParameter("userid");
		memoDao.deleteByUserid(userId);
		return "logout";
	}
	
	@RequestMapping(value="/deleteBook", method=RequestMethod.GET)
	public String deleteBook(HttpServletRequest request) {
		String id = request.getParameter("id");
		Long delId = Long.parseLong(id);
		bookDao.deleteById(delId);
		List<BookInfo> result = bookDao.findAll(Sort.by(Sort.Direction.DESC, "id"));
		request.setAttribute("bookList", result);
		return "book_list";
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.GET)
	public String modify(HttpServletRequest request) {
		String userId = request.getParameter("id");
		String userPassword = request.getParameter("password");
		String addr = request.getParameter("addr");
		
		Optional<Memo> result = memoDao.findByUserid(userId);
		Memo resultMemo = result.get();
		
		Memo memo = Memo.builder()
				.id(resultMemo.getId())
				.userid(userId)
				.password(userPassword)
				.addr(addr)
				.build();
		memoDao.save(memo);
		return "logout";
	}
}
