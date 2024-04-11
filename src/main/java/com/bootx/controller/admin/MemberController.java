
package com.bootx.controller.admin;

import com.bootx.common.Pageable;
import com.bootx.common.Result;
import com.bootx.controller.BaseController;
import com.bootx.entity.App;
import com.bootx.entity.BaseEntity;
import com.bootx.service.AppService;
import com.bootx.service.MemberService;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author black
 */
@RestController("adminMemberController")
@RequestMapping("/admin/api/member")
public class MemberController extends BaseController {

	@Resource
	private MemberService memberService;
	@Resource
	private AppService appService;


	@PostMapping("/list")
	@JsonView(BaseEntity.PageView.class)
	public Result members(Long appId,Pageable pageable){
		App app = appService.find(appId);
		return Result.success(memberService.findPage(pageable,app));
	}

}