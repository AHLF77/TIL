## day0413

### src/main/java
#### com.multi
- MessageByLocale
```java
package com.multi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.web.server.ServerWebExchange;

@Configuration
public class MessageByLocale {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private LocaleResolve localeResolver;

    public String getMessage(String code, ServerWebExchange exchange) {
        LocaleContext localeContext = localeResolver.resolveLocaleContext(exchange);
        return messageSource.getMessage(code, null, localeContext.getLocale());
    }
}
```

- LocaleResolve
```java
package com.multi;

import java.util.Locale;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.SimpleLocaleContext;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.i18n.LocaleContextResolver;

@Configuration
public class LocaleResolve implements LocaleContextResolver {

    @Override
    public LocaleContext resolveLocaleContext(ServerWebExchange exchange) {
        //String language = exchange.getRequest().getHeaders().getFirst("Accept-Language");
        String language = exchange.getRequest().getQueryParams().getFirst("lang");
        Locale targetLocale = Locale.getDefault();
        if (language != null && !language.isEmpty()) {
            targetLocale = Locale.forLanguageTag(language);
        }
        return new SimpleLocaleContext(targetLocale);
    }

    @Override
    public void setLocaleContext(ServerWebExchange exchange, LocaleContext localeContext) {
        throw new UnsupportedOperationException("Not Supported");
    }

}
```

- WebConfig
```java
package com.multi;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class WebConfig implements ApplicationContextAware {

    ApplicationContext context;

  
    
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("messages/message");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }



    @Override
    public void setApplicationContext(ApplicationContext context) {
        this.context = context;
    }
}
```

### src/main/java
#### com.multi.biz
- ProductBiz
```java
package com.multi.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.frame.Biz;
import com.multi.mapper.ProductMapper;
import com.multi.vo.ProductVO;

@Service("productbiz")
public class ProductBiz implements Biz<Integer, ProductVO>{

	@Autowired
	ProductMapper dao;
	
	@Override
	public void register(ProductVO v) throws Exception {
		dao.insert(v);
	}

	@Override
	public void modify(ProductVO v) throws Exception {
		dao.update(v);
	}

	@Override
	public void remove(Integer k) throws Exception {
		dao.delete(k);
	}

	@Override
	public ProductVO get(Integer k) throws Exception {
		return dao.select(k);
	}

	@Override
	public List<ProductVO> get() throws Exception {
		return dao.selectall();
	}
	
	public int getcnt() throws Exception{
		return dao.selectcnt();
	}
	
}

```

### src/main/java
#### com.multi.controller
- CustController
```java
package com.multi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.multi.biz.CustBiz;
import com.multi.vo.CustVO;

@Controller
@RequestMapping("/cust")
public class CustController {

	@Autowired
	CustBiz biz;
	
	@RequestMapping("")
	public ModelAndView main(ModelAndView mv) {
		mv.setViewName("main");
		mv.addObject("left", "cust/left");
		mv.addObject("center", "cust/center");
		return mv;
	}
	@RequestMapping("/register")
	public ModelAndView register(ModelAndView mv) {
		mv.setViewName("main");
		mv.addObject("left", "cust/left");
		mv.addObject("center", "cust/register");
		return mv;
	}
	@RequestMapping("/registerimpl")
	public ModelAndView registerimpl(ModelAndView mv,CustVO obj) {
		System.out.println(obj);
		mv.setViewName("main");
		mv.addObject("left", "cust/left");
		try {
			biz.register(obj);
			mv.addObject("center", "cust/registerok");
			mv.addObject("rcust", obj);
		} catch (Exception e) {
			mv.addObject("center", "cust/registerfail");
		}
		return mv;
	}
	@RequestMapping("/custselect")
	public ModelAndView custselect(ModelAndView mv) {
		mv.setViewName("main");
		mv.addObject("left", "cust/left");
		List<CustVO> list = null;
		try {
			list = biz.get();
			mv.addObject("center", "cust/custselect");
			mv.addObject("allcust", list);
		} catch (Exception e) {
			mv.addObject("center", "cust/registerfail");
		}
		return mv;
	}
	@RequestMapping("/custdetail")
	public ModelAndView detail(ModelAndView mv, String id) {
		mv.setViewName("main");
		mv.addObject("left", "cust/left");
		CustVO obj = null;
		try {
			obj = biz.get(id);
			mv.addObject("center", "cust/custdetail");
			mv.addObject("dcust", obj);
		} catch (Exception e) {
			mv.addObject("center", "cust/registerfail");
		}
		return mv;
	}
	@RequestMapping("/custdelete")
	public String custdelete(String id) {
		try {
			biz.remove(id);
		} catch (Exception e) {
				
		}
		return "redirect:custselect";
	}
	@RequestMapping("/custupdate")
	public ModelAndView custupdate(ModelAndView mv, String id) {
		CustVO cust = null;
		mv.setViewName("main");
		mv.addObject("left", "cust/left");
		try {
			cust = biz.get(id);
			mv.addObject("ucust", cust);
			mv.addObject("center", "cust/custupdate");
		} catch (Exception e) {
				
		}
		return mv;
	}
	@RequestMapping("/custupdateimpl")
	public String custupdateimpl(CustVO cust) {
		try {
			biz.modify(cust);
		} catch (Exception e) {
				
		}
		return "redirect:custdetail?id="+cust.getId();
	}
}

```

