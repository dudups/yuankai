package com.example.validateddemo.controller;

import com.example.validateddemo.base.Result;
import com.example.validateddemo.entity.dto.Team1Dto;
import com.example.validateddemo.entity.dto.User1Dto;
import com.example.validateddemo.entity.dto.User2Dto;
import com.example.validateddemo.interfaces.Group1;
import com.example.validateddemo.interfaces.Group2;
import com.example.validateddemo.utils.ResultUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.constraints.Min;
import javax.validation.groups.Default;


class Annoyance extends Exception {}
class Sneeze extends Annoyance {}
/**
 * @author He Changjie on 2020/9/5
 */
@RestController
@RequestMapping("/api/v1")
@Validated
public class Demo1Controller {

    public static void main(String[] args) throws Exception {
        try {
            try {
                throw new Sneeze();
            }
            catch ( Annoyance a ) {
                System.out.println("Caught Annoyance");
                throw a;
            }
        }
        catch ( Sneeze s ) {
            System.out.println("Caught Sneeze");
            return ;
        }
        finally {
            System.out.println("Hello World!");
        }
    }

    @PostMapping("/insert")
    public Result validatedDemo1(@RequestParam @Min(value = 1, message = "id 必须大于等于 1") Long id, @Validated @RequestBody User1Dto user1Dto){
        return ResultUtil.success(user1Dto);
    }

    @PostMapping("/insert2")
    public Result validatedDemo2(@Validated @RequestBody Team1Dto team1Dto){
        return ResultUtil.success(team1Dto);
    }

    @PostMapping("/insert3")
    public Result validatedDemo3(@Validated(Default.class) @RequestBody User2Dto user2Dto){
        return ResultUtil.success(user2Dto);
    }

    @PostMapping("/insert4")
    public Result validatedDemo4(@Validated(Group1.class) @RequestBody User2Dto user2Dto){
        return ResultUtil.success(user2Dto);
    }

    @PostMapping("/insert5")
    public Result validatedDemo5(@Validated(Group2.class) @RequestBody User2Dto user2Dto){
        return ResultUtil.success(user2Dto);
    }
}
