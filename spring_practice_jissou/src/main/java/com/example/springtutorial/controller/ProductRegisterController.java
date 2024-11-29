package com.example.springtutorial.controller;

import org.springframework.core.Conventions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.springtutorial.form.ProductRegisterForm;

@Controller
public class ProductRegisterController {
	
	@GetMapping("/register")
	public String register(Model model) {
		if(!model.containsAttribute("form")) {
			model.addAttribute("form",new ProductRegisterForm());
		}
		return "productRegisterView";
	}
	
	@PostMapping("/check")
	public String check(RedirectAttributes redirectAttributes,
			@Validated ProductRegisterForm form, BindingResult result) {
		//バリデーションエラーのあるなしをチェック
		System.out.println(result.getErrorCount());
		
		if(result.hasErrors()) {
			//formの紐づけ
			redirectAttributes.addFlashAttribute("form", form);
			System.out.println("チェックのif文中のエラーです");
			//エラーを送付
			//BindingResultオブジェクトを参照して名前をSpringBootで有効な形式に変換
			redirectAttributes.addFlashAttribute( BindingResult.MODEL_KEY_PREFIX 
				+Conventions.getVariableName(form) , result);
			return "redirect:/register";
		}
		//formの紐づけ→registerで実行
		//redirectAttributes.addFlashAttribute("form", form);
		//リダイレクト
		System.out.println("チェックのif文外のエラーです");
		
		return "redirect:/register";
	}
}