- MainController
```java
package com.multi.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multi.biz.CustBiz;
import com.multi.vo.CustVO;

@Controller
public class MainController {

	@Autowired
	CustBiz cbiz;
	
	@RequestMapping("/")
	public String main() {
		return "main";
	}
	
	@RequestMapping("/login")
	public String login(Model m) {
		m.addAttribute("center", "login");
		return "main";
	}
	@RequestMapping("/logout")
	public String logout(Model m, HttpSession session) {
		if(session != null) {
			session.invalidate();
		}
		return "main";
	}
	
	
	@RequestMapping("/loginimpl")
	public String loginimpl(Model m, String id, String pwd, HttpSession session) {
		String next = "";
		CustVO cust = null;
		try {
			cust = cbiz.get(id);
			if(cust != null) {
				if(cust.getPwd().equals(pwd)) {
					session.setAttribute("logincust", cust);
					m.addAttribute("logincust", cust);
					next = "loginok";
				}else {
					throw new Exception();
				}
			}else {
				throw new Exception();
			}
		} catch (Exception e) {
			next="loginfail";
		}
	
		m.addAttribute("center", next);
		return "main";
	}
	
	
	
}
```

- ProductController
```java
package com.multi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.multi.biz.ProductBiz;
import com.multi.vo.ProductVO;

@Controller
@RequestMapping("product")
public class ProductController {
	
	@Autowired
	ProductBiz pbiz;
	
	@RequestMapping("")
	public ModelAndView main(ModelAndView mv) {
		mv.setViewName("main");
		mv.addObject("left","product/left");
		mv.addObject("center","product/center");
		return mv;
	}
	
	@RequestMapping("/register")
	public String register(Model m) {
		m.addAttribute("left","product/left");
		m.addAttribute("center","product/register");
		return "main";
	}
	
	@RequestMapping("/registerimpl")
	public ModelAndView registerimpl(ModelAndView mv, ProductVO obj) {
		mv.setViewName("main");
		mv.addObject("left","product/left");
		try {
			pbiz.register(obj);
			int cnt = pbiz.getcnt();
			mv.addObject("cnt", cnt);
			mv.addObject("center","product/registerok");
		} catch (Exception e) {
			mv.addObject("center","product/registerfail");
		}
		return mv;
	}
	
	@RequestMapping("/productselect")
	public ModelAndView productselect(ModelAndView mv) {
		mv.setViewName("main");
		mv.addObject("left","product/left");
		mv.addObject("center","product/productselect");

		List<ProductVO> list = null;
		try {
			list = pbiz.get();
			mv.addObject("allproduct", list);
		} catch (Exception e) {
			
		}
		return mv;
	}
	
	@RequestMapping("productdetail")
	public ModelAndView productdetail(ModelAndView mv, Integer id) {
		ProductVO prod = null;
		try {
			prod = pbiz.get(id);
			mv.addObject("dproduct", prod);
		} catch (Exception e) {
			
		}
		mv.setViewName("main");
		mv.addObject("left","product/left");
		mv.addObject("center","product/productdetail");
		return mv;
	}
	
	@RequestMapping("productupdate")
	public ModelAndView productupdate(ModelAndView mv, Integer cnt) {
		mv.setViewName("main");
		mv.addObject("left","product/left");
		mv.addObject("center","product/productupdate");
		
		ProductVO prod = null;
		try {
			prod = pbiz.get(cnt);
			mv.addObject("uproduct", prod);
		} catch (Exception e) {
			
		}
		return mv;	
	}
	
	@RequestMapping("productupdateimpl")
	public String productupdateimpl(ProductVO prod) {
		String next= "productupdateok";
		try {
			pbiz.modify(prod);
		} catch (Exception e) {
			
		}
		return "redirect:productdetail?id="+prod.getId();
	}
	
	@RequestMapping("productdelete")
	public String productdelete(Integer cnt) {
		try {
			pbiz.remove(cnt);
		} catch (Exception e) {
			
		}
		return "redirect:productselect";	
	}
}
```

### src/main/java
#### com.multi.frame
- Biz
```java
package com.multi.frame;

import java.util.List;

public interface Biz<K,V> {
	public void register(V v) throws Exception;
	public void modify(V v) throws Exception;
	public void remove(K k) throws Exception;
	public V get(K k) throws Exception;
	public List<V> get() throws Exception;
}

```