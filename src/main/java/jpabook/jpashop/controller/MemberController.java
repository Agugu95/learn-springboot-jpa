package jpabook.jpashop.controller;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService  memberService; // 컨트롤러가 보통 서비스를 가져다 씀

    @GetMapping(value = "/members/new") // HTTP GET
    public String createForm(Model model) {

        // view 속성에 form 담아 반환(빈 껍떼기), thymeleaf에서 오브젝트로 받을 수 있음
        model.addAttribute("memberForm", new MemberForm());
        // 실제 view로 보여질 html, 서버 사이드 렌더링
        return "members/createMemberForm";
    }

    @PostMapping(value = "/members/new") // HTTP POST, 데이터 삽입
    public String create(@Valid MemberForm form, BindingResult result) {
        // 에러가 생기면 BindingResult로 에러가 넘어감
        if (result.hasErrors()) { // result에 에러가 있으면 다시 createMemberForm으 리턴
            return "members/createMemberForm";
        }

        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());
        Member member = new Member();
        member.setName(form.getName());
        member.setAddress(address);

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping(value = "/members") // HTTP GET
    public String list(Model model) { // 정말 심플하기에 엔티티를 그대로 쓴거지만 원래라면 이것도 form을 통해서 처리해야 함
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

}
